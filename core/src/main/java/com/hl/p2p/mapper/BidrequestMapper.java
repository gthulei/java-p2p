package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Bidrequest;
import java.util.List;

public interface BidrequestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bidrequest record);

    Bidrequest selectByPrimaryKey(Long id);

    List<Bidrequest> selectAll();

    int updateByPrimaryKey(Bidrequest record);
}