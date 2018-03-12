package com.hl.p2p.server;

import com.hl.p2p.pojo.Iplog;
import com.hl.p2p.query.IpLogQueryObject;
import com.hl.p2p.query.PageResult;

public interface IIplogServer {

  void addIplog(Iplog iplog);

  Iplog getLogLastTime(String username);

  PageResult queryPage(IpLogQueryObject qo);
}
