package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.AccountMapper;
import com.hl.p2p.pojo.Account;
import com.hl.p2p.server.IAccountServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户信息
 */
@Service
public class AccountServerImpl implements IAccountServer{

  @Autowired
  private AccountMapper accountMapper;

  @Override
  public void updateAccount(Account account) {
    int result = accountMapper.insert(account);
    //表示乐观锁失败
    if (result == 0) {
      throw new RuntimeException("乐观锁失败,Account:" + account.getId());
    }
  }

  @Override
  public boolean addAccount(Account account) {
    int result = accountMapper.insert(account);
    return result > 0;
  }

  @Override
  public Account getAccountInfoById(long id) {
    Account result = accountMapper.selectByPrimaryKey(id);
    return result;
  }
}
