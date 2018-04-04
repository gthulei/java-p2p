package com.hl.p2p.pojo;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Companybankinfo {
    private Long id;

    private String bankname;//开户行

    private String accountname;//开户人

    private String banknumber;//卡号

    private String bankforkname;//支行名称

    public String getJsonString(){
        Map<String, Object> json = new HashMap<>();
        json.put("id",id);
        json.put("bankName",bankname);
        json.put("accountName",accountname);
        json.put("accountNumber",banknumber);
        json.put("forkName",bankforkname);
        return JSONObject.toJSONString(json);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    public String getBanknumber() {
        return banknumber;
    }

    public void setBanknumber(String banknumber) {
        this.banknumber = banknumber == null ? null : banknumber.trim();
    }

    public String getBankforkname() {
        return bankforkname;
    }

    public void setBankforkname(String bankforkname) {
        this.bankforkname = bankforkname == null ? null : bankforkname.trim();
    }
}