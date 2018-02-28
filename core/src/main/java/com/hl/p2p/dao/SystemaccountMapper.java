package com.hl.p2p.dao;

import com.hl.p2p.pojo.Systemaccount;
import java.util.List;

public interface SystemaccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Systemaccount record);

    Systemaccount selectByPrimaryKey(Long id);

    List<Systemaccount> selectAll();

    int updateByPrimaryKey(Systemaccount record);
}