package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Vedioauth;
import java.util.List;

public interface VedioauthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vedioauth record);

    Vedioauth selectByPrimaryKey(Long id);

    List<Vedioauth> selectAll();

    int updateByPrimaryKey(Vedioauth record);
}