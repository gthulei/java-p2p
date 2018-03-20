package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Systemdictionaryitem;
import com.hl.p2p.query.SystemdictionaryQueryObject;

import java.util.List;

public interface SystemdictionaryitemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Systemdictionaryitem record);

    int updateByPrimaryKey(Systemdictionaryitem record);

    int selectCount(SystemdictionaryQueryObject qo);

    int selectByPrimaryKey(long id);

    List<Systemdictionaryitem> querySystemdictionaryitemList(SystemdictionaryQueryObject qo);

    List<Systemdictionaryitem> selectBysn(String sn);

}