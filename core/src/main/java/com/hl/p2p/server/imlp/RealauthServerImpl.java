package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.RealauthMapper;
import com.hl.p2p.pojo.Realauth;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.RealauthQueryObject;
import com.hl.p2p.server.IRealauthServer;
import com.hl.p2p.server.IUserinfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    userinfoServer.addRealauthId(realauth.getId(),realauth.getIdnumber());
  }

  @Override
  public void updateRealauth(Realauth realauth) {
    realauthMapper.updateByPrimaryKey(realauth);
  }

  @Override
  public Realauth getRealauth(long id) {
    return realauthMapper.selectById(id);
  }

  @Override
  public Realauth getRealauthByid(long id) {
    return realauthMapper.selectById(id);
  }


  // 后台管理
  @Override
  public PageResult getRealauthPage(RealauthQueryObject qo) {
    int i = realauthMapper.selectCount(qo);
    List<Realauth> realauths = realauthMapper.selectPage(qo);
    PageResult result = new PageResult();
    result.setCurrentPage(qo.getCurrentPage());
    result.setPageSize(qo.getPageSize());
    result.setTotalCount(i);
    result.setData(realauths);
    return result;
  }

  @Override
  public Realauth getRealauthApplier(Long applierId) {
    return realauthMapper.selectByApplierId(applierId);
  }
}
