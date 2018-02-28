package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Rechargeoffline;
import java.util.List;

public interface RechargeofflineMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Rechargeoffline record);

    Rechargeoffline selectByPrimaryKey(Long id);

    List<Rechargeoffline> selectAll();

    int updateByPrimaryKey(Rechargeoffline record);
}