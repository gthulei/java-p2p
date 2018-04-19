package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.UserbankinfoMapper;
import com.hl.p2p.pojo.Realauth;
import com.hl.p2p.pojo.Userbankinfo;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.IRealauthServer;
import com.hl.p2p.server.IUserbankinfoServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.BitStatesUtils;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 绑卡相关
 */
@Service
public class UserbankinfoServerImpl implements IUserbankinfoServer {

  @Autowired
  private UserbankinfoMapper userbankinfoMapper;

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IRealauthServer realauthServer;

  @Override
  public boolean tiedCard(Userbankinfo userbankinfo) {
    Userinfo userinfo = userinfoServer.getUserinfoById(UserContext.getCurrent().getId());
    Realauth realauth = realauthServer.getRealauth(userinfo.getRealauthid());
    // 已实名未绑卡
    if(userinfo.getIsRealAuth() && !userinfo.getIsBindBank()){
      userbankinfo.setAccountname(realauth.getRealname());
      userbankinfo.setId(UserContext.getCurrent().getId());
      userbankinfo.setLogininfoId(UserContext.getCurrent().getId());
      userbankinfoMapper.insert(userbankinfo);
      //修改用户绑定银行卡的状态码
      userinfo.addState(BitStatesUtils.OP_HAS_BIND_BANK);
      userinfoServer.updateUserInfo(userinfo);
      return true;
    }
    return false;
  }

  @Override
  public Userbankinfo getUserbank(Long id) {
    return userbankinfoMapper.selectByPrimaryKey(id);
  }
}
