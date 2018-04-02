package com.hl.p2p.query;

public class BidRequestQueryObject extends BaseQuery{
  private int bidrequeststate=0;

  private int quertState;// 1后端满标审核查询 2 多条件排序查询

  public int getQuertState() {
    return quertState;
  }

  public void setQuertState(int quertState) {
    this.quertState = quertState;
  }

  public int getBidrequeststate() {
    return bidrequeststate;
  }

  public void setBidrequeststate(int bidrequeststate) {
    this.bidrequeststate = bidrequeststate;
  }
}
