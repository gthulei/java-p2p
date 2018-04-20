package com.hl.p2p.pojo;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Moneywithdraw extends BaseAuth{

    private String banknumber;

    private String bankforkname;

    private String bankname;

    private String realname;

    private BigDecimal moneyamount;

    private BigDecimal chargefee;

    public String getJsonString (){
        Map<String , Object> json =  new HashMap<>();
        json.put("id", this.getId()) ;
        json.put("username", this.getApplier().getUsername()) ;
        json.put("realName", this.realname) ;
        json.put("applyTime", this.getApplytime()) ;
        json.put("bankName", this.bankname) ;
        json.put("accountNumber", this.banknumber) ;
        json.put("forkName", this.bankforkname) ;
        json.put("moneyAmount", this.moneyamount) ;
        return JSONObject.toJSONString(json);
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

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public BigDecimal getMoneyamount() {
        return moneyamount;
    }

    public void setMoneyamount(BigDecimal moneyamount) {
        this.moneyamount = moneyamount;
    }

    public BigDecimal getChargefee() {
        return chargefee;
    }

    public void setChargefee(BigDecimal chargefee) {
        this.chargefee = chargefee;
    }
}