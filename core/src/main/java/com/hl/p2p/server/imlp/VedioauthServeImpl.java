package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.VedioauthMapper;
import com.hl.p2p.pojo.Vedioauth;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.VedioQueryObject;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.server.IVedioauthServer;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 视频认证
 */
@Service
public class VedioauthServeImpl implements IVedioauthServer{

  @Autowired
  private VedioauthMapper vedioauthMapper;

  @Autowired
  private IUserinfoServer userinfoServer;

  @Override
  public boolean addVedioauth(Vedioauth vedioauth) {
    Vedioauth vo = new Vedioauth();
    vo.setApplytime(new Date());
    vo.setAudittime(new Date());
    vo.setApplierId(vedioauth.getApplierId());
    vo.setAuditorId(UserContext.getCurrent().getId());
    vo.setRemark(vedioauth.getRemark());
    vo.setState(vedioauth.getState());
    userinfoServer.vedioauth(vedioauth);
    return vedioauthMapper.insert(vo)>0;
  }

  @Override
  public PageResult getVedioPage(VedioQueryObject qo) {
    int i = vedioauthMapper.selectCount(qo);
    List<Vedioauth> page = vedioauthMapper.selectVedioPage(qo);
    PageResult pageResult = new PageResult();
    pageResult.setCurrentPage(qo.getCurrentPage());
    pageResult.setPageSize(qo.getPageSize());
    pageResult.setTotalCount(i);
    pageResult.setData(page);
    return pageResult;
  }
}
