package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.AccountflowMapper;
import com.hl.p2p.pojo.Account;
import com.hl.p2p.pojo.Accountflow;
import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.server.IAccountflowServer;
import com.hl.p2p.utils.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AccountflowServerImpl implements IAccountflowServer {

  @Autowired
  private AccountflowMapper accountflowMapper;

  @Override
  public boolean saveAccountflow(Accountflow accountflow) {
    return accountflowMapper.insert(accountflow)>0;
  }

  /**
   * 投标账户流水
   * @param amount
   * @param account
   * @param bidRequestId
   * @return
   */
  @Override
  public boolean createBidAccountflow(BigDecimal amount, Account account, Long bidRequestId) {
    Accountflow accountflow = new Accountflow();
    accountflow.setBalance(accountflow.getBalance().subtract(amount));
    accountflow.setFreezed(accountflow.getFreezed().add(amount));
    accountflow.setActiontime(new Date());
    accountflow.setAccountId(account.getId());
    accountflow.setAccountactiontype(BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL);
    accountflow.setNote("投标标bidRequestId"+bidRequestId);
    return accountflowMapper.insert(accountflow) > 0;
  }

  /**
   * 用户借款成功放款流水
   * @param account
   * @param bidrequest
   * @return
   */
  @Override
  public boolean createBorrowAccountflow(Account account, Bidrequest bidrequest) {
    Accountflow accountflow = new Accountflow();
    accountflow.setBalance(accountflow.getBalance().add(bidrequest.getBidrequestamount()));
    accountflow.setActiontime(new Date());
    accountflow.setAccountId(bidrequest.getId());
    accountflow.setAccountactiontype(BidConst.ACCOUNT_ACTIONTYPE_BIDREQUEST_SUCCESSFUL);
    accountflow.setNote("成功借款");
    return accountflowMapper.insert(accountflow) > 0;
  }

  /**
   * 借款手续费
   * @param account
   * @param ChargeFee
   * @return
   */
  @Override
  public boolean createBorrowChargeFeeAccountflow(Account account, Bidrequest bidrequest,BigDecimal ChargeFee) {
    Accountflow accountflow = new Accountflow();
    accountflow.setActiontime(new Date());
    accountflow.setAccountId(bidrequest.getId());
    accountflow.setBalance(accountflow.getBalance().subtract(ChargeFee));
    accountflow.setAccountactiontype(BidConst.ACCOUNT_ACTIONTYPE_CHARGE);
    accountflow.setNote("支付平台管理费");
    return accountflowMapper.insert(accountflow) > 0;
  }

  /**
   * 放款减少投资人冻结金额
   * @return
   */
  @Override
  public boolean bidFreezeAccountflow(Account account) {
    Accountflow accountflow = new Accountflow();
    accountflow.setFreezed(accountflow.getFreezed().subtract(account.getFreezedamount()));
    accountflow.setActiontime(new Date());
    accountflow.setAccountId(account.getId());
    accountflow.setAccountactiontype(BidConst.ACCOUNT_ACTIONTYPE_BID_UNFREEZED);
    accountflow.setNote("放款投标人冻结金额减少");
    return accountflowMapper.insert(accountflow) > 0;
  }

  /**
   * 满标拒绝退款流水
   * @param bidAccount
   * @param bidrequest
   * @return
   */
  @Override
  public boolean bidEscAccountflow(Account bidAccount, Bidrequest bidrequest) {
    Accountflow accountflow = new Accountflow();
    accountflow.setBalance(accountflow.getBalance().add(bidAccount.getUsableamount()));
    accountflow.setFreezed(accountflow.getFreezed().subtract(bidAccount.getFreezedamount()));
    accountflow.setActiontime(new Date());
    accountflow.setAccountId(bidAccount.getId());
    accountflow.setAccountactiontype(BidConst.ACCOUNT_ACTIONTYPE_BID_UNFREEZED);
    accountflow.setNote(bidrequest.getId()+"满标拒绝");
    return accountflowMapper.insert(accountflow) > 0;
  }

  /**
   * 提现资金冻结流水
   * @param userId
   * @param moneyAmount
   * @return
   */
  @Override
  public boolean withdrawalFreezeAccountflow(Long userId, BigDecimal moneyAmount) {
    Accountflow accountflow = new Accountflow();
    accountflow.setBalance(accountflow.getBalance().subtract(moneyAmount));
    accountflow.setFreezed(accountflow.getFreezed().add(moneyAmount));
    accountflow.setActiontime(new Date());
    accountflow.setAccountId(userId);
    accountflow.setAccountactiontype(BidConst.ACCOUNT_ACTIONTYPE_WITHDRAW_FREEZED);
    accountflow.setNote("提现资金冻结流水");
    return accountflowMapper.insert(accountflow) > 0;
  }

}
