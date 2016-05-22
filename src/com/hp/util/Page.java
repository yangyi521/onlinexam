package com.hp.util;

public class Page
{
  private int everyPage;
  private int totalCount;
  private int totalPage;
  private int currentPage;
  private int beginIndex;
  private boolean hasPrePage;
  private boolean hasNextPage;
  
  public int getEveryPage()
  {
    return this.everyPage;
  }
  
  public void setEveryPage(int everyPage)
  {
    this.everyPage = everyPage;
  }
  
  public int getTotalCount()
  {
    return this.totalCount;
  }
  
  public void setTotalCount(int totalCount)
  {
    this.totalCount = totalCount;
  }
  
  public int getTotalPage()
  {
    return this.totalPage;
  }
  
  public void setTotalPage(int totalPage)
  {
    this.totalPage = totalPage;
  }
  
  public int getCurrentPage()
  {
    return this.currentPage;
  }
  
  public void setCurrentPage(int currentPage)
  {
    this.currentPage = currentPage;
  }
  
  public int getBeginIndex()
  {
    return this.beginIndex;
  }
  
  public void setBeginIndex(int beginIndex)
  {
    this.beginIndex = beginIndex;
  }
  
  public boolean isHasPrePage()
  {
    return this.hasPrePage;
  }
  
  public void setHasPrePage(boolean hasPrePage)
  {
    this.hasPrePage = hasPrePage;
  }
  
  public boolean isHasNextPage()
  {
    return this.hasNextPage;
  }
  
  public void setHasNextPage(boolean hasNextPage)
  {
    this.hasNextPage = hasNextPage;
  }
  
  public Page(int everyPage, int totalCount, int totalPage, int currentPage, int beginIndex, boolean hasPrePage, boolean hasNextPage)
  {
    this.everyPage = everyPage;
    this.totalCount = totalCount;
    this.totalPage = totalPage;
    this.currentPage = currentPage;
    this.beginIndex = beginIndex;
    this.hasPrePage = hasPrePage;
    this.hasNextPage = hasNextPage;
  }
  
  public Page() {}
}
