package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Accountflow;
import com.hl.p2p.query.AccountflowQueryObject;

import java.util.List;

public interface AccountflowMapper {

    int insert(Accountflow record);

    List<Accountflow> selectAll();

    int selectCount(AccountflowQueryObject qo);

    List<Accountflow> selectPage(AccountflowQueryObject qo);
}