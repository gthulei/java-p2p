package com.hl.p2p.controller;

import com.hl.p2p.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录注册
 */
@Controller
public class RegisterController {

  @RequestMapping("/register")
  public String register() {

    return "register";
  }

  @RequestMapping("/login")
  public String login() {

    return "login";
  }

}
