package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Rechargeoffline;
import com.hl.p2p.query.RechargeQueryObject;

import java.util.List;

public interface RechargeofflineMapper {

    int insert(Rechargeoffline record);

    List<Rechargeoffline> selectPage(RechargeQueryObject qo);

    int updateByPrimaryKey(Rechargeoffline record);

    Rechargeoffline selectByPrimaryKey(Long id);

    int selectCount(RechargeQueryObject qo);
}