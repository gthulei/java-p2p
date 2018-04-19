package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.SystemaccountflowMapper;
import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.pojo.Systemaccountflow;
import com.hl.p2p.server.ISystemaccountflowServer;
import com.hl.p2p.utils.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SystemaccountflowServerImpl implements ISystemaccountflowServer {

  @Autowired
  private SystemaccountflowMapper systemaccountflowMapper;

  @Override
  public boolean SystemaMgtFeeccountflow(Bidrequest bidrequest, BigDecimal borrowChargeFee,Long systemaccountId) {
    Systemaccountflow systemaccountflow = new Systemaccountflow();
    systemaccountflow.setAmount(systemaccountflow.getAmount().add(borrowChargeFee));
    systemaccountflow.setBalance(systemaccountflow.getBalance().add(borrowChargeFee));
    systemaccountflow.setNote("借款管理费");
    systemaccountflow.setTargetuserId(bidrequest.getCreateuser().getId());
    systemaccountflow.setAccountactiontype(BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_MANAGE_CHARGE);
    systemaccountflow.setSystemaccountId(systemaccountId);
    return systemaccountflowMapper.insert(systemaccountflow)>0;
  }
}
