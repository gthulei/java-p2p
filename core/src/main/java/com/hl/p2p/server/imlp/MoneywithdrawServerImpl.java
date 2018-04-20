package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.MoneywithdrawMapper;
import com.hl.p2p.pojo.*;
import com.hl.p2p.query.MoneyWithdrawQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.*;
import com.hl.p2p.utils.BidConst;
import com.hl.p2p.utils.BitStatesUtils;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 提现
 */
@Service
public class MoneywithdrawServerImpl implements IMoneywithdrawServer {

  @Autowired
  private MoneywithdrawMapper moneywithdrawMapper;

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IUserbankinfoServer userbankinfoServer;

  @Autowired
  private IRealauthServer realauthServer;

  @Autowired
  private IAccountServer accountServer;

  @Autowired
  private IAccountflowServer accountflowServer;

  /**
   * 提现申请
   * @param moneyAmount
   * @return
   */
  @Override
  public void withdrawalApply(BigDecimal moneyAmount) {
    Userinfo userinfo = userinfoServer.getUserinfoById(UserContext.getCurrent().getId());
    Userbankinfo userbank = userbankinfoServer.getUserbank(UserContext.getCurrent().getId());
    Realauth realauth = realauthServer.getRealauth(userinfo.getRealauthid());
    //申请中的提现
    if(userinfo.getHasWithdrawInProcess()){
      throw new RuntimeException("有一笔申请中的提现");
    }

    if(moneyAmount.compareTo(BidConst.MIN_WITHDRAW_AMOUNT)<0){
      throw new RuntimeException("最小提现金额为"+BidConst.MIN_WITHDRAW_AMOUNT);
    }
    Moneywithdraw withdraw = new Moneywithdraw();
    withdraw.setBanknumber(userbank.getBanknumber());
    withdraw.setBankforkname(userbank.getBankforkname());
    withdraw.setBankname(userbank.getBankname());
    withdraw.setRealname(realauth.getRealname());
    withdraw.setMoneyamount(moneyAmount);
    withdraw.setState(withdraw.STATE_NORMAL);
    if(moneyAmount.compareTo(BidConst.MONEY_WITHDRAW)>0){
      withdraw.setChargefee(BidConst.MONEY_WITHDRAW_CHARGEFEE_MAX);
    }else {
      withdraw.setChargefee(BidConst.MONEY_WITHDRAW_CHARGEFEE_MIN);
    }
    withdraw.setApplier(UserContext.getCurrent());
    withdraw.setApplytime(new Date());
    withdraw.setRemark("提现申请");
    // 资金
    Account accountInfo = accountServer.getAccountInfoById(UserContext.getCurrent().getId());
    accountInfo.setUsableamount(accountInfo.getUsableamount().subtract(moneyAmount));
    accountInfo.setFreezedamount(accountInfo.getFreezedamount().add(moneyAmount));
    accountServer.updateAccount(accountInfo);
    // 资金流水
    accountflowServer.withdrawalFreezeAccountflow(userinfo.getId(),moneyAmount);
    moneywithdrawMapper.insert(withdraw);
    userinfo.addState(BitStatesUtils.OP_HAS_WITHDRAW_PROCESS);
    userinfoServer.updateUserInfo(userinfo);

  }

  @Override
  public PageResult withdrawalPage(MoneyWithdrawQueryObject qo) {
    int i = moneywithdrawMapper.selectCount(qo);
    List<Moneywithdraw> list = moneywithdrawMapper.selectPage(qo);
    PageResult pageResult = new PageResult();
    pageResult.setData(list);
    pageResult.setCurrentPage(qo.getCurrentPage());
    pageResult.setPageSize(qo.getPageSize());
    pageResult.setTotalCount(i);
    return pageResult;
  }


  /**
   * 提现审核
   * @param id
   * @param state
   * @param remark
   */
  @Override
  public void withdrawalaudit(Long id, Long state, String remark) {
    Moneywithdraw moneywithdraw = moneywithdrawMapper.selectByPrimaryKey(id);
    Userinfo userinfo = userinfoServer.getUserinfoById(moneywithdraw.getApplier().getId());
    Account account = accountServer.getAccountInfoById(moneywithdraw.getApplier().getId());
    //有为审核的提现
    if(userinfo.getHasWithdrawInProcess()){
      // 移除用户审核状态
      userinfo.removeState(BitStatesUtils.OP_HAS_WITHDRAW_PROCESS);

      moneywithdraw.setRemark(remark);
      moneywithdraw.setAuditor(UserContext.getCurrent());
      moneywithdraw.setAudittime(new Date());
      //审核成功
      if(state==moneywithdraw.STATE_AUDIT){
        moneywithdraw.setState(moneywithdraw.STATE_AUDIT);
        // 冻结资金减少
        account.setFreezedamount(account.getFreezedamount().subtract(moneywithdraw.getMoneyamount()));
        //生成流水
        accountflowServer.withdrawalSuccessAccountflow(moneywithdraw.getApplier().getId(),moneywithdraw.getMoneyamount());
      }else {
        moneywithdraw.setState(moneywithdraw.STATE_REFUSE);
        //退款
        account.setFreezedamount(account.getFreezedamount().subtract(moneywithdraw.getMoneyamount()));
        account.setUsableamount(account.getUsableamount().add(moneywithdraw.getMoneyamount()));
        //生成流水
        accountflowServer.withdrawalFailAccountflow(moneywithdraw.getApplier().getId(),moneywithdraw.getMoneyamount());
      }
      moneywithdrawMapper.updateByPrimaryKey(moneywithdraw);
      accountServer.updateAccount(account);
      userinfoServer.updateUserInfo(userinfo);
    }
  }
}
