package com.hl.p2p.server;

import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.pojo.Userinfo;

public interface IBidrequestServer {

  boolean apply(Bidrequest bidrequest);

  void update(Bidrequest bidrequest);

  Bidrequest get(Long id);

  boolean canApply(Userinfo user);
}
