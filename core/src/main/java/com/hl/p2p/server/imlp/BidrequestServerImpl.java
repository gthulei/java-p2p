package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.BidrequestMapper;
import com.hl.p2p.mapper.PaymentscheduleMapper;
import com.hl.p2p.mapper.PaymentscheduledetailMapper;
import com.hl.p2p.pojo.*;
import com.hl.p2p.query.BidRequestQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.*;
import com.hl.p2p.utils.*;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

  @Autowired
  private  ISystemaAcountServer systemaAcountServer;

  @Autowired
  private ISystemaccountflowServer systemaccountflowServer;

  @Autowired
  private PaymentscheduleMapper paymentScheduleMapper;

  @Autowired
  private PaymentscheduledetailMapper paymentScheduleDetailMapper;

  public Bidrequestaudithistory bidVo(Bidrequest bidrequest,Userinfo userinfo){
    Bidrequestaudithistory bidrequestaudithistory = new Bidrequestaudithistory();
    bidrequestaudithistory.setAudittime(new Date());
    bidrequestaudithistory.setApplytime(bidrequest.getApplytime());
    bidrequestaudithistory.setAuditor(UserContext.getCurrent());
    Logininfo logininfo = new Logininfo();
    logininfo.setId(userinfo.getId());
    bidrequestaudithistory.setApplier(logininfo);
    bidrequestaudithistory.setBidrequestid(userinfo.getId());
    return bidrequestaudithistory;
  }
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
      Bidrequestaudithistory bidrequestaudithistory = this.bidVo(bidrequest, userinfo);
      bidrequestaudithistory.setRemark(remark);
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
      this.update(bidrequest);
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
      bidServer.saveBid(bid);
      bidrequestMapper.updateByPrimaryKey(bidrequest);
      accountServer.updateAccount(accountInfo);
      // 账户流水
      accountflowServer.createBidAccountflow(amount,accountInfo,bidrequest.getId());
    }
  }

  /**
   * 满标一审
   * @param id
   * @param state
   * @param remark
   */
  @Override
  public void borrowFullAuditOne(Long id, int state, String remark) {
    Bidrequest bidrequest = bidrequestMapper.selectByPrimaryKey(id);
    Userinfo userinfo = userinfoServer.getUserinfoById(bidrequest.getCreateuser().getId());
    // 是否符合审核条件
    if(bidrequest.getBidrequeststate() == BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1){
      // 审核记录
      Bidrequestaudithistory bidrequestaudithistory = this.bidVo(bidrequest, userinfo);
      bidrequestaudithistory.setRemark(remark);
      bidrequestaudithistory.setAudittype(Bidrequestaudithistory.FULL_AUDIT1);//满标一审
      //审核历史
      bidrequestaudithistoryServer.add(bidrequestaudithistory);
      // 审核成功
      if(state==BidConst.BIDREQUEST_STATE_BIDDING){
        bidrequest.setPublishtime(new Date());
        // 设置审核状态
        bidrequest.setBidrequeststate(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2);
        bidrequest.setNote(remark);
      }else {
        // 设置审核状态
        bidrequest.setBidrequeststate(BidConst.BIDREQUEST_STATE_REJECTED);
        // 移除在申请中的状态
        userinfo.removeState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS) ;
        this.userinfoServer.updateUserInfo(userinfo);
        //退款
        this.returnMoney(bidrequest);
      }
      //更新标审核信息
      this.update(bidrequest);
    }
  }

  /**
   * 满标二审
   * @param id
   * @param state
   * @param remark
   */
  @Override
  public void borrowFullAuditTwo(Long id, int state, String remark) {
    Bidrequest bidrequest = bidrequestMapper.selectByPrimaryKey(id);
    Userinfo userinfo = userinfoServer.getUserinfoById(bidrequest.getCreateuser().getId());
    // 是否符合审核条件
    if(bidrequest.getBidrequeststate() == BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2){
      // 移除在申请中的状态
      userinfo.removeState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS) ;
      this.userinfoServer.updateUserInfo(userinfo);
      // 审核记录
      Bidrequestaudithistory bidrequestaudithistory = this.bidVo(bidrequest, userinfo);
      bidrequestaudithistory.setRemark(remark);
      bidrequestaudithistory.setAudittype(Bidrequestaudithistory.FULL_AUDIT2);//满标二审
      //审核历史
      bidrequestaudithistoryServer.add(bidrequestaudithistory);
      // 审核成功
      if(state==BidConst.BIDREQUEST_STATE_BIDDING){
        // 设置审核状态
        bidrequest.setBidrequeststate(BidConst.BIDREQUEST_STATE_PAYING_BACK);
        // 借款人账户余额增加 , 生成同一条账户流水
        Account account = accountServer.getAccountInfoById(bidrequest.getCreateuser().getId());
        account.setUsableamount(account.getUsableamount().add(bidrequest.getBidrequestamount()));
        account.setRemainborrowlimit(account.getRemainborrowlimit().subtract(bidrequest.getBidrequestamount()));
        account.setUnreturnamount(account.getUnreturnamount().add(bidrequest.getTotalrewardamount())
          .add(bidrequest.getBidrequestamount()));
        // 账户流水
        this.accountflowServer.createBorrowAccountflow(account,bidrequest);
        // 平台手续费
        BigDecimal borrowChargeFee = CalculatetUtil.calAccountManagementCharge(bidrequest.getBidrequestamount());

        this.accountflowServer.createBorrowChargeFeeAccountflow(account,bidrequest,borrowChargeFee);
        account.setUsableamount(account.getUsableamount().subtract(borrowChargeFee));
        this.accountServer.updateAccount(account);
        Long systemaccountId = this.systemaAcountServer.seveSystemaAcount(borrowChargeFee);
        // 系统账户流水
        this.systemaccountflowServer.SystemaMgtFeeccountflow(bidrequest,borrowChargeFee,systemaccountId);

        Map<Long, Account> map = new HashMap<>();
        for(Bid bid :bidrequest.getBids()){
          Long amountId = bid.getBiduser().getId();//投资人ID
          Account investmentAccount = map.get(amountId);
          // 不存在去查询，已存在直接取
          if (investmentAccount==null){
            investmentAccount =this.accountServer.getAccountInfoById(amountId);
            map.put(amountId,investmentAccount);
          }
          investmentAccount.setFreezedamount(investmentAccount.getFreezedamount().subtract(bid.getAvailableamount()));
        }
        //满标审核对还款流程 : 生成针对这个借款的还款信息和回款信息
        List<Paymentschedule> pss = createPaymentSchedule(bidrequest);

        //增加待收利息和待收本金
        for (Paymentschedule ps : pss) {
          for (Paymentscheduledetail psd : ps.getPaymentScheduleDetails()) {
            Account bidAccount = map.get(psd.getTologininfoId());  //得到收款人的账户
            //待收本金
            bidAccount.setUnreceiveprincipal(bidAccount.getUnreceiveprincipal().add(psd.getPrincipal()));
            bidAccount.setUnreceiveinterest(bidAccount.getUnreceiveinterest().add(psd.getInterest())) ;
          }

        }
        for (Account mapAccount : map.values()) {
          this.accountflowServer.bidFreezeAccountflow(mapAccount);
          this.accountServer.updateAccount(mapAccount);
        }

      }else {
        // 设置审核状态
        bidrequest.setBidrequeststate(BidConst.BIDREQUEST_STATE_REJECTED);
        // 移除在申请中的状态
        userinfo.removeState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS) ;
        this.userinfoServer.updateUserInfo(userinfo);
        //退款
        this.returnMoney(bidrequest);
      }
      this.update(bidrequest);
    }
  }
  /**
   * 创建针对这个借款的还款信息和汇款信息
   */
  private List<Paymentschedule> createPaymentSchedule(Bidrequest bidrequest) {
    List<Paymentschedule> ret = new ArrayList<>() ;
    //用于 累加本金
    BigDecimal totalPrincipal = BidConst.ZERO ;
    //用于累加利息
    BigDecimal totalInterest = BidConst.ZERO ;
    for (int i = 0; i < bidrequest.getMonthes2return(); i++) {
      //每期都是一个还款对象
      Paymentschedule ps = new Paymentschedule();
      ps.setBidrequestId(bidrequest.getId());
      ps.setBidrequesttitle(bidrequest.getTitle());
      ps.setBidrequesttype(bidrequest.getBidrequesttype());
      ps.setBorrowuser(bidrequest.getCreateuser());
      ps.setDeadline(DateUtils.addMonths(bidrequest.getPublishtime(), i+1));
      ps.setMonthindex(i+1);
      ps.setReturntype(bidrequest.getReturnType());
      ps.setState(BidConst.PAYMENT_STATE_NORMAL);
      if (i<bidrequest.getMonthes2return() - 1) {
        //每期要还款的利息
        ps.setInterest(CalculatetUtil.calMonthlyInterest(bidrequest.getReturnType(), bidrequest.getBidrequestamount(),
          bidrequest.getCurrentrate(), i+1, bidrequest.getMonthes2return()));
        // 每期还款总金额，利息 +本金
        ps.setTotalamount(CalculatetUtil.calMonthToReturnMoney(bidrequest.getReturnType(), bidrequest.getBidrequestamount(),
          bidrequest.getCurrentrate(), i+1, bidrequest.getMonthes2return()));
        //本期还款本金   总的还款 - 利息
        ps.setPrincipal(ps.getTotalamount().subtract(ps.getInterest()));

        totalPrincipal=totalPrincipal.add(ps.getPrincipal());
        totalInterest=totalInterest.add(ps.getInterest());
      }else{
        //最后一期
        ps.setInterest(bidrequest.getTotalrewardamount().subtract(totalInterest));
        ps.setPrincipal(bidrequest.getBidrequestamount().subtract(totalPrincipal));
        ps.setTotalamount(ps.getInterest().add(ps.getPrincipal())) ;
      }
      this.paymentScheduleMapper.insert(ps);
      createPaymentScheduleDetail(ps,bidrequest); //创建每期还款对象对于的汇款明细
      ret.add(ps);
    }
    return ret ;
  }

  /**
   * 创建针对每一期还款的回款对象
   * @param ps
   * @param bidRequest
   */
  private void createPaymentScheduleDetail(Paymentschedule ps, Bidrequest bidRequest) {
    //用于 累加 本期还款本金
    BigDecimal totalPrincipal = BidConst.ZERO ;
    //用于累加 总金额 (本金+利息)
    BigDecimal totalAmount = BidConst.ZERO ;
    for (int i = 0; i < bidRequest.getBids().size(); i++) {
      Bid bid = bidRequest.getBids().get(i);
      //针对每一个投标创建已个回款对象
      Paymentscheduledetail psd = new Paymentscheduledetail();
      psd.setBidamount(bid.getAvailableamount());
      psd.setBidrequestId(bidRequest.getId());
      psd.setBidId(bid.getId());
      psd.setDeadline(ps.getDeadline());
      psd.setFromlogininfo(bidRequest.getCreateuser());
      psd.setMonthindex(i+1) ;
      psd.setTologininfoId(bid.getBiduser().getId());
      psd.setPaymentscheduleId(ps.getId());
      psd.setReturntype(bidRequest.getReturnType());
      if ( i < bidRequest.getBids().size()-1) {
        // 回款本金 = 投标金额 / 借款金额 * 本期还款本金
        psd.setPrincipal(bid.getAvailableamount().divide(bidRequest.getBidrequestamount(),
          BidConst.CAL_SCALE, RoundingMode.HALF_UP).multiply(ps.getPrincipal()
          .setScale(BidConst.CAL_SCALE, RoundingMode.HALF_UP)));
        // 汇款利息 = 投标金额/ 借款金额 * 本期还款利息
        psd.setInterest(bid.getAvailableamount().divide(bidRequest.getBidrequestamount(),
          BidConst.CAL_SCALE, RoundingMode.HALF_UP).multiply(ps.getInterest()
          .setScale(BidConst.CAL_SCALE, RoundingMode.HALF_UP)));
        psd.setTotalamount(psd.getInterest().add(psd.getPrincipal()));

        totalPrincipal=totalPrincipal.add(psd.getPrincipal());   //本金
        totalAmount=totalAmount.add(psd.getTotalamount());		//本息

      }else{
        //最后一个回款明细
        psd.setPrincipal(ps.getPrincipal().subtract(totalPrincipal)); //本期的剩余本金
        psd.setTotalamount(ps.getTotalamount().subtract(totalAmount)); //本期的剩余本息
        psd.setInterest(psd.getTotalamount().subtract(psd.getPrincipal()));   //剩余的利息
      }
      this.paymentScheduleDetailMapper.insert(psd);
      ps.getPaymentScheduleDetails().add(psd);
    }
  }

  /**
   * 退款处理
   * @param bidrequest
   */
  public void returnMoney(Bidrequest bidrequest){
    Map<Long, Account> map = new HashMap<>();
    for(Bid bid :bidrequest.getBids()){
      Long amountId = bid.getBiduser().getId();//投资人ID
      Account account = map.get(amountId);
      // 不存在去查询，已存在直接取
      if (account==null){
        account = accountServer.getAccountInfoById(amountId);
        map.put(amountId,account);
      }
      account.setUsableamount(account.getUsableamount().add(bid.getAvailableamount()));
      account.setFreezedamount(account.getFreezedamount().subtract(bid.getAvailableamount()));
    }
    // 再统一去修改投标人对应的账户
    for (Account bidAccount : map.values()) {
      // 账户流水
      this.accountflowServer.bidEscAccountflow(bidAccount,bidrequest);
      this.accountServer.updateAccount(bidAccount);
    }
  }

}
