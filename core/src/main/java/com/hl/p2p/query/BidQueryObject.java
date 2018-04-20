package com.hl.p2p.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BidQueryObject extends BaseQuery{
  private Date beginDate ;

  private Date endDate;

  private Long userid;

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

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }
}
