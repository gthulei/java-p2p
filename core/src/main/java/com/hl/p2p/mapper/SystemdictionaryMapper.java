package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Systemdictionary;
import com.hl.p2p.query.SystemdictionaryQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemdictionaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Systemdictionary record);

    Systemdictionary selectByPrimaryKey(Long id);

    int selectByPrimarySn(String sn);

    List<Systemdictionary> querySystemdictionaryList(SystemdictionaryQueryObject qo);

    int updateByPrimaryKey(Systemdictionary record);

    int selectByCount(@Param("keyword") String keyword);

    List<Systemdictionary> selectAll();
}