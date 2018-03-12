package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.LogininfoMapper;
import com.hl.p2p.pojo.Account;
import com.hl.p2p.pojo.Iplog;
import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.IAccountServer;
import com.hl.p2p.server.IIplogServer;
import com.hl.p2p.server.ILoginInfoServer;
import com.hl.p2p.server.IUserinfoServer;
import cpm.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * 登录注相关服务
 */
@Service
public class LoginInfoServerImpl implements ILoginInfoServer {

  @Autowired
  private LogininfoMapper logininfoMapper;

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IAccountServer accountServer;

  @Autowired
  private IIplogServer iIplogServer;

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
        //userinfo
        Userinfo userinfo = new Userinfo();
        userinfo.setId(logininfo.getId());
        userinfo.setRealname(userName);
        userinfoServer.addUserinfo(userinfo);
        //account
        Account account = new Account();
        account.setId(logininfo.getId());
        accountServer.addAccount(account);
        return 1;
      }else {
        return 2;
      }
    }
    return -1;
  }

  // 登录
  @Override
  public boolean login(String userName, String password, String ip, int userType) {
    Logininfo login = logininfoMapper.login(userName, DigestUtils.md5DigestAsHex(password.getBytes()));
    int i = logininfoMapper.selectCountByUserName(userName);
    Iplog iplog = new Iplog();
    iplog.setIp(ip);
    iplog.setUsername(userName);
    iplog.setLogintype(userType);
    iplog.setLogintime(new Date());
    if(login != null){
      // 存Session
       if (i > 0){
         iplog.setLoginstate(Iplog.LOG_SUCCESS);
         iIplogServer.addIplog(iplog);
       }
      UserContext.putCurrent(login);
      return true;
    }
    if (i > 0){
      iplog.setLoginstate(Iplog.LOG_ERROR);
      iIplogServer.addIplog(iplog);
    }

    return false;
  }

}