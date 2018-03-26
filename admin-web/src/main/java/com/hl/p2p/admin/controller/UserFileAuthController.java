package com.hl.p2p.admin.controller;

import com.hl.p2p.pojo.Userfile;
import com.hl.p2p.query.UserFileQueryObject;
import com.hl.p2p.server.IUserFileServer;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserFileAuthController {

  @Autowired
  private IUserFileServer userFileServer;

  @RequireLogin
  @RequestMapping("/userFileAuth")
  public String userFileAuth(@ModelAttribute("qo") UserFileQueryObject qo, Model model){
    model.addAttribute("pageResult",userFileServer.getUserFileList(qo));
    return "userFileAuth";
  }

  @RequireLogin
  @RequestMapping(value = "/getUserFile.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult getUserFileById(Long id){
    Userfile resule = userFileServer.getUserFileById(id);
    return JsonResult.resultSuccess(resule);
  }

  @RequestMapping(value = "/audit.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult audit(Userfile userfile){
    userFileServer.updateUserFileAuth(userfile);
    return JsonResult.resultSuccess("更新成功");
  }
}
