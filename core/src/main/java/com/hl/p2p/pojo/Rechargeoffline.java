package com.hl.p2p.pojo;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Rechargeoffline extends BaseAuth{
    private Long id;

    private String remark;

    private String tradecode;

    private Date tradetime;

    private BigDecimal amount;

    private String note;

    private Companybankinfo bankinfo;

    public String getJsonString(){
        Map<String, Object> json  = new HashMap<>();
        json.put("id", id);
        json.put("username", this.getApplier().getUsername());
        json.put("tradecode", tradecode);
        json.put("tradetime", tradetime);
        json.put("amount", amount);
        json.put("note", note);

        return JSONObject.toJSONString(json);
    }


    public Companybankinfo getBankinfo() {
        return bankinfo;
    }

    public void setBankinfo(Companybankinfo bankinfo) {
        this.bankinfo = bankinfo;
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

    public String getTradecode() {
        return tradecode;
    }

    public void setTradecode(String tradecode) {
        this.tradecode = tradecode == null ? null : tradecode.trim();
    }

    public Date getTradetime() {
        return tradetime;
    }

    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

}