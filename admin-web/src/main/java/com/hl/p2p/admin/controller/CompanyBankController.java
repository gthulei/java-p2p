package com.hl.p2p.admin.controller;

import com.hl.p2p.pojo.Companybankinfo;
import com.hl.p2p.query.CompanybankQueryObject;
import com.hl.p2p.server.ICompanybankServer;
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
public class CompanyBankController {

  @Autowired
  private ICompanybankServer companybankServer;

  @RequireLogin
  @RequestMapping("/companyBanklist")
  public String companyBank(@ModelAttribute("qo") CompanybankQueryObject qo, Model model){
    model.addAttribute("pageResult",companybankServer.getCompanybankPage(qo));
    return "companyBanklist";
  }

  @RequestMapping(value="/companyBankUpdate.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult seveCompanyBank(Companybankinfo bank){
    boolean b = companybankServer.addCompanybank(bank);
    if(b){
      return JsonResult.resultSuccess("成功");
    }
    return JsonResult.resultError("0000019","添加失败");
  }

}
