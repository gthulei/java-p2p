package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Userbankinfo;
import java.util.List;

public interface UserbankinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Userbankinfo record);

    Userbankinfo selectByPrimaryKey(Long id);

    List<Userbankinfo> selectAll();

    int updateByPrimaryKey(Userbankinfo record);
}