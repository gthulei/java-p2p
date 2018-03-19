package com.hl.p2p.admin.listener;

import com.hl.p2p.server.ILoginInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * spring 启动加载
 */
@Component
public class InitAdminListening implements ApplicationListener<ContextRefreshedEvent>{

  @Autowired
  private ILoginInfoServer loginInfoServer;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (loginInfoServer.selectAdminCount()){
      loginInfoServer.adminRegister("admin", "123456");
    }
  }
}
