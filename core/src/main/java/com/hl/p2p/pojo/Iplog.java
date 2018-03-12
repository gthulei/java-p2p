package com.hl.p2p.pojo;

import java.util.Date;

public class Iplog {

    public static final int LOG_SUCCESS = 1;//登录成功

    public static final int LOG_ERROR = 0;//登录失败

    private Long id;

    private String ip;

    private int loginstate;

    private String username;

    private Long logininfoid;

    private int logintype;

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

    public int getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(int loginstate) {
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

    public int getLogintype() {
        return logintype;
    }

    public void setLogintype(int logintype) {
        this.logintype = logintype;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }
}