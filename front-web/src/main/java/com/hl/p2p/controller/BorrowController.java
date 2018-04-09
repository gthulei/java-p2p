package com.hl.p2p.controller;

import com.hl.p2p.pojo.Bidrequest;
import com.hl.p2p.pojo.Bidrequestaudithistory;
import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.pojo.Userinfo;
import com.hl.p2p.server.*;
import com.hl.p2p.utils.BidConst;
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

import java.math.BigDecimal;
import java.util.List;

@Controller
public class BorrowController {

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IAccountServer accountServer;

  @Autowired
  private IBidrequestServer bidrequestServer;

  @Autowired
  private IRealauthServer realauthServer;

  @Autowired
  private IUserFileServer userFileServer;

  @Autowired
  private IBidrequestaudithistoryServer bidrequestaudithistoryServer;

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


  @RequestMapping(value = "/borrowDes")
  public String borrowDes(@RequestParam("id") Long id, Model model){
    Bidrequest bidrequest = bidrequestServer.get(id);
    // 标地信息
    model.addAttribute("bidRequest",bidrequest);
    // 标用户信息
    model.addAttribute("userInfo",userinfoServer.getUserinfoById(bidrequest.getCreateuser().getId()));
    // 标认证信息
    model.addAttribute("realAuth",realauthServer.getRealauthApplier(bidrequest.getCreateuser().getId()));
    // 风控信息
    model.addAttribute("userFiles",userFileServer.getUserFileByApplierList(bidrequest.getCreateuser().getId()));
    //审核历史
    model.addAttribute("audits",bidrequestaudithistoryServer.getHistoryList());

    if(UserContext.getCurrent()!=null){
      if(UserContext.getCurrent().getId()==bidrequest.getCreateuser().getId()){
        model.addAttribute("self",true);
      }else {
        model.addAttribute("self",false);
      }
      model.addAttribute("account",accountServer.getAccountInfoById(UserContext.getCurrent().getId()));
    }else {
      model.addAttribute("self",false);
    }

    return "borrow_info";
  }

  /**
   * 投标
   * @return
   */
  @RequireLogin
  @RequestMapping(value = "/borrowBid.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult borrowBid(Long bidRequestId,BigDecimal amount){
    try {
      bidrequestServer.borrowBid(bidRequestId,amount);
      return JsonResult.resultSuccess("投标成功");
    }catch (Exception e){
      return JsonResult.resultError("0000019",e.getMessage());
    }

  }

}
