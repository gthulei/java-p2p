package com.hl.p2p.server;
/**
 * 登录注相关服务
 */
public interface ILoginInfoServer {

  int register(String userName, String password);

  boolean login (String userName, String password);
}
