package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.MoneywithdrawMapper;
import com.hl.p2p.pojo.Moneywithdraw;
import com.hl.p2p.pojo.Realauth;
import com.hl.p2p.pojo.Userbankinfo;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.IMoneywithdrawServer;
import com.hl.p2p.server.IRealauthServer;
import com.hl.p2p.server.IUserbankinfoServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.BidConst;
import com.hl.p2p.utils.BitStatesUtils;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

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
      throw new RuntimeException("最小提醒金额为"+BidConst.MIN_WITHDRAW_AMOUNT);
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
    moneywithdrawMapper.insert(withdraw);
    userinfo.addState(BitStatesUtils.OP_HAS_WITHDRAW_PROCESS);
    userinfoServer.updateUserInfo(userinfo);
  }
}