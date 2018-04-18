package com.hl.p2p.pojo;

import com.hl.p2p.utils.BidConst;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paymentschedule {
    private Long id;

    private Date deadline;

    private Date paydate;

    private BigDecimal totalamount;

    private BigDecimal principal;

    private BigDecimal interest;

    private int monthindex;

    private int state;

    private int bidrequesttype;

    private int returntype;

    private Long bidrequestId;

    private Logininfo borrowuser;

    private String bidrequesttitle;

    // 本期还款计划对应的还款计划明细
    private List<Paymentscheduledetail> paymentScheduleDetails = new ArrayList<>();


    public String getStateDisplay() {
        switch (state) {
            case BidConst.PAYMENT_STATE_NORMAL:
                return "正常待还";
            case BidConst.PAYMENT_STATE_DONE:
                return "已还";
            case BidConst.PAYMENT_STATE_OVERDUE:
                return "逾期";
            default:
                return "未知";
        }
    }

    public List<Paymentscheduledetail> getPaymentScheduleDetails() {
        return paymentScheduleDetails;
    }

    public void setPaymentScheduleDetails(List<Paymentscheduledetail> paymentScheduleDetails) {
        this.paymentScheduleDetails = paymentScheduleDetails;
    }

    public Logininfo getBorrowuser() {
        return borrowuser;
    }

    public void setBorrowuser(Logininfo borrowuser) {
        this.borrowuser = borrowuser;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public int getMonthindex() {
        return monthindex;
    }

    public void setMonthindex(int monthindex) {
        this.monthindex = monthindex;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBidrequesttype() {
        return bidrequesttype;
    }

    public void setBidrequesttype(int bidrequesttype) {
        this.bidrequesttype = bidrequesttype;
    }

    public int getReturntype() {
        return returntype;
    }

    public void setReturntype(int returntype) {
        this.returntype = returntype;
    }

    public Long getBidrequestId() {
        return bidrequestId;
    }

    public void setBidrequestId(Long bidrequestId) {
        this.bidrequestId = bidrequestId;
    }


    public String getBidrequesttitle() {
        return bidrequesttitle;
    }

    public void setBidrequesttitle(String bidrequesttitle) {
        this.bidrequesttitle = bidrequesttitle == null ? null : bidrequesttitle.trim();
    }
}