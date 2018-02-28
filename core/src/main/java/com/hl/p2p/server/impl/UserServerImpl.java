package com.hl.p2p.server.impl;

import com.hl.p2p.mapper.UserinfoMapper;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpl implements UserServer{

 @Autowired
 UserinfoMapper userinfoMapper;

  @Override
  public Userinfo getUserInfoById(Long id) {
    Userinfo userinfo = userinfoMapper.selectByPrimaryKey(id);
    return userinfo;
  }
}
