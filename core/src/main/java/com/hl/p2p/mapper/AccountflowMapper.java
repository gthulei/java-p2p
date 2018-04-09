package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Accountflow;
import java.util.List;

public interface AccountflowMapper {

    int insert(Accountflow record);

    List<Accountflow> selectAll();

}