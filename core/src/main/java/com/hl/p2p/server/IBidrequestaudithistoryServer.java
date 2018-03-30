package com.hl.p2p.server;

import com.hl.p2p.pojo.Bidrequestaudithistory;

import java.util.List;

public interface IBidrequestaudithistoryServer {

  List<Bidrequestaudithistory> getHistoryList();

  void add(Bidrequestaudithistory b);
}
