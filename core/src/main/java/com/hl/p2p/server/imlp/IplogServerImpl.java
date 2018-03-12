package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.IplogMapper;
import com.hl.p2p.pojo.Iplog;
import com.hl.p2p.query.IpLogQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.IIplogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录日志
 */
@Service
public class IplogServerImpl implements IIplogServer{


  @Autowired
  private IplogMapper iplogMapper;

  @Override
  public void addIplog(Iplog iplog) {
    iplogMapper.insert(iplog);
  }

  @Override
  public Iplog getLogLastTime(String username) {
    Iplog iplog = iplogMapper.selectByusernameLogLast(username);
    return iplog;
  }

  @Override
  public PageResult queryPage(IpLogQueryObject qo) {
    List<Iplog> iplogs = iplogMapper.selectAll(qo);
    int i = iplogMapper.selectCount(qo);
    PageResult pageResult = new PageResult();
    pageResult.setData(iplogs);
    pageResult.setTotalCount(i);
    pageResult.setPageSize(qo.getPageSize());
    pageResult.setCurrentPage(qo.getCurrentPage());
    return pageResult;
  }
}
