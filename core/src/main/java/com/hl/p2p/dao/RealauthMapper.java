package com.hl.p2p.dao;

import com.hl.p2p.pojo.Realauth;
import java.util.List;

public interface RealauthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Realauth record);

    Realauth selectByPrimaryKey(Long id);

    List<Realauth> selectAll();

    int updateByPrimaryKey(Realauth record);
}