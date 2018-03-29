package com.hl.p2p.pojo;

import com.hl.p2p.utils.BidConst;

import java.math.BigDecimal;
import java.util.Date;

public class Bidrequest {
    private Long id;

    private Integer version; //版本号

    private int bidrequesttype = BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL; //借款类型

    private int returnType = BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL; // 还款方式

    private int bidrequeststate = BidConst.BIDREQUEST_STATE_PUBLISH_PENDING; //借款状态

    private BigDecimal bidrequestamount = BidConst.ZERO; //借款总金额

    private BigDecimal currentrate = BidConst.ZERO; //年化利率

    private int monthes2return = 1; //还款月数

    private Integer bidcount = 0;//已投标次数

    private BigDecimal totalrewardamount = BidConst.ZERO; //总利息

    private BigDecimal currentsum = BidConst.ZERO; //当前已投标总金额

    private String title; //借款标题

    private String description; //借款描述

    private String note = ""; //风控意见

    private Date disabledate = new Date(); //招标截止日期

    private Logininfo createuser; //借款人

    public Logininfo getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Logininfo createuser) {
        this.createuser = createuser;
    }

    private int disabledays = 0; //招标天数

    private BigDecimal minbidamount = BidConst.SMALLEST_BID_AMOUNT; //最小借款金额

    private Date applytime; //申请时间

    private Date publishtime;//publishTime

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

    public int getBidrequesttype() {
        return bidrequesttype;
    }

    public void setBidrequesttype(int bidrequesttype) {
        this.bidrequesttype = bidrequesttype;
    }

    public int getBidrequeststate() {
        return bidrequeststate;
    }

    public void setBidrequeststate(int bidrequeststate) {
        this.bidrequeststate = bidrequeststate;
    }

    public BigDecimal getBidrequestamount() {
        return bidrequestamount;
    }

    public void setBidrequestamount(BigDecimal bidrequestamount) {
        this.bidrequestamount = bidrequestamount;
    }

    public BigDecimal getCurrentrate() {
        return currentrate;
    }

    public void setCurrentrate(BigDecimal currentrate) {
        this.currentrate = currentrate;
    }

    public int getMonthes2return() {
        return monthes2return;
    }

    public void setMonthes2return(int monthes2return) {
        this.monthes2return = monthes2return;
    }

    public Integer getBidcount() {
        return bidcount;
    }

    public void setBidcount(Integer bidcount) {
        this.bidcount = bidcount;
    }

    public BigDecimal getTotalrewardamount() {
        return totalrewardamount;
    }

    public void setTotalrewardamount(BigDecimal totalrewardamount) {
        this.totalrewardamount = totalrewardamount;
    }

    public BigDecimal getCurrentsum() {
        return currentsum;
    }

    public void setCurrentsum(BigDecimal currentsum) {
        this.currentsum = currentsum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getDisabledate() {
        return disabledate;
    }

    public void setDisabledate(Date disabledate) {
        this.disabledate = disabledate;
    }

    public int getDisabledays() {
        return disabledays;
    }

    public void setDisabledays(int disabledays) {
        this.disabledays = disabledays;
    }

    public BigDecimal getMinbidamount() {
        return minbidamount;
    }

    public void setMinbidamount(BigDecimal minbidamount) {
        this.minbidamount = minbidamount;
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }
}