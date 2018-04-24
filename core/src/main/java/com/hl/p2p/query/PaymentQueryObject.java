package com.hl.p2p.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PaymentQueryObject extends BaseQuery{
  private Date beginDate ;

  private Date endDate;

  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
