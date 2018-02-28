package com.hl.p2p.dao;

import com.hl.p2p.pojo.Emailactive;
import java.util.List;

public interface EmailactiveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Emailactive record);

    Emailactive selectByPrimaryKey(Long id);

    List<Emailactive> selectAll();

    int updateByPrimaryKey(Emailactive record);
}