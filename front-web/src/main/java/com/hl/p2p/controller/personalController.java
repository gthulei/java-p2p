package com.hl.p2p.controller;

import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.server.IAccountServer;
import com.hl.p2p.server.IIplogServer;
import com.hl.p2p.server.ISendVerifyCodeServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.RequireLogin;
import com.hl.p2p.utils.UserContext;
import com.hl.p2p.utils.ValidUtils;
import com.hl.p2p.vo.VerifyCodeVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 个人中心
 */
@Controller
public class personalController {

  @Autowired
  private IAccountServer accountServer;

  @Autowired
  private IUserinfoServer userinfoServer;

  @Autowired
  private IIplogServer iIplogServer;

  @Autowired
  private ISendVerifyCodeServer sendVerifyCodeServer;

  @RequireLogin
  @RequestMapping("/personal")
  public String doPersonal(Model model){
    Logininfo user = UserContext.getCurrent();
    model.addAttribute("userinfo",userinfoServer.getUserinfoById(user.getId()));
    model.addAttribute("account",accountServer.getAccountInfoById(user.getId()));
    model.addAttribute("lasetTime",iIplogServer.getLogLastTime(user.getUsername()));
    return "personal";
  }

  @RequestMapping(value = "/bindPhone.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult bindPhone(VerifyCodeVo vo){
    if (!ValidUtils.isMobile(vo.getPhoneNumber())){
      return JsonResult.resultError("0000019","手机号码格式不正确");
    }
    if (StringUtils.isBlank(vo.getCode())){
      return JsonResult.resultError("0000019","短信验证码不能为空");
    }
    try {
      vo.setSendTime(UserContext.getVerifyCode().getSendTime());
      sendVerifyCodeServer.isVerifyCode(vo);
      userinfoServer.bindPhone(vo.getPhoneNumber());
      return JsonResult.resultSuccess("绑定成功");
    }catch (Exception e){
      return JsonResult.resultError("0000050",e.getMessage());
    }
  }
}
