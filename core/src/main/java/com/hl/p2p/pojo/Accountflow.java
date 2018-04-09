package com.hl.p2p.pojo;

import com.hl.p2p.utils.BidConst;

import java.math.BigDecimal;
import java.util.Date;

public class Accountflow {
    private Long id;

    private int accountactiontype;

    private BigDecimal amount = BidConst.ZERO;

    private String note;

    private BigDecimal balance = BidConst.ZERO;

    private BigDecimal freezed = BidConst.ZERO;

    private Date actiontime;

    private Long accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAccountactiontype() {
        return accountactiontype;
    }

    public void setAccountactiontype(int accountactiontype) {
        this.accountactiontype = accountactiontype;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreezed() {
        return freezed;
    }

    public void setFreezed(BigDecimal freezed) {
        this.freezed = freezed;
    }

    public Date getActiontime() {
        return actiontime;
    }

    public void setActiontime(Date actiontime) {
        this.actiontime = actiontime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}