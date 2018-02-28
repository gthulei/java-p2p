package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Bidrequestaudithistory;
import java.util.List;

public interface BidrequestaudithistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bidrequestaudithistory record);

    Bidrequestaudithistory selectByPrimaryKey(Long id);

    List<Bidrequestaudithistory> selectAll();

    int updateByPrimaryKey(Bidrequestaudithistory record);
}