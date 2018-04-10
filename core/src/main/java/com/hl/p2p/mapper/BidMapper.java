package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Bid;

import java.math.BigDecimal;
import java.util.List;

public interface BidMapper {

    int insert(Bid record);

    List<Bid> selectBidList();

    int updateByPrimaryKey(Bid record);

    BigDecimal selectBidSumAmount(long bidrequestid,long userid);
}