package com.hl.p2p.server;

public interface ILoginInfoServer {

  int register(String userName, String password);

  boolean login(String ip, String userName, String password, int userType);
}
