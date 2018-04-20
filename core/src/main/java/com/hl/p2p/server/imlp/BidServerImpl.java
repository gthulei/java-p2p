package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.BidMapper;
import com.hl.p2p.pojo.Bid;
import com.hl.p2p.query.BidQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.IBidServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServerImpl implements IBidServer {

  @Autowired
  private BidMapper bidMapper;

  @Override
  public void saveBid(Bid bid) {
    bidMapper.insert(bid);
  }

  @Override
  public PageResult getBidPage(BidQueryObject qo) {
    int i = bidMapper.selectCount(qo);
    List<Bid> bids = bidMapper.selectPage(qo);
    PageResult pageResult = new PageResult();
    pageResult.setCurrentPage(qo.getCurrentPage());
    pageResult.setData(bids);
    pageResult.setTotalCount(i);
    pageResult.setPageSize(qo.getPageSize());
    return pageResult;
  }
}
