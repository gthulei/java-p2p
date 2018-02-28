package com.hl.p2p.pojo;

import java.util.Date;

public class Iplog {
    private Long id;

    private String ip;

    private Byte loginstate;

    private String username;

    private Long logininfoid;

    private Byte logintype;

    private Date logintime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Byte getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(Byte loginstate) {
        this.loginstate = loginstate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Long getLogininfoid() {
        return logininfoid;
    }

    public void setLogininfoid(Long logininfoid) {
        this.logininfoid = logininfoid;
    }

    public Byte getLogintype() {
        return logintype;
    }

    public void setLogintype(Byte logintype) {
        this.logintype = logintype;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }
}