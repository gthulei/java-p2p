package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.AccountflowMapper;
import com.hl.p2p.pojo.Accountflow;
import com.hl.p2p.server.IAccountflowServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountflowServerImpl implements IAccountflowServer {

  @Autowired
  private AccountflowMapper accountflowMapper;

  @Override
  public boolean saveAccountflow(Accountflow accountflow) {
    return accountflowMapper.insert(accountflow)>0;
  }
}
