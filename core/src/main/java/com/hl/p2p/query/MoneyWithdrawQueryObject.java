package com.hl.p2p.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MoneyWithdrawQueryObject extends BaseQuery{

  private Date beginDate ;

  private Date endDate;

  private int state = -1;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public void setBeginDate(Date beginDate){
    this.beginDate = beginDate ;
  }
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public void setEndDate(Date endDate){
    this.endDate = endDate ;
  }

  public Date getBeginDate() {
    return beginDate;
  }

  public Date getEndDate() {
    return endDate;
  }


  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }
}
