package com.hl.p2p.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class VedioQueryObject extends BaseQuery{

  private int state = -1;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public int getState() {

    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  private String  keyword;

  private Date beginDate ;

  private Date endDate;

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
