package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Logininfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogininfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);

    int selectCountByUserName(@Param("userName") String userName);

    Logininfo login(@Param("userName") String userName, @Param("passWord") String passWord);
}