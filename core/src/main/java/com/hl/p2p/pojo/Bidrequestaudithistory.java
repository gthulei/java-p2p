package com.hl.p2p.pojo;

import java.util.Date;

public class Bidrequestaudithistory extends BaseAuth{

    public static  final int PUBLISH_AUDIT = 0 ; //发标审核
    public static  final int FULL_AUDIT1 = 1 ; //满标一审
    public static  final int FULL_AUDIT2 = 2 ; //满标二审

    private Long id;

    private String remark;

    private Date audittime;

    private Date applytime;

    private Long bidrequestid;

    private int audittype;//标的审核

    public String getAuditTypeDisplay(){
        switch (this.audittype) {
            case PUBLISH_AUDIT: return "发标审核";
            case FULL_AUDIT1: return "满标一审";
            case FULL_AUDIT2: return "满标二审";
            default: return "" ;
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

    public Long getBidrequestid() {
        return bidrequestid;
    }

    public void setBidrequestid(Long bidrequestid) {
        this.bidrequestid = bidrequestid;
    }

    public int getAudittype() {
        return audittype;
    }

    public void setAudittype(int audittype) {
        this.audittype = audittype;
    }
}