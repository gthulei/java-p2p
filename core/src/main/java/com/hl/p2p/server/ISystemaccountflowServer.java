package com.hl.p2p.server;

import com.hl.p2p.pojo.Bidrequest;

import java.math.BigDecimal;

public interface ISystemaccountflowServer {

   boolean SystemaMgtFeeccountflow(Bidrequest bidrequest, BigDecimal borrowChargeFee,Long systemaccountId);

}
