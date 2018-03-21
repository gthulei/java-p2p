package com.hl.p2p.pojo;

import java.util.Date;

public class Realauth {

  public final static int STATE_NORMAL = 0;//正常

  public final static int STATE_AUDIT = 1;//审核中

  public final static int STATE_REFUSE = 2;//审核拒绝

  public final static int STATE_SUCCESS = 3;//认证成功

  private Long id;

  private String realname;//真实姓名

  private int sex = 0; //性别

  private String birthdate;//生日

  private String idnumber;//身份证号码

  private String address;//身份证地址

  private int state;// 审核状态

  private String image1;//身份证正面照

  private String image2;//身份证反面照

  private String remark; //审核时的备注信息

  private Date audittime;  //审核操作时间

  private Date applytime = new Date(); //申请时间

  private Long auditorId;//审核人id

  private Long applierId; // 申请人id

  public String getGender() {
    return this.sex == 0 ? "男" : "女";
  }

  public String getAudit() {
    if (this.state == 0) {
      return "未审核";
    } else if (this.state == 1) {
      return "审核中";
    } else if (this.state == 2) {
      return "审核拒绝";
    } else {
      return "认证成功";
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname == null ? null : realname.trim();
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate == null ? null : birthdate.trim();
  }

  public String getIdnumber() {
    return idnumber;
  }

  public void setIdnumber(String idnumber) {
    this.idnumber = idnumber == null ? null : idnumber.trim();
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getImage1() {
    return image1;
  }

  public void setImage1(String image1) {
    this.image1 = image1 == null ? null : image1.trim();
  }

  public String getImage2() {
    return image2;
  }

  public void setImage2(String image2) {
    this.image2 = image2 == null ? null : image2.trim();
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  public Date getAudittime() {
    return audittime;
  }

  public void setAudittime(Date audittime) {
    this.audittime = audittime;
  }

  public Date getApplytime() {
    return applytime;
  }

  public void setApplytime(Date applytime) {
    this.applytime = applytime;
  }

  public Long getAuditorId() {
    return auditorId;
  }

  public void setAuditorId(Long auditorId) {
    this.auditorId = auditorId;
  }

  public Long getApplierId() {
    return applierId;
  }

  public void setApplierId(Long applierId) {
    this.applierId = applierId;
  }
}