package com.hl.p2p.pojo;

import java.util.Date;

public class BaseAuth {

  public final static int STATE_NORMAL = 0;//待审核

  public final static int STATE_AUDIT = 1;//审核通过

  public final static int STATE_REFUSE = 2;//审核拒绝

  private Long id;

  private int state;// 审核状态

  private String remark; //审核时的备注信息

  private Date audittime;  //审核操作时间

  private Date applytime = new Date(); //申请时间

  private Long auditorId;//审核人id

  private Long applierId; // 申请人id

  private Logininfo auditor;//审核人

  private Logininfo applier;//申请人


  public Logininfo getAuditor() {
    return auditor;
  }

  public void setAuditor(Logininfo auditor) {
    this.auditor = auditor;
  }

  public Logininfo getApplier() {
    return applier;
  }

  public void setApplier(Logininfo applier) {
    this.applier = applier;
  }

  public BaseAuth() {
  }

  public BaseAuth(int state,Logininfo applier) {
    this.applier = applier;
  }

  public String getAudit() {
    if (this.state == 0) {
      return "待审核";
    } else if (this.state == 1) {
      return "审核通过";
    } else {
      return "审核拒绝";
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }
}
