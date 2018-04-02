package com.hl.p2p.controller;

import com.hl.p2p.query.BidRequestQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.IBidrequestServer;
import com.hl.p2p.utils.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvestController {

  @Autowired
  private IBidrequestServer bidrequestServer;

  @RequireLogin
  @RequestMapping("/invest")
  public String invest(Model model, @ModelAttribute("qo") BidRequestQueryObject qo){
    PageResult result = bidrequestServer.getApplyList(qo);
    model.addAttribute("pageResult",result);
    return "invest";
  }

}
