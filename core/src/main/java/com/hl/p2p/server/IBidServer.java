package com.hl.p2p.server;

import com.hl.p2p.pojo.Bid;
import com.hl.p2p.query.BidQueryObject;
import com.hl.p2p.query.PageResult;

public interface IBidServer {

  void saveBid(Bid bid);

  PageResult getBidPage(BidQueryObject qo);
}
