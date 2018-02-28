package com.hl.p2p.dao;

import com.hl.p2p.pojo.Userfile;
import java.util.List;

public interface UserfileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Userfile record);

    Userfile selectByPrimaryKey(Long id);

    List<Userfile> selectAll();

    int updateByPrimaryKey(Userfile record);
}