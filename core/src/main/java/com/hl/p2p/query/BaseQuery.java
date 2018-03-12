package com.hl.p2p.query;

abstract public class BaseQuery {

  private int currentPage = 1;

  private int pageSize = 10;

  public int getStart() {
    return  (this.currentPage - 1) * this.pageSize;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getCurrentPage() {

    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }
}
