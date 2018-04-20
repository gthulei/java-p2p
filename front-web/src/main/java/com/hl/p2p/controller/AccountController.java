package com.hl.p2p.controller;

import com.hl.p2p.query.AccountflowQueryObject;
import com.hl.p2p.server.IAccountflowServer;
import com.hl.p2p.utils.RequireLogin;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

  @Autowired
  private IAccountflowServer accountflowServer;

  @RequireLogin
  @RequestMapping("/accountFlowList")
  public String accountFlowList(@ModelAttribute("qo") AccountflowQueryObject qo, Model model){
    qo.setAccountid(UserContext.getCurrent().getId());
    model.addAttribute("pageResult",accountflowServer.getaccountflowPage(qo));
    return "accountflow_list";
  }
}
