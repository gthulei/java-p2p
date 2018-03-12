package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Iplog;
import com.hl.p2p.query.IpLogQueryObject;

import java.util.List;

public interface IplogMapper {

    int insert(Iplog record);

    Iplog selectByuseName(String username);

    Iplog selectByusernameLogLast(String username);

    int selectCount(IpLogQueryObject qo);

    List<Iplog> selectAll(IpLogQueryObject qo);

}