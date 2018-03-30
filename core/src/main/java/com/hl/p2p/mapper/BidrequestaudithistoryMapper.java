package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Bidrequestaudithistory;
import java.util.List;

public interface BidrequestaudithistoryMapper {

    int insert(Bidrequestaudithistory record);

    List<Bidrequestaudithistory> selectAll();

    int updateByPrimaryKey(Bidrequestaudithistory record);
}