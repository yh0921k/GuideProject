package com.eomcs.lms.domain;

import java.io.Serializable;

public class PhotoFile implements Serializable {

  private static final long serialVersionUID = 1L;

  int no;
  String filepath;
  int boardNo;

  public PhotoFile() {}

  public PhotoFile(String filepath, int boardNo) {
    this.filepath = filepath;
    this.boardNo = boardNo;
  }

  public PhotoFile(int no, String filepath, int boardNo) {
    this(filepath, boardNo);
    this.no = no;

  }

  @Override
  public String toString() {
    return "PhotoFile [no=" + no + ", filepath=" + filepath + ", boardNo=" + boardNo + "]";
  }

  public int getNo() {
    return no;
  }

  public PhotoFile setNo(int no) {
    this.no = no;
    return this;
  }

  public String getFilepath() {
    return filepath;
  }

  public PhotoFile setFilepath(String filepath) {
    this.filepath = filepath;
    return this;
  }

  public int getBoardNo() {
    return boardNo;
  }

  public PhotoFile setBoardNo(int boardNo) {
    this.boardNo = boardNo;
    return this;
  }


}
