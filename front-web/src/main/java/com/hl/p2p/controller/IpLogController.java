package com.hl.p2p.controller;

import com.hl.p2p.query.IpLogQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.IIplogServer;
import cpm.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IpLogController {

  @Autowired
  private IIplogServer iIplogServer;

  @RequestMapping("/ipLog")
  public String ipLog(@ModelAttribute("qo") IpLogQueryObject qo, Model model){
    qo.setUsername(UserContext.getCurrent().getUsername());
    PageResult result = iIplogServer.queryPage(qo);
    model.addAttribute("pageResult",result);
    return "iplog_list";
  }

  @RequestMapping(value = "/ipLogPage.json",method = RequestMethod.POST)
  @ResponseBody
  public PageResult query(IpLogQueryObject qo){
    PageResult result = iIplogServer.queryPage(qo);
    return result;
  }





}


