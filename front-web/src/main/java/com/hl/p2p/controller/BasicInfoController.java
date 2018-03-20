package com.hl.p2p.controller;

import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.ISystemdictionaryServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicInfoController {

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private ISystemdictionaryServer systemdictionaryServer;

  @RequireLogin
  @RequestMapping("/basicInfo")
  public String basicInfo(Model model){
    Userinfo userinfo = userinfoServer.getUserinfoById(UserContext.getCurrent().getId());
    model.addAttribute("userinfo",userinfo);
    model.addAttribute("educationBackgrounds",systemdictionaryServer.selectSystemdictionaryitemSn("educationBackground"));
    model.addAttribute("incomeGrades",systemdictionaryServer.selectSystemdictionaryitemSn("incomeGrade"));
    model.addAttribute("marriages",systemdictionaryServer.selectSystemdictionaryitemSn("marriage"));
    model.addAttribute("kidCounts",systemdictionaryServer.selectSystemdictionaryitemSn("kidCount"));
    model.addAttribute("houseConditions",systemdictionaryServer.selectSystemdictionaryitemSn("houseCondition"));
    return "userInfo";
  }

  @RequireLogin
  @RequestMapping(value = "/basicInfoSave.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult basicInfoSave(Userinfo userinfo){
    try {
      userinfoServer.saveBaseinfo(userinfo);
      return JsonResult.resultSuccess("更新成功");
    }catch (Exception e){
      return JsonResult.resultError("0000019",e.getMessage());
    }

  }

}
