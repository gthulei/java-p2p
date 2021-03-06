package com.hl.p2p.pojo;

import com.hl.p2p.utils.BitStatesUtils;

public class Userinfo {
    private Long id;

    private Integer version;// 版本号

    private long bitstate = 0;// 用户认证状态码

    private String realname;//用户名

    private String idnumber;//身份证号

    private String phonenumber;//手机号

    private Integer authscore; // 用户的风控总分数

    private Long realauthid; //用户实名认证

    private String email;//邮箱

    private Systemdictionaryitem incomegrade; // 收入

    private Systemdictionaryitem marriage;// 婚姻情况

    private Systemdictionaryitem kidcount; // 子女情况

    private Systemdictionaryitem educationbackground; // 学历

    private Systemdictionaryitem housecondition;// 住房

    // 判断是否已经绑定了手机
    public boolean getIsBindPhone() {
        return BitStatesUtils.hasState(this.bitstate,
          BitStatesUtils.OP_BIND_PHONE);
    }

    // 判断是否已经绑定看了银行卡
    public boolean getIsBindBank() {
        return BitStatesUtils.hasState(this.bitstate,
          BitStatesUtils.OP_HAS_BIND_BANK);
    }

    // 判断是否已经绑定了邮箱
    public boolean getIsBindEmail() {
        return BitStatesUtils.hasState(this.bitstate,
          BitStatesUtils.OP_BIND_EMAIL);
    }

    // 添加绑定的状态码
    public void addState(Long state) {
        bitstate = BitStatesUtils.addState(this.bitstate, state);
    }
    // 移除状态码
    public void  removeState(Long state) {
        bitstate = BitStatesUtils.removeState(this.bitstate, state);
    }

    // 判断用户是否已经填写了基本资料
    public boolean getIsBasicInfo() {
        return BitStatesUtils.hasState(this.bitstate,
          BitStatesUtils.OP_BASIC_INFO);
    }

    // 判断用户是否已经实名认证
    public boolean getIsRealAuth() {
        return BitStatesUtils.hasState(this.bitstate,
          BitStatesUtils.OP_REAL_AUTH);
    }

    // 判断用户是否已经视频认证
    public boolean getIsVedioAuth() {
        return BitStatesUtils.hasState(this.bitstate,
          BitStatesUtils.OP_VEDIO_AUTH);
    }
    // 判断用户是否已经有一个借款在审核流程中
    public boolean getHasBidRequestInProcess() {
        return BitStatesUtils.hasState(this.bitstate,
          BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
    }
    // 判断用户是否已经有一个提现在审核流程中
    public boolean getHasWithdrawInProcess() {
        return BitStatesUtils.hasState(this.bitstate,
          BitStatesUtils.OP_HAS_WITHDRAW_PROCESS);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber == null ? null : idnumber.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }


    public Integer getAuthscore() {
        return authscore;
    }

    public void setAuthscore(Integer authscore) {
        this.authscore = authscore;
    }

    public Long getRealauthid() {
        return realauthid;
    }

    public void setRealauthid(Long realauthid) {
        this.realauthid = realauthid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public void setBitstate(long bitstate) {
        this.bitstate = bitstate;
    }

    public Systemdictionaryitem getIncomegrade() {
        return incomegrade;
    }

    public void setIncomegrade(Systemdictionaryitem incomegrade) {
        this.incomegrade = incomegrade;
    }

    public Systemdictionaryitem getMarriage() {
        return marriage;
    }

    public void setMarriage(Systemdictionaryitem marriage) {
        this.marriage = marriage;
    }

    public Systemdictionaryitem getKidcount() {
        return kidcount;
    }

    public void setKidcount(Systemdictionaryitem kidcount) {
        this.kidcount = kidcount;
    }

    public Systemdictionaryitem getEducationbackground() {
        return educationbackground;
    }

    public void setEducationbackground(Systemdictionaryitem educationbackground) {
        this.educationbackground = educationbackground;
    }

    public Systemdictionaryitem getHousecondition() {
        return housecondition;
    }

    public void setHousecondition(Systemdictionaryitem housecondition) {
        this.housecondition = housecondition;
    }
}