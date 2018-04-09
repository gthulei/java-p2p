package com.hl.p2p.server;

import com.hl.p2p.pojo.Rechargeoffline;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.RechargeQueryObject;

public interface IRechargeofflineServer {

  boolean rechargeofflineAppry(Rechargeoffline rechargeoffline);

  PageResult getRechargeofflinePage(RechargeQueryObject qo);

  void updateRechargeoffline(Long id,int state,String remark);
}
