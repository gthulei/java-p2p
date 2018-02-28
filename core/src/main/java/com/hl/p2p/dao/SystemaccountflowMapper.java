package com.hl.p2p.dao;

import com.hl.p2p.pojo.Systemaccountflow;
import java.util.List;

public interface SystemaccountflowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Systemaccountflow record);

    Systemaccountflow selectByPrimaryKey(Long id);

    List<Systemaccountflow> selectAll();

    int updateByPrimaryKey(Systemaccountflow record);
}