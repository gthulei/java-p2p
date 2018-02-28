package com.hl.p2p.pojo;

import java.util.Date;

public class Emailactive {
    private Long id;

    private Long logininfoId;

    private String email;

    private Date senddate;

    private String uuidcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogininfoId() {
        return logininfoId;
    }

    public void setLogininfoId(Long logininfoId) {
        this.logininfoId = logininfoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    public String getUuidcode() {
        return uuidcode;
    }

    public void setUuidcode(String uuidcode) {
        this.uuidcode = uuidcode == null ? null : uuidcode.trim();
    }
}