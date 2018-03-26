package com.hl.p2p.server;

import com.hl.p2p.pojo.Realauth;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.pojo.Vedioauth;

public interface IUserinfoServer {

  void updateUserInfo(Userinfo userinfo);

  boolean addUserinfo(Userinfo userinfo);

  Userinfo getUserinfoById(long id);

  boolean bindPhone(String phonenumber);

  void bindEmail(String uuid);

  void saveBaseinfo(Userinfo userinfo);

  void addRealauthId(long realauthid ,String idCard);

  void bindRealauth(Realauth realauth);

  void vedioauth(Vedioauth vo);

  void  updateScore(Long id,Integer score);
}
