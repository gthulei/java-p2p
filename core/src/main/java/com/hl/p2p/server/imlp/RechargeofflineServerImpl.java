package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.RechargeofflineMapper;
import com.hl.p2p.pojo.Account;
import com.hl.p2p.pojo.Accountflow;
import com.hl.p2p.pojo.Rechargeoffline;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.RechargeQueryObject;
import com.hl.p2p.server.IAccountServer;
import com.hl.p2p.server.IAccountflowServer;
import com.hl.p2p.server.IRechargeofflineServer;
import com.hl.p2p.utils.BidConst;
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

  @Autowired
  private IAccountServer accountServer;

  @Autowired
  private IAccountflowServer accountflowServer;

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

  @Override
  public void updateRechargeoffline(Long id,int state,String remark) {
    Rechargeoffline result = rechargeofflineMapper.selectByPrimaryKey(id);
    //待审核状态
    if(result.getState()==result.STATE_NORMAL){
      result.setState(state);
      result.setAuditor(UserContext.getCurrent());
      result.setAudittime(new Date());
      result.setRemark(remark);
      // 审核通过
      if(state == 1){
        Account accountInfo = accountServer.getAccountInfoById(result.getApplier().getId());
        accountInfo.setUsableamount(accountInfo.getUnreturnamount().add(result.getAmount()));
        accountServer.updateAccount(accountInfo);
        //资金流水
        Accountflow accountflow = new Accountflow();
        accountflow.setAccountactiontype(BidConst.ACCOUNT_ACTIONTYPE_RECHARGE_OFFLINE);
        accountflow.setActiontime(new Date());
        accountflow.setAccountId(accountInfo.getId());
        accountflow.setAmount(result.getAmount());
        accountflow.setBalance(result.getAmount());
        accountflow.setNote("线下充值");
        accountflowServer.saveAccountflow(accountflow);
      }
    }
    rechargeofflineMapper.updateByPrimaryKey(result);
  }
}
