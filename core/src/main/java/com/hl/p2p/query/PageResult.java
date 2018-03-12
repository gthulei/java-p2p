package com.hl.p2p.query;

/**
 * 分页返回数据
 */

import java.util.List;

public class PageResult {

  private List data;

  private int totalCount;

  private int currentPage = 1;

  private int pageSize = 10;

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

  public int getTotalCount() {

    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount % this.pageSize == 0 ? totalCount / this.pageSize : totalCount / this.pageSize +1;
  }

  public List getData() {

    return data;
  }

  public void setData(List data) {
    this.data = data;
  }
}
