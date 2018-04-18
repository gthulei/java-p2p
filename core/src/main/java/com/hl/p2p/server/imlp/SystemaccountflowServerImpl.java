package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.SystemaccountflowMapper;
import com.hl.p2p.pojo.Systemaccountflow;
import com.hl.p2p.server.ISystemaccountflowServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemaccountflowServerImpl implements ISystemaccountflowServer {

  @Autowired
  private SystemaccountflowMapper systemaccountflowMapper;

  @Override
  public boolean seveSystemaccountflow(Systemaccountflow systemaccountflow) {
    return systemaccountflowMapper.insert(systemaccountflow) > 0;
  }
}
