package com.hl.p2p.server;

import com.hl.p2p.pojo.Userinfo;

public interface IUserinfoServer {

  void updateUserInfo(Userinfo userinfo);

  boolean addUserinfo(Userinfo userinfo);

  Userinfo getUserinfoById(long id);

  boolean bindPhone(String phonenumber);

  void bindEmail(String uuid);
}
