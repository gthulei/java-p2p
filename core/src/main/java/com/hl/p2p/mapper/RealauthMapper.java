package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Realauth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RealauthMapper {

    int insert(Realauth record);

    Realauth selectByPrimaryKey(@Param("applierid") Long id);

    int updateByPrimaryKey(Realauth record);
}