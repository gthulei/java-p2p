package com.hl.p2p.admin.controller;

import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.server.ILoginInfoServer;
import com.hl.p2p.utils.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

  @Autowired
  private ILoginInfoServer loginInfoServer;

  @RequestMapping("/login")
  public String login(){
    return "login";
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
    boolean login = loginInfoServer.login(username, password, request.getRemoteAddr(),Logininfo.USER_MANAGER);
    if (login){
      return JsonResult.resultSuccess("登录成功");
    }
    return JsonResult.resultError("0000130", "用户名或密码不正确");
  }


}
