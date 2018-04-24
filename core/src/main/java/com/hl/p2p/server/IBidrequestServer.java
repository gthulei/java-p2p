package com.hl.p2p.server;

import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.query.BidRequestQueryObject;
import com.hl.p2p.query.PageResult;

import java.math.BigDecimal;

public interface IBidrequestServer {

  boolean apply(Bidrequest bidrequest);

  void update(Bidrequest bidrequest);

  Bidrequest get(Long id);

  boolean canApply(Userinfo user);

  PageResult getApplyList(BidRequestQueryObject qo);

  void borrowFullAudit(Long id,int state,String remark);

  void borrowBid(Long bidRequestId,BigDecimal amount);

  void borrowFullAuditOne(Long id, int state, String remark);

  void borrowFullAuditTwo(Long id, int state, String remark);

  PageResult getBorrowList(BidRequestQueryObject qo);
}
