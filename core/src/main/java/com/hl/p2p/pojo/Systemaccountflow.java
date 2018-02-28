package com.hl.p2p.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Systemaccountflow {
    private Long id;

    private Date createddate;

    private Byte accountactiontype;

    private BigDecimal amount;

    private String note;

    private BigDecimal balance;

    private BigDecimal freezedamount;

    private Long systemaccountId;

    private Long targetuserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Byte getAccountactiontype() {
        return accountactiontype;
    }

    public void setAccountactiontype(Byte accountactiontype) {
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

    public BigDecimal getFreezedamount() {
        return freezedamount;
    }

    public void setFreezedamount(BigDecimal freezedamount) {
        this.freezedamount = freezedamount;
    }

    public Long getSystemaccountId() {
        return systemaccountId;
    }

    public void setSystemaccountId(Long systemaccountId) {
        this.systemaccountId = systemaccountId;
    }

    public Long getTargetuserId() {
        return targetuserId;
    }

    public void setTargetuserId(Long targetuserId) {
        this.targetuserId = targetuserId;
    }
}