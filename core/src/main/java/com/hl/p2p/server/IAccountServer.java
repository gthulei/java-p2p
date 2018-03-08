package com.hl.p2p.server;

import com.hl.p2p.pojo.Account;

public interface IAccountServer {

  void updateAccount(Account account);

  boolean addAccount(Account account);

  Account getAccountInfoById(long id);

}
