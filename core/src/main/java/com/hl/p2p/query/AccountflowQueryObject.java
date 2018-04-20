package com.hl.p2p.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AccountflowQueryObject extends BaseQuery{
  private Date beginDate ;

  private Date endDate;

  private Long accountid;

  public Long getAccountid() {
    return accountid;
  }

  public void setAccountid(Long accountid) {
    this.accountid = accountid;
  }

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
}
