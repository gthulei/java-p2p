package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Accountflow;
import java.util.List;

public interface AccountflowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Accountflow record);

    Accountflow selectByPrimaryKey(Long id);

    List<Accountflow> selectAll();

    int updateByPrimaryKey(Accountflow record);
}