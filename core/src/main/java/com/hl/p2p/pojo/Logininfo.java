package com.hl.p2p.pojo;

public class Logininfo {

    public static final int STATE_NORMAL = 0 ; // 普通用户

    public static final int USER_MANAGER = 0 ;//后台用户
    public static final int USER_CLIENT = 1 ;// 前台用户

    private Long id;

    private String username;

    private String password;

    private int state;

    private int usertype;

    private Boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}