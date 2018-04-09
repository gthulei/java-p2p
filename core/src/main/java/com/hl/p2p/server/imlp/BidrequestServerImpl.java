package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.BidrequestMapper;
import com.hl.p2p.pojo.*;
import com.hl.p2p.query.BidRequestQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.*;
import com.hl.p2p.utils.*;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 发标借款
 */
@Service
public class BidrequestServerImpl implements IBidrequestServer{

  @Autowired
  private BidrequestMapper bidrequestMapper;

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IAccountServer accountServer;

  @Autowired
  private IAccountflowServer accountflowServer;

  @Autowired
  private IBidrequestaudithistoryServer bidrequestaudithistoryServer;

  @Autowired
  private IBidServer bidServer;

  /**
   * 发标
   * @param bidrequest
   * @return
   */
  @Override
  public boolean apply(Bidrequest bidrequest) {
    Userinfo userinfo = userinfoServer.getUserinfoById(UserContext.getCurrent().getId());
    Account account = accountServer.getAccountInfoById(UserContext.getCurrent().getId());
    if(this.canApply(userinfo)//判断当前用户是否可以申请借款
      && !userinfo.getHasBidRequestInProcess()//是否有在申请的标
      && bidrequest.getBidrequestamount().compareTo(BidConst.SMALLEST_BIDREQUEST_AMOUNT) >=0//借款最小金额
      && bidrequest.getBidrequestamount().compareTo(account.getRemainborrowlimit()) <=0//借款最大金额
      && bidrequest.getCurrentrate().compareTo(BidConst.SMALLEST_CURRENT_RATE) >=0//借款最小利率
      && bidrequest.getCurrentrate().compareTo(BidConst.MAX_CURRENT_RATE) <=0 //借款最大利率
      && bidrequest.getMonthes2return() >= BidConst.BORROW_MIN_LIMIT // 借款最小期限
      && bidrequest.getMonthes2return() <= BidConst.BORROW_MAX_LIMIT//借款最大期限
      && bidrequest.getMinbidamount().compareTo(BidConst.SMALLEST_BID_AMOUNT) >=0//最小投标
      && bidrequest.getDisabledays() >= BidConst.RECURIT_BORROW_MIN_TIME//招标最小天数
      && bidrequest.getDisabledays() <= BidConst.RECURIT_BORROW_MAX_TIME//招标最大天数
      ){
      Bidrequest bid = new Bidrequest();
      bid.setBidrequestamount(bidrequest.getBidrequestamount());
      bid.setCurrentrate(bidrequest.getCurrentrate());
      bid.setDescription(bidrequest.getDescription());
      bid.setDisabledays(bidrequest.getDisabledays());
      bid.setMinbidamount(bidrequest.getMinbidamount());
      bid.setMonthes2return(bidrequest.getMonthes2return());
      bid.setReturnType(bidrequest.getReturnType());
      bid.setTitle(bidrequest.getTitle());
      //设置借款类型 :信用标    申请时间   申请人
      bid.setBidrequesttype(BidConst.BIDREQUEST_TYPE_NORMAL);
      bid.setApplytime(new Date());
      bid.setCreateuser(UserContext.getCurrent());
      //设置总的回报金额(总利息)    工具类中计算
      bid.setTotalrewardamount(CalculatetUtil.calTotalInterest(
        bidrequest.getReturnType(), bidrequest.getBidrequestamount(),
        bidrequest.getCurrentrate(), bidrequest.getMonthes2return()));
      //设置当前标的状态 .待发布
      bid.setBidrequeststate(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);

      // 给用户添加一个状态码
      userinfo.addState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS) ;
      this.userinfoServer.updateUserInfo(userinfo);
      return  this.bidrequestMapper.insert(bid) > 0;
    }
    return false;
  }

  /**
   * 更新标信息 乐观锁
   * @param bidrequest
   */
  @Override
  public void update(Bidrequest bidrequest) {
    int count = bidrequestMapper.updateByPrimaryKey(bidrequest);
    if (count <= 0) {
      throw new RuntimeException("乐观锁错误bidRequest:" +bidrequest.getId());
    }
  }

  /**
   * 查询申请的标
   * @param id
   * @return
   */
  @Override
  public Bidrequest get(Long id) {
    return bidrequestMapper.selectByPrimaryKey(id);
  }

  /**
   * 判断当前用户是否可以申请借款
   */
  @Override
  public boolean canApply(Userinfo user) {
    //判断实名 视频 基本资料 风控材料
    return user.getIsRealAuth() && user.getIsVedioAuth()
      && user.getIsBasicInfo()
      && user.getAuthscore() >= BidConst.CREDIT_BORROW_SCORE;
  }

  /**
   * 查询发标列表
   * @param qo
   * @return
   */
  @Override
  public PageResult getApplyList(BidRequestQueryObject qo) {
    int i = bidrequestMapper.selectCount();
    if (qo.getQuertState()==1){
      qo.setBidrequeststate(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
    }
    List<Bidrequest> resule = bidrequestMapper.selectPage(qo);
    PageResult pageResult = new PageResult();
    pageResult.setPageSize(qo.getPageSize());
    pageResult.setCurrentPage(qo.getCurrentPage());
    pageResult.setTotalCount(i);
    pageResult.setData(resule);
    return pageResult;
  }

  /**
   * 满标前审核
   * @param id
   * @param state
   */
  @Override
  public void borrowFullAudit(Long id, int state,String remark) {
    Bidrequest bidrequest = bidrequestMapper.selectByPrimaryKey(id);
    Userinfo userinfo = userinfoServer.getUserinfoById(bidrequest.getCreateuser().getId());
    // 判断有没有在申请中的标
    if(userinfo.getHasBidRequestInProcess()){
      Bidrequestaudithistory bidrequestaudithistory = new Bidrequestaudithistory();
      bidrequestaudithistory.setRemark(remark);
      bidrequestaudithistory.setAudittime(new Date());
      bidrequestaudithistory.setApplytime(bidrequest.getApplytime());
      bidrequestaudithistory.setAuditor(UserContext.getCurrent());
      Logininfo logininfo = new Logininfo();
      logininfo.setId(userinfo.getId());
      bidrequestaudithistory.setApplier(logininfo);
      bidrequestaudithistory.setBidrequestid(userinfo.getId());
      bidrequestaudithistory.setAudittype(Bidrequestaudithistory.PUBLISH_AUDIT);//设置发标请审核
      //审核历史
      bidrequestaudithistoryServer.add(bidrequestaudithistory);
      // 审核成功
      if(state==BidConst.BIDREQUEST_STATE_BIDDING){
        bidrequest.setPublishtime(new Date());
        // 设置审核状态
        bidrequest.setBidrequeststate(BidConst.BIDREQUEST_STATE_BIDDING);
        bidrequest.setNote(remark);
        // 到期时间
        bidrequest.setDisabledate(DateUtils.addDays(bidrequest.getPublishtime(),bidrequest.getDisabledays()));
      }else {
        // 设置审核状态
        bidrequest.setBidrequeststate(BidConst.BIDREQUEST_STATE_PUBLISH_REFUSE);
        // 移除在申请中的状态
        userinfo.removeState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS) ;
        this.userinfoServer.updateUserInfo(userinfo);
      }
      //审核
      bidrequestMapper.updateByPrimaryKey(bidrequest);
    }
  }

  /**
   * 前端投标
   */
  @Override
  public void borrowBid(Long bidRequestId,BigDecimal amount) {
    Bidrequest bidrequest = bidrequestMapper.selectByPrimaryKey(bidRequestId);
    // 账户信息
    Account accountInfo = accountServer.getAccountInfoById(UserContext.getCurrent().getId());

    if(bidrequest.getBidrequeststate()==BidConst.BIDREQUEST_STATE_BIDDING//招标中
      && !bidrequest.getCreateuser().getId().equals(UserContext.getCurrent().getId())//判断是否招标本人
      && amount.compareTo(bidrequest.getMinbidamount())>=0//最小投标金额
      && amount.compareTo(bidrequest.getRemainAmount())<=0//当前标的剩余金额
      && amount.compareTo(accountInfo.getUsableamount())<=0//账户可用余额
      ){
      //用户投标对象
      Bid bid = new Bid();
      bid.setActualrate(bidrequest.getCurrentrate());
      bid.setAvailableamount(amount);
      bid.setBidrequestId(bidRequestId);
      bid.setBidrequesttitle(bidrequest.getTitle());
      bid.setBidtime(new Date());
      bid.setBiduser(UserContext.getCurrent());
      // 投标总金额
      bidrequest.setCurrentsum(bidrequest.getCurrentsum().add(amount));
      // 次数
      bidrequest.setBidcount(bidrequest.getBidcount()+1);
      // 满标
      if(bidrequest.getBidrequestamount().equals(bidrequest.getCurrentsum())){
        bidrequest.setBidrequeststate(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
      }
      // 账户
      accountInfo.setFreezedamount(accountInfo.getFreezedamount().add(amount));
      accountInfo.setUsableamount(accountInfo.getUsableamount().subtract(amount));
      // 账户流水
      Accountflow accountflow = new Accountflow();
      accountflow.setAccountactiontype(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);
      accountflow.setNote("投标");
      accountflow.setAmount(accountflow.getAmount().subtract(amount));
      accountflow.setBalance(accountflow.getBalance().subtract(amount));
      accountflow.setFreezed(accountflow.getFreezed().add(amount));
      accountflow.setActiontime(new Date());
      accountflow.setAccountId(accountInfo.getId());
      bidServer.saveBid(bid);
      bidrequestMapper.updateByPrimaryKey(bidrequest);
      accountServer.updateAccount(accountInfo);
      accountflowServer.saveAccountflow(accountflow);
    }
  }
}
