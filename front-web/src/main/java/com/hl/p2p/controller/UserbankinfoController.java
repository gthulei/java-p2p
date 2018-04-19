package com.hl.p2p.controller;

import com.hl.p2p.pojo.Userbankinfo;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.IUserbankinfoServer;
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
public class UserbankinfoController {

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IUserbankinfoServer userbankinfoServer;


  @RequireLogin
  @RequestMapping("/bankInfo")
  public String bankInfo(Model model){
    Long userid = UserContext.getCurrent().getId();
    Userinfo userinfo= userinfoServer.getUserinfoById(userid);
    model.addAttribute("userinfo",userinfo);
    if(!userinfo.getIsBindBank()){
      return "bankInfo";
    }
    model.addAttribute("bankInfo",userbankinfoServer.getUserbank(userid));
    return "bankInfo_result";
  }

  @RequestMapping(value="/bankInfoSave.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult bankInfoSave(Userbankinfo userbankinfo){
    try {
      boolean b = userbankinfoServer.tiedCard(userbankinfo);
      if(b){
        return JsonResult.resultSuccess("绑卡成功");
      }else {
        return JsonResult.resultError("0000019","已绑卡请勿重复提交");
      }
    }catch (Exception e){
      return JsonResult.resultError("0000019",e.getMessage());
    }
  }

}
