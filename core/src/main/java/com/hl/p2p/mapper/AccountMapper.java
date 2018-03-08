package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Account;
import java.util.List;

public interface AccountMapper {

    int insert(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Account record);
}