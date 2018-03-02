package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.LogininfoMapper;
import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.server.ILoginInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginInfoServerImpl implements ILoginInfoServer {

  @Autowired
  private LogininfoMapper logininfoMapper;

  // 注册
  @Override
  public int register(String userName, String password) {
    // 用户名是否存在
    int i = logininfoMapper.selectCountByUserName(userName);
    if (i <= 0){
      Logininfo logininfo = new Logininfo();
      logininfo.setUsername(userName);
      logininfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
      logininfo.setState(logininfo.STATE_NORMAL);
      logininfo.setUsertype(logininfo.USER_CLIENT);
      int user = logininfoMapper.insert(logininfo);
      if(user > 0){
        return 1;
      }else {
        return 2;
      }
    }
    return -1;
  }

  // 登录
  @Override
  public boolean login(String userName, String password) {
    Logininfo login = logininfoMapper.login(userName, DigestUtils.md5DigestAsHex(password.getBytes()));
    if(login != null){
      return true;
    }
    return false;
  }

}