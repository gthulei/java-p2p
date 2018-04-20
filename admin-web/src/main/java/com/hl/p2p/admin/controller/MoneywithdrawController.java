package com.hl.p2p.admin.controller;

import com.hl.p2p.query.MoneyWithdrawQueryObject;
import com.hl.p2p.server.IMoneywithdrawServer;
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
public class MoneywithdrawController {

  @Autowired
  private IMoneywithdrawServer moneywithdrawServer;

  @RequireLogin
  @RequestMapping("/moneyWithdraw")
  public String moneywithdraw(@ModelAttribute("qo") MoneyWithdrawQueryObject qo, Model model){
    model.addAttribute("pageResult",moneywithdrawServer.withdrawalPage(qo));
    return "moneyWithdraw";
  }

  @RequireLogin
  @RequestMapping(value = "/moneyWithdrawAudit.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult moneyWithdrawAudit(Long id, Long state, String remark){
    try {
      moneywithdrawServer.withdrawalaudit(id,state,remark);
      return JsonResult.resultSuccess("审核成功");
    }catch (Exception e){
      return JsonResult.resultError("0000019",e.getMessage());
    }
  }
}
