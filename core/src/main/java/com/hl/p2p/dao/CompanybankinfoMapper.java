package com.hl.p2p.dao;

import com.hl.p2p.pojo.Companybankinfo;
import java.util.List;

public interface CompanybankinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Companybankinfo record);

    Companybankinfo selectByPrimaryKey(Long id);

    List<Companybankinfo> selectAll();

    int updateByPrimaryKey(Companybankinfo record);
}