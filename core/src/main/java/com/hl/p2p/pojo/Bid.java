package com.hl.p2p.pojo;

import com.hl.p2p.utils.BidConst;

import java.math.BigDecimal;
import java.util.Date;

public class Bid {
    private Long id;

    private BigDecimal actualrate = BidConst.ZERO;// 实际年利率(应该是等同于标的的利率)

    private BigDecimal availableamount = BidConst.ZERO;  // 投标有效金额(就是投标金额)

    private Long bidrequestId; // 来自于哪个借款标

    private Long biduserId;  // 投标人id(loginInfo)

    private Date bidtime;  // 投标时间

    private String bidrequesttitle; // 投标标题

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getActualrate() {
        return actualrate;
    }

    public void setActualrate(BigDecimal actualrate) {
        this.actualrate = actualrate;
    }

    public BigDecimal getAvailableamount() {
        return availableamount;
    }

    public void setAvailableamount(BigDecimal availableamount) {
        this.availableamount = availableamount;
    }

    public Long getBidrequestId() {
        return bidrequestId;
    }

    public void setBidrequestId(Long bidrequestId) {
        this.bidrequestId = bidrequestId;
    }

    public Long getBiduserId() {
        return biduserId;
    }

    public void setBiduserId(Long biduserId) {
        this.biduserId = biduserId;
    }

    public Date getBidtime() {
        return bidtime;
    }

    public void setBidtime(Date bidtime) {
        this.bidtime = bidtime;
    }

    public String getBidrequesttitle() {
        return bidrequesttitle;
    }

    public void setBidrequesttitle(String bidrequesttitle) {
        this.bidrequesttitle = bidrequesttitle == null ? null : bidrequesttitle.trim();
    }
}