package com.hl.p2p.controller;

import com.hl.p2p.query.BidRequestQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.IBidrequestServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @Autowired
  private IBidrequestServer bidrequestServer;

  @RequestMapping("/index")
  public String index(Model model){
    BidRequestQueryObject qo = new BidRequestQueryObject();
    qo.setQuertState(2);
    qo.setPageSize(5);
    PageResult applyList = bidrequestServer.getApplyList(qo);
    model.addAttribute("bidRequests",applyList.getData());
    return "main";
  }

}
