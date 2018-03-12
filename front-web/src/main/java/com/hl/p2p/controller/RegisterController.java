package com.hl.p2p.controller;

import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.server.ILoginInfoServer;
import com.hl.p2p.utils.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录注册
 */
@Controller
public class RegisterController {

  @Autowired
  private ILoginInfoServer iLoginInfoServer;

  @RequestMapping("/register")
  public String register() {

    return "register";
  }

  @RequestMapping("/login")
  public String login() {

    return "login";
  }

  @RequestMapping(value = "/register.json", method = RequestMethod.POST)
  @ResponseBody
  public JsonResult doRegister(String username, String password) {
    if (StringUtils.isBlank(username)) {
      return JsonResult.resultError("0000090", "用户名不能为空");
    }
    if (StringUtils.isBlank(password)) {
      return JsonResult.resultError("0000090", "密码不能为空");
    }
    int i = iLoginInfoServer.register(username, password);
    if (i == -1) {
      return JsonResult.resultError("0000090", "用户名已存在");
    } else if (i == 1) {
      return JsonResult.resultError("0000090", "注册成功");
    } else {
      return JsonResult.resultError("0000090", "注册失败");
    }
  }

  @RequestMapping(value = "/login.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult doLogin(HttpServletRequest request, String username, String password){
    if (StringUtils.isBlank(username)) {
      return JsonResult.resultError("0000090", "用户名不能为空");
    }
    if (StringUtils.isBlank(password)) {
      return JsonResult.resultError("0000090", "密码不能为空");
    }
    if(iLoginInfoServer.login(username, password, request.getRemoteAddr(), Logininfo.USER_CLIENT)){
      return JsonResult.resultSuccess("登录成功",null);
    }
    return JsonResult.resultError("0000130", "用户名或密码不正确");

  }


}
