package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.SystemaccountMapper;
import com.hl.p2p.pojo.Systemaccount;
import com.hl.p2p.server.ISystemaAcountServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SystemaAcountServerImpl implements ISystemaAcountServer {

  @Autowired
  private SystemaccountMapper systemaccountMapper;

  /**
   * 乐观锁
   * @param account
   */
  @Override
  public void updateSystemaAcount(Systemaccount account) {
    int result = systemaccountMapper.updateByPrimaryKey(account);
    //表示乐观锁失败
    if (result == 0) {
      throw new RuntimeException("乐观锁失败,Account:" + account.getId());
    }
  }

  @Override
  public Long seveSystemaAcount(BigDecimal account) {
    Systemaccount systemaccount = new Systemaccount();
    systemaccount.setCreatedate(new Date());
    systemaccount.setUpdatedate(new Date());
    systemaccount.setTotalbalance(account);
    systemaccountMapper.insert(systemaccount);
    return systemaccount.getId();
  }
}
