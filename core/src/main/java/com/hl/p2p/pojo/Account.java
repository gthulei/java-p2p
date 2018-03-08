package com.hl.p2p.pojo;

import cpm.hl.p2p.utils.BidConst;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class Account {
    private Long id;

    private Integer version;

    private String tradepassword; // 交易密码

    private BigDecimal usableamount = BidConst.ZERO;// 账户可用金额

    private BigDecimal freezedamount = BidConst.ZERO; // 账户冻结金额

    private BigDecimal borrowlimitamount = BidConst.ZERO; //授信额度


    private BigDecimal unreceiveinterest = BidConst.ZERO; //账户待收利息

    private BigDecimal unreceiveprincipal = BidConst.ZERO; //账户待收本金

    private BigDecimal unreturnamount = BidConst.ZERO; //账户待还金额

    private BigDecimal remainborrowlimit = BidConst.INIT_BORROW_LIMIT; //账户剩余授信额度


    //账户总额= 账户可用金额 + 账户冻结金额 + 账户待收本金
    private BigDecimal getTotalAmount(){
        return usableamount.add(this.freezedamount).add(this.unreceiveprincipal);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradepassword() {
        return tradepassword;
    }

    public void setTradepassword(String tradepassword) {
        this.tradepassword = tradepassword == null ? null : tradepassword.trim();
    }

    public BigDecimal getUsableamount() {
        return usableamount;
    }

    public void setUsableamount(BigDecimal usableamount) {
        this.usableamount = usableamount;
    }

    public BigDecimal getFreezedamount() {
        return freezedamount;
    }

    public void setFreezedamount(BigDecimal freezedamount) {
        this.freezedamount = freezedamount;
    }

    public BigDecimal getBorrowlimitamount() {
        return borrowlimitamount;
    }

    public void setBorrowlimitamount(BigDecimal borrowlimitamount) {
        this.borrowlimitamount = borrowlimitamount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BigDecimal getUnreceiveinterest() {
        return unreceiveinterest;
    }

    public void setUnreceiveinterest(BigDecimal unreceiveinterest) {
        this.unreceiveinterest = unreceiveinterest;
    }

    public BigDecimal getUnreceiveprincipal() {
        return unreceiveprincipal;
    }

    public void setUnreceiveprincipal(BigDecimal unreceiveprincipal) {
        this.unreceiveprincipal = unreceiveprincipal;
    }

    public BigDecimal getUnreturnamount() {
        return unreturnamount;
    }

    public void setUnreturnamount(BigDecimal unreturnamount) {
        this.unreturnamount = unreturnamount;
    }

    public BigDecimal getRemainborrowlimit() {
        return remainborrowlimit;
    }

    public void setRemainborrowlimit(BigDecimal remainborrowlimit) {
        this.remainborrowlimit = remainborrowlimit;
    }
}