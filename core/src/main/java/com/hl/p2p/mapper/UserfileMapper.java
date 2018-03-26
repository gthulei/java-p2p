package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Userfile;
import com.hl.p2p.query.UserFileQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserfileMapper {

    int insetList(@Param("list") List<Userfile> list);

    List<Userfile> selectFiletype(@Param("id") Long id,@Param("type") boolean b);

    int updateByPrimaryKey(Userfile record);

    Userfile selectByPrimaryKey(Long id);

    int selectCount(UserFileQueryObject qo);

    List<Userfile> selectPage(UserFileQueryObject qo);

    int selectScore(Long id);
}