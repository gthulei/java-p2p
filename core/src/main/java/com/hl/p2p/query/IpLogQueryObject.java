package com.hl.p2p.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 分页参数
 */
public class IpLogQueryObject extends BaseQuery{

  private Date beginDate ;

  private Date endDate;

  private int userType = -1;

  private String username;

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

  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
  }

  public String getUsername() {
    return username;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
