package com.hl.p2p.vo;

import java.util.Date;

/**
 * 短信验证码
 */
public class VerifyCodeVo {

  private String phoneNumber;

  private String code;

  private Date sendTime;

  public Date getSendTime() {
    return sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }

  public String getCode() {

    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getPhoneNumber() {

    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
