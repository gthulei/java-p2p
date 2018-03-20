package com.hl.p2p.controller;

import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BorrowController {

  @Autowired
  private IUserinfoServer userinfoServer;

  @RequestMapping("/borrow")
  public String borrow(Model model){
    Logininfo user = UserContext.getCurrent();
    Userinfo userinfo =null;
    if(user!=null){
       userinfo = userinfoServer.getUserinfoById(user.getId());
    }
    model.addAttribute("user",user);
    model.addAttribute("userinfo",userinfo);
    return "borrow";
  }
}
