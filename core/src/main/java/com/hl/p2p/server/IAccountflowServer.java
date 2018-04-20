package com.hl.p2p.server;

import com.hl.p2p.pojo.Account;
import com.hl.p2p.pojo.Accountflow;
import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.query.AccountflowQueryObject;
import com.hl.p2p.query.PageResult;

import java.math.BigDecimal;

public interface IAccountflowServer {

  boolean saveAccountflow(Accountflow accountflow);
  
  boolean createBidAccountflow(BigDecimal amount, Account account, Long bidRequestId);

  boolean createBorrowAccountflow(Account account,Bidrequest bidrequest);

  boolean createBorrowChargeFeeAccountflow(Account account,Bidrequest bidrequest,BigDecimal ChargeFee);

  boolean bidFreezeAccountflow(Account account);

  boolean bidEscAccountflow(Account account,Bidrequest bidrequest);

  boolean withdrawalFreezeAccountflow(Long userId,BigDecimal moneyAmount);

  boolean withdrawalSuccessAccountflow(Long userId,BigDecimal moneyAmount);

  boolean withdrawalFailAccountflow(Long userId,BigDecimal moneyAmount);

  PageResult getaccountflowPage(AccountflowQueryObject qo);
}
