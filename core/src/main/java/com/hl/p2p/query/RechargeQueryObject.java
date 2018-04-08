package com.hl.p2p.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RechargeQueryObject extends BaseQuery{
  private Date beginDate ;

  private Date endDate;

  private int state = -1;

  private String tradeCode;

  private int bankId;

  public int getBankId() {
    return bankId;
  }

  public void setBankId(int bankId) {
    this.bankId = bankId;
  }

  public String getTradeCode() {
    return tradeCode;
  }

  public void setTradeCode(String tradeCode) {
    this.tradeCode = tradeCode;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public Date getEndDate() {
    return endDate;
  }

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getBeginDate() {
    return beginDate;
  }

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }
}
