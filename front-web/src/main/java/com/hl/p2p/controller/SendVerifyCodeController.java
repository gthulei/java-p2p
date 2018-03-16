package com.hl.p2p.controller;

import com.hl.p2p.server.ISendVerifyCodeServer;
import com.hl.p2p.utils.JsonResult;
import com.hl.p2p.utils.ValidUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.GeneralSecurityException;

@Controller
public class SendVerifyCodeController {

  @Autowired
  private ISendVerifyCodeServer sendVerifyCodeServer;

  @RequestMapping(value = "/sendVerifyCode.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult sendVerifyCode(String phoneNumber){
    if (StringUtils.isBlank(phoneNumber)){
      return JsonResult.resultError("000019","手机号不能为空");
    }
    if(!ValidUtils.isMobile(phoneNumber)){
      return JsonResult.resultError("000019","手机号格式不正确");
    }
    try {
      sendVerifyCodeServer.sendVerifyCode(phoneNumber);
      return JsonResult.resultSuccess("验证码发送成功");
    }catch (Exception e){
      return JsonResult.resultError("000019",e.getMessage());
    }
  }

  @RequestMapping(value = "/sendEmail.json",method = RequestMethod.POST)
  @ResponseBody
  public JsonResult sendEmail(String email) {
    if(StringUtils.isBlank(email)){
      return JsonResult.resultError("0000019","邮箱不能为空");
    }
    if (!ValidUtils.isEmail(email)){
      return JsonResult.resultError("0000019","邮箱格式不正确");
    }
    try {
      sendVerifyCodeServer.sendEmail(email);
      return JsonResult.resultSuccess("发送成功");
    }catch (Exception e){
      return JsonResult.resultError("0000050",e.getMessage());
    }
  }

}
