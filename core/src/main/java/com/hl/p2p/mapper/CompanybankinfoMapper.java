package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Companybankinfo;
import com.hl.p2p.query.CompanybankQueryObject;

import java.util.List;

public interface CompanybankinfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Companybankinfo record);

    int updateByPrimaryKey(Companybankinfo record);

    List<Companybankinfo> selectCompanybankPage(CompanybankQueryObject qo);

    int selectCount();
}