package com.hl.p2p.server;

import com.hl.p2p.pojo.Account;
import com.hl.p2p.pojo.Accountflow;
import com.hl.p2p.pojo.Bidrequest;

import java.math.BigDecimal;

public interface IAccountflowServer {

  boolean saveAccountflow(Accountflow accountflow);
  
  boolean createBidAccountflow(BigDecimal amount, Account account, Long bidRequestId);

  boolean createBorrowAccountflow(Account account,Bidrequest bidrequest);

  boolean createBorrowChargeFeeAccountflow(Account account,Bidrequest bidrequest,BigDecimal ChargeFee);

  boolean bidFreezeAccountflow(Account account);

  boolean bidEscAccountflow(Account account,Bidrequest bidrequest);

  boolean withdrawalFreezeAccountflow(Long userId,BigDecimal moneyAmount);
}
