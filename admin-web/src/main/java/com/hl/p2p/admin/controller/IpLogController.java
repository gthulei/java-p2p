package com.hl.p2p.admin.controller;

import com.hl.p2p.query.IpLogQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.IIplogServer;
import com.hl.p2p.utils.RequireLogin;
import cpm.hl.p2p.utils.UserContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IpLogController {

  @Autowired
  private IIplogServer iIplogServer;

  @RequireLogin
  @RequestMapping("/ipLog")
  public String ipLog(@ModelAttribute("qo") IpLogQueryObject qo, Model model){
    if(StringUtils.isBlank(qo.getUsername())){
      qo.setUsername(UserContext.getCurrent().getUsername());
    }
    PageResult result = iIplogServer.queryPage(qo);
    model.addAttribute("pageResult",result);
    return "log_list";
  }





}


