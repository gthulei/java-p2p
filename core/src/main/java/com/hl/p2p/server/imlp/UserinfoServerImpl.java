package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.UserinfoMapper;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.IUserinfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserinfoServerImpl implements IUserinfoServer{

  @Autowired
  private UserinfoMapper userinfoMapper;

  @Override
  public void updateUserInfo(Userinfo userinfo) {
    int result = userinfoMapper.insert(userinfo);
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
}
