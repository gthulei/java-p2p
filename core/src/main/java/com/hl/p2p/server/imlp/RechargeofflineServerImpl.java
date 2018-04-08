package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.RechargeofflineMapper;
import com.hl.p2p.pojo.Rechargeoffline;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.RechargeQueryObject;
import com.hl.p2p.server.IRechargeofflineServer;
import com.hl.p2p.utils.Serialnumber;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 充值相关
 */
@Service
public class RechargeofflineServerImpl implements IRechargeofflineServer {

  @Autowired
  private RechargeofflineMapper rechargeofflineMapper;

  @Override
  public boolean rechargeofflineAppry(Rechargeoffline rechargeoffline) {
    rechargeoffline.setTradetime(new Date());
    rechargeoffline.setTradecode(Serialnumber.Getnum()+""+UserContext.getCurrent().getId());
    rechargeoffline.setApplier(UserContext.getCurrent());
    rechargeoffline.setState(rechargeoffline.STATE_NORMAL);
    return rechargeofflineMapper.insert(rechargeoffline) > 0;
  }

  @Override
  public PageResult getRechargeofflinePage(RechargeQueryObject qo) {
    int i = rechargeofflineMapper.selectCount(qo);
    List<Rechargeoffline> rechargeofflines = rechargeofflineMapper.selectPage(qo);
    PageResult pageResult = new PageResult();
    pageResult.setData(rechargeofflines);
    pageResult.setTotalCount(i);
    pageResult.setPageSize(qo.getPageSize());
    pageResult.setCurrentPage(qo.getCurrentPage());
    return pageResult;
  }
}
