package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.query.BidRequestQueryObject;

import java.util.List;

public interface BidrequestMapper {

    int insert(Bidrequest record);

    Bidrequest selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Bidrequest record);

    List<Bidrequest> selectPage(BidRequestQueryObject qo);

    int selectCount();
}