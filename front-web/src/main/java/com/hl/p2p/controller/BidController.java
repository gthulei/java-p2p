package com.hl.p2p.controller;

import com.hl.p2p.query.BidQueryObject;
import com.hl.p2p.server.IBidServer;
import com.hl.p2p.utils.RequireLogin;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BidController {

  @Autowired
  private IBidServer bidServe;

  @RequireLogin
  @RequestMapping("/bidList")
  public String bidList(@ModelAttribute("qo") BidQueryObject qo, Model model){
    qo.setUserid(UserContext.getCurrent().getId());
    model.addAttribute("pageResult",bidServe.getBidPage(qo));
    return "bid_list";
  }
}
