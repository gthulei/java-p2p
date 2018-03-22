package com.hl.p2p.server;

import com.hl.p2p.pojo.Logininfo;

import java.util.List;

public interface ILoginInfoServer {

  int register(String userName, String password);

  boolean adminRegister(String userName, String password);

  boolean login(String ip, String userName, String password, int userType);

  void escLogin();

  boolean selectAdminCount();

  List<Logininfo> selectLogInfoList(String username);
}
