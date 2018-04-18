package com.hl.p2p.server;

import com.hl.p2p.pojo.Systemaccount;

import java.math.BigDecimal;

public interface ISystemaAcountServer {

  void updateSystemaAcount(Systemaccount account);

  Long seveSystemaAcount(BigDecimal account);

}
