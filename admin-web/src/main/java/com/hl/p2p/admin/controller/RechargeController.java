package com.hl.p2p.admin.controller;

import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.RechargeQueryObject;
import com.hl.p2p.server.ICompanybankServer;
import com.hl.p2p.server.IRechargeofflineServer;
import com.hl.p2p.utils.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RechargeController {

  @Autowired
  private ICompanybankServer companybankServer;

  @Autowired
  private IRechargeofflineServer rechargeofflineServer;

  @RequireLogin
  @RequestMapping("/rechargeOffline")
  public String rechargeOffline(@ModelAttribute("qo")RechargeQueryObject qo, Model model){
    model.addAttribute("banks",companybankServer.getCompanybankList());
    PageResult rechargeofflinePage = rechargeofflineServer.getRechargeofflinePage(qo);

    model.addAttribute("pageResult",rechargeofflinePage);
    return "rechargeOffline";
  }
}
