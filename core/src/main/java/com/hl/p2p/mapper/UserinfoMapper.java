package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Userinfo;

public interface UserinfoMapper {

    int insert(Userinfo record);

    Userinfo selectByPrimaryKey(Long id);


    int updateByPrimaryKey(Userinfo record);
}