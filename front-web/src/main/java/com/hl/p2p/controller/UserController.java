package com.hl.p2p.controller;

import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

  @Autowired
  UserServer userServer;

  @RequestMapping(value="/userInfo/{id}",method= RequestMethod.GET)
  @ResponseBody
  public Userinfo getUserInfo(@PathVariable Long id){
     Userinfo userInfoById = userServer.getUserInfoById(id);
     return  userInfoById;
  }

}
