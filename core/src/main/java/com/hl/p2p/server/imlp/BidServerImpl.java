package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.BidMapper;
import com.hl.p2p.pojo.Bid;
import com.hl.p2p.server.IBidServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServerImpl implements IBidServer {

  @Autowired
  private BidMapper bidMapper;

  @Override
  public void saveBid(Bid bid) {
    bidMapper.insert(bid);
  }
}
