package com.hl.p2p.controller;

import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.pojo.Realauth;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.IRealauthServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.FileUploadUtil;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class RealAuthController {

  @Autowired
  private IRealauthServer realauthServer;

  @Autowired
  private IUserinfoServer userinfoServer;

  @RequireLogin
  @RequestMapping("/realAuth")
  public String realAuth(Model model) {
    Logininfo logininfo = UserContext.getCurrent();
    Userinfo userinfo = userinfoServer.getUserinfoById(logininfo.getId());
    model.addAttribute("user", logininfo);
    if(userinfo.getRealauthid() !=null){
      boolean b = false;
      if(userinfo.getIsRealAuth()){
        b = true;
      }
      model.addAttribute("realAuth",realauthServer.getRealauth(userinfo.getRealauthid()));
      model.addAttribute("auditing",b);

      return "realAuth_result";
    }
    return "realAuth";
  }

  @RequireLogin
  @RequestMapping(value = "/realAuthSave.json", method = RequestMethod.POST)
  @ResponseBody
  public JsonResult fildUpload(Realauth realauth, @RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
    try {
      List<String> list = FileUploadUtil.fileUpload(file, request);
      realauth.setImage1(list.get(0));
      realauth.setImage2(list.get(1));
      realauth.setApplierId(UserContext.getCurrent().getId());
      realauth.setState(realauth.STATE_NORMAL);
      realauthServer.addRealauth(realauth);
      return JsonResult.resultSuccess("保存成功");
    } catch (Exception e) {
      return JsonResult.resultError("0000019", e.getMessage());
    }
  }
}
