package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.UserinfoMapper;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.ISendVerifyCodeServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.BitStatesUtils;
import com.hl.p2p.utils.UserContext;
import com.hl.p2p.vo.VerifyCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息
 */
@Service
public class UserinfoServerImpl implements IUserinfoServer{

  @Autowired
  private UserinfoMapper userinfoMapper;

  @Override
  public void updateUserInfo(Userinfo userinfo) {
    int result = userinfoMapper.updateByPrimaryKey(userinfo);
    if( result == 0){
      throw new RuntimeException("乐观锁失败,Account:" + userinfo.getId());
    }
  }

  @Override
  public boolean addUserinfo(Userinfo userinfo) {
    int result = userinfoMapper.insert(userinfo);
    return result > 0;
  }

  @Override
  public Userinfo getUserinfoById(long id) {
    Userinfo userinfo = userinfoMapper.selectByPrimaryKey(id);
    return userinfo;
  }

  @Override
  public boolean bindPhone(String phonenumber) {
    Userinfo user = this.getUserinfoById(UserContext.getCurrent().getId());
    // 判断用户绑定没有
    if(!user.getIsBindPhone()){
      user.setPhonenumber(phonenumber);
      // 绑定状态
      user.addState(BitStatesUtils.OP_BIND_PHONE);
      this.updateUserInfo(user);
      return true;
    }else {
      return false;
    }
  }
}
