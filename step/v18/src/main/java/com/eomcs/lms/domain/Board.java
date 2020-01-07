package com.eomcs.lms.domain;

import java.util.Date;

public class Board {
  private int    postNum;
  private String postContent;
  private Date   writeDay;
  private int    viewCount;
  private String writer;
  
  public int getPostNum() {
    return postNum;
  }
  public void setPostNum(int postNum) {
    this.postNum = postNum;
  }
  public String getPostContent() {
    return postContent;
  }
  public void setPostContent(String postContent) {
    this.postContent = postContent;
  }
  public Date getWriteDay() {
    return writeDay;
  }
  public void setWriteDay(Date writeDay) {
    this.writeDay = writeDay;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }
}
