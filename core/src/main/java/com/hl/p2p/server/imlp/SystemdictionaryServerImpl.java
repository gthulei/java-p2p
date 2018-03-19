package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.SystemdictionaryMapper;
import com.hl.p2p.mapper.SystemdictionaryitemMapper;
import com.hl.p2p.pojo.Systemdictionary;
import com.hl.p2p.pojo.Systemdictionaryitem;
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

  @Autowired
  private SystemdictionaryitemMapper systemdictionaryitemMapper;

  @Override
  public boolean saveSystemdictionary(Systemdictionary system) {
    // 判断是否存在
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

  @Override
  public List<Systemdictionary> getSystemdictionary() {
    return systemdictionaryMapper.selectAll();
  }


  @Override
  public PageResult selectSystemdictionaryitemList(SystemdictionaryQueryObject qo) {
    int i = systemdictionaryitemMapper.selectCount(qo);
    List<Systemdictionaryitem> result = systemdictionaryitemMapper.querySystemdictionaryitemList(qo);
    PageResult pageResult = new PageResult();
    pageResult.setCurrentPage(qo.getCurrentPage());
    pageResult.setPageSize(qo.getPageSize());
    pageResult.setTotalCount(i);
    pageResult.setData(result);
    return pageResult;
  }

  @Override
  public boolean saveSystemdictionaryItem(Systemdictionaryitem system) {
    // 判断是否存在
    if (system.getId()!=null){
      int i = systemdictionaryitemMapper.selectByPrimaryKey(system.getId());
      if(i > 0){
        systemdictionaryitemMapper.updateByPrimaryKey(system);
        return true;
      }
    }
    return systemdictionaryitemMapper.insert(system) > 0;
  }

  @Override
  public boolean systemDictionaryItemIdDel(long id) {
    return systemdictionaryitemMapper.deleteByPrimaryKey(id) >0;
  }

}
