package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.BidrequestaudithistoryMapper;
import com.hl.p2p.pojo.Bidrequestaudithistory;
import com.hl.p2p.server.IBidrequestaudithistoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审核历史
 */
@Service
public class BidrequestaudithistoryServerImpl implements IBidrequestaudithistoryServer{

  @Autowired
  private BidrequestaudithistoryMapper bidrequestaudithistoryMapper;

  @Override
  public List<Bidrequestaudithistory> getHistoryList() {
    return bidrequestaudithistoryMapper.selectAll();
  }

  @Override
  public void add(Bidrequestaudithistory b) {
    bidrequestaudithistoryMapper.insert(b);
  }
}
