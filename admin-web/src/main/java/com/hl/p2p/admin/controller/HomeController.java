package com.hl.p2p.admin.controller;

import com.hl.p2p.utils.RequireLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequireLogin
  @RequestMapping("/home")
  public String home(){
    return  "main";
  }

}
