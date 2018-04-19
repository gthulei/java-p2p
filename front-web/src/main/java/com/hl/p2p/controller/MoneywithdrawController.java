package com.hl.p2p.controller;

import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.IAccountServer;
import com.hl.p2p.server.IMoneywithdrawServer;
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

import java.math.BigDecimal;

@Controller
public class MoneywithdrawController {
  
  @Autowired
  private IAccountServer accountServer;
  
  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IUserbankinfoServer userbankinfoServer;

  @Autowired
  private IMoneywithdrawServer moneywithdrawServer;

  @RequireLogin
  @RequestMapping("/moneyWithdraw")
  public String moneyWithdraw(Model model){
    Long userId = UserContext.getCurrent().getId();
    Userinfo userinfo = userinfoServer.getUserinfoById(userId);
    // 实名认证
    if(!userinfo.getIsRealAuth()){
      return "redirect:/realAuth";
    }else {
      model.addAttribute("account",accountServer.getAccountInfoById(userId));
      model.addAttribute("bankInfo",userbankinfoServer.getUserbank(userId));
      model.addAttribute("bindBank",userinfo.getIsBindBank());
      model.addAttribute("hasWithdrawInProcess",userinfo.getHasWithdrawInProcess());
      return "moneyWithdraw_apply";
    }
  }

  @RequireLogin
  @RequestMapping(value = "/moneyWithdrawApply.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult moneyWithdrawApply(BigDecimal moneyAmount){
    try {
      moneywithdrawServer.withdrawalApply(moneyAmount);
      return JsonResult.resultSuccess("成功");
    }catch (Exception e){
      return JsonResult.resultError("0000019",e.getMessage());
    }

  }

}
