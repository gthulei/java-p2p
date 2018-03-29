package com.hl.p2p.controller;

import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.IAccountServer;
import com.hl.p2p.server.IBidrequestServer;
import com.hl.p2p.server.IUserFileServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.BidConst;
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
public class BorrowController {

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IAccountServer accountServer;

  @Autowired
  private IBidrequestServer bidrequestServer;

  @RequestMapping("/borrow")
  public String borrow(Model model){
    Logininfo user = UserContext.getCurrent();
    Userinfo userinfo =null;
    if(user!=null){
       userinfo = userinfoServer.getUserinfoById(user.getId());
       model.addAttribute("account", accountServer.getAccountInfoById(user.getId()));
    }
    model.addAttribute("user",user);
    model.addAttribute("userinfo",userinfo);
    return "borrow";
  }

  @RequireLogin
  @RequestMapping("/borrowInfo")
  public String borrowInfo(Model model){
    Userinfo user = userinfoServer.getUserinfoById(UserContext.getCurrent().getId());
    // 判断当前用户是否符合发标条件
    if(bidrequestServer.canApply(user)){
      // 判断用户是否已经有一个借款在审核流程中
      if(user.getHasBidRequestInProcess()){
        return "borrow_apply_result";
      }
      model.addAttribute("bidRequestAmountMin",BidConst.SMALLEST_BIDREQUEST_AMOUNT);
      model.addAttribute("account", accountServer.getAccountInfoById(user.getId()));
      model.addAttribute("currentRateMin",BidConst.SMALLEST_CURRENT_RATE);
      model.addAttribute("currentRateMax",BidConst.MAX_CURRENT_RATE);
      model.addAttribute("minBidAmount",BidConst.SMALLEST_BID_AMOUNT);
      return "borrow_apply";
    }else {
      return  "redirect:/borrow";
    }
  }

  @RequireLogin
  @RequestMapping(value = "/borrowApply.json",method = RequestMethod.POST)
  public String borrowApply(Bidrequest bidrequest){
    // 借款提交
    bidrequestServer.apply(bidrequest);
    return "redirect:/borrowInfo";
  }
}
