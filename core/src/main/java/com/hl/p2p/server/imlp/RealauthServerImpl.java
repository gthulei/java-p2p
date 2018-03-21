package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.RealauthMapper;
import com.hl.p2p.pojo.Realauth;
import com.hl.p2p.server.IRealauthServer;
import com.hl.p2p.server.IUserinfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实名认证
 */
@Service
public class RealauthServerImpl implements IRealauthServer{

  @Autowired
  private RealauthMapper realauthMapper;

  @Autowired
  private IUserinfoServer userinfoServer;

  @Override
  public void addRealauth(Realauth realauth) {
    realauthMapper.insert(realauth);
    userinfoServer.addRealauthId(realauth.getId());
  }

  @Override
  public void updateRealauth(Realauth realauth) {
    realauthMapper.updateByPrimaryKey(realauth);
  }

  @Override
  public Realauth getRealauth(long id) {
    return realauthMapper.selectByPrimaryKey(id);
  }
}
