package com.hl.p2p.admin.controller;

import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.pojo.Vedioauth;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.VedioQueryObject;
import com.hl.p2p.server.ILoginInfoServer;
import com.hl.p2p.server.IVedioauthServer;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VedioAuthController {

  @Autowired
  private IVedioauthServer vedioauthServer;

  @Autowired
  private ILoginInfoServer loginInfoServer;

  @RequireLogin
  @RequestMapping("/vedioAuth")
  public String vedioAuth(@ModelAttribute("qo") VedioQueryObject qo, Model model){
    PageResult vedioPage = vedioauthServer.getVedioPage(qo);
    model.addAttribute("pageResult",vedioPage);
    return  "vedioAuth";
  }

  @RequestMapping(value = "/vedioAuthAudit.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult vedioAuthAudit(Vedioauth vedioauth){
    try {
      vedioauthServer.addVedioauth(vedioauth);
      return JsonResult.resultSuccess("审核成果");
    }catch (Exception e){
      return JsonResult.resultError("0000019",e.getMessage());
    }
  }

  @RequestMapping(value = "/vedioAuthAutocomplate.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult vedioAuthAutocomplate(String username){
    List<Logininfo> logininfos = loginInfoServer.selectLogInfoList(username);
    return JsonResult.resultSuccess(logininfos);
  }
}
