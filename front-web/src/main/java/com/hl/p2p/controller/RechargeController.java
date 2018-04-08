package com.hl.p2p.controller;

import com.hl.p2p.pojo.Rechargeoffline;
import com.hl.p2p.server.ICompanybankServer;
import com.hl.p2p.server.IRechargeofflineServer;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RechargeController {

  @Autowired
  private ICompanybankServer companybankServer;

  @Autowired
  private IRechargeofflineServer rechargeofflineServer;

  @RequireLogin
  @RequestMapping("/recharge")
  public String recharge(Model model){
    model.addAttribute("banks",companybankServer.getCompanybankList());
    return "recharge";
  }

  @RequestMapping(value = "/rechargeSave.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult rechargeSave(Rechargeoffline appry){
    boolean b = rechargeofflineServer.rechargeofflineAppry(appry);
    if(b){
      return JsonResult.resultSuccess("审核中");
    }
    return JsonResult.resultError("0000019","充值失败");
  }

}
