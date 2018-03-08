package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Logininfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogininfoMapper {

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Logininfo record);

    int selectCountByUserName(@Param("userName") String userName);

    Logininfo login(@Param("userName") String userName, @Param("passWord") String passWord);
}