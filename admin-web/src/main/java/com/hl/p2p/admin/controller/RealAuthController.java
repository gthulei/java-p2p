package com.hl.p2p.admin.controller;

import com.hl.p2p.pojo.Realauth;
import com.hl.p2p.query.RealauthQueryObject;
import com.hl.p2p.server.IRealauthServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RealAuthController {

  @Autowired
  private IRealauthServer realauthServer;

  @Autowired
  private IUserinfoServer userinfoServer;

  @RequireLogin
  @RequestMapping("/realAuth")
  public String realAuth(@ModelAttribute("qo") RealauthQueryObject qo, Model model){
    model.addAttribute("pageResult",realauthServer.getRealauthPage(qo));
    return "realAuth";
  }

  @RequireLogin
  @RequestMapping(value = "/getRealAuthById.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult realAuthById(long id){
    Realauth result = realauthServer.getRealauthByid(id);
    return JsonResult.resultSuccess(result);
  }

  @RequireLogin
  @RequestMapping(value = "/realAuthAudit.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult realAuthAudit(Realauth realauth){
    try {
      userinfoServer.bindRealauth(realauth);
      return JsonResult.resultSuccess("审核中请稍后");
    }catch (Exception e){
      return JsonResult.resultError("0000019",e.getMessage());
    }
  }

}
