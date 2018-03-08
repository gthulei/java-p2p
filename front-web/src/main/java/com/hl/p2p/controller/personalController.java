package com.hl.p2p.controller;

import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.server.IAccountServer;
import com.hl.p2p.server.IUserinfoServer;
import cpm.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 个人中心
 */
@Controller
public class personalController {

  @Autowired
  private IAccountServer accountServer;

  @Autowired
  private IUserinfoServer userinfoServer;

  @RequestMapping("/personal")
  public String doPersonal(Model model){
    Logininfo user = UserContext.getCurrent();
    model.addAttribute("userinfo",userinfoServer.getUserinfoById(user.getId()));
    model.addAttribute("account",accountServer.getAccountInfoById(user.getId()));
    return "personal";
  }
}
