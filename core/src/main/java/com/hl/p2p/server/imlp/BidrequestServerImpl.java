package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.BidrequestMapper;
import com.hl.p2p.pojo.Account;
import com.hl.p2p.pojo.Bid;
import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.query.BidRequestQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.IAccountServer;
import com.hl.p2p.server.IBidrequestServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.BidConst;
import com.hl.p2p.utils.BitStatesUtils;
import com.hl.p2p.utils.CalculatetUtil;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    qo.setBidrequeststate(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
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
    bidrequest.setBidrequeststate(state);
    bidrequest.setNote(remark);
    bidrequestMapper.updateByPrimaryKey(bidrequest);
  }
}
