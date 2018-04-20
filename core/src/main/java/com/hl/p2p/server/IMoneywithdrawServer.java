package com.hl.p2p.server;

import com.hl.p2p.pojo.Moneywithdraw;
import com.hl.p2p.query.MoneyWithdrawQueryObject;
import com.hl.p2p.query.PageResult;

import java.math.BigDecimal;

public interface IMoneywithdrawServer {

  void withdrawalApply(BigDecimal moneyAmount);

  PageResult withdrawalPage(MoneyWithdrawQueryObject qo);

  void withdrawalaudit(Long id, Long state, String remark);

}
