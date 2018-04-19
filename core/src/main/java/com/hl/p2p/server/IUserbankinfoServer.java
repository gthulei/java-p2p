package com.hl.p2p.server;

import com.hl.p2p.pojo.Userbankinfo;

public interface IUserbankinfoServer {

 boolean tiedCard(Userbankinfo userbankinfo);

 Userbankinfo getUserbank(Long id);
}
