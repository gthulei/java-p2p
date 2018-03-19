package com.hl.p2p.query;

public class SystemdictionaryQueryObject extends BaseQuery{

  private String keyword;

  private long parentId;

  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
