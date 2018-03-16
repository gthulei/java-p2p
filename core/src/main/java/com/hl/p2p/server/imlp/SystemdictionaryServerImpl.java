package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.SystemdictionaryMapper;
import com.hl.p2p.pojo.Systemdictionary;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.SystemdictionaryQueryObject;
import com.hl.p2p.server.ISystemdictionaryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户字典
 */
@Service
public class SystemdictionaryServerImpl implements ISystemdictionaryServer{

  @Autowired
  private SystemdictionaryMapper systemdictionaryMapper;

  @Override
  public boolean saveSystemdictionary(Systemdictionary system) {
    int i = systemdictionaryMapper.selectByPrimarySn(system.getSn());
    if(i > 0){
      systemdictionaryMapper.updateByPrimaryKey(system);
      return true;
    }
    return systemdictionaryMapper.insert(system) > 0;
  }

  @Override
  public PageResult selectSystemdictionary(SystemdictionaryQueryObject qo) {
    int i = systemdictionaryMapper.selectByCount(qo.getKeyword());
    List<Systemdictionary> list = systemdictionaryMapper.querySystemdictionaryList(qo);
    PageResult pageResult = new PageResult();
    pageResult.setCurrentPage(qo.getCurrentPage());
    pageResult.setPageSize(qo.getPageSize());
    pageResult.setTotalCount(i);
    pageResult.setData(list);
    return pageResult;
  }

  @Override
  public boolean systemDictionaryIdDel(String id) {
    return systemdictionaryMapper.deleteByPrimaryKey(id) >0;
  }
}
