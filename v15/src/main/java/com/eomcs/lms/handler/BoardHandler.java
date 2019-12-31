package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.eomcs.lms.domain.Board;

public class BoardHandler {  
  int boardCnt  = 0;
  Board[] boards;
  private BufferedReader br;
  
  static final int BOARD_SIZE  = 100;  
  
  public BoardHandler(BufferedReader br) {
    this.br = br;
    this.boards = new Board[BOARD_SIZE];
  }
  public BoardHandler(BufferedReader br, int capacity) {
    this.br = br;
    if(capacity < BOARD_SIZE || capacity > 10000)    
      this.boards = new Board[BOARD_SIZE];
    else
      this.boards = new Board[capacity];
  }
  public void addBoard() throws Exception {
    Board b = new Board();          
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    b.setPostNum(Integer.parseInt(br.readLine()));
    System.out.printf("내용 : ");
    b.setPostContent(br.readLine());
    b.setWriteDay(new Date());
    b.setViewCount(0);
    this.boards[this.boardCnt++] = b;
    System.out.println("\nBoard Save Complete.");
  }
  public void printBoardList() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s\n", "번호", " ", "내용");
    for(int i=0; i<this.boardCnt; i++) { 
      System.out.printf("%4d%4s%20s\n", 
          this.boards[i].getPostNum(), " ", this.boards[i].getPostContent());
    }
  }
  public void printDetailBoard() throws Exception {
    System.out.printf("번호 : ");
    int boardNum = Integer.parseInt(br.readLine());
    
    Board b = null;
    for(int i = 0; i < this.boardCnt; i++) {
      if(this.boards[i].getPostNum() == boardNum) {
        b = this.boards[i];
        break;
      }
    }
    
    if(b == null) {
      System.out.println("Board Number does not exists.");
      return;
    }
    
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s%26s%25s%12s%6s\n", "번호", " ", "내용", " ", "작성일", " ", "조회수");
    System.out.printf("%4d%4s%20s%10s%9s%8s\n", 
          b.getPostNum(), " ", b.getPostContent(), " ", 
          new SimpleDateFormat("yyyy-MM-dd").format(b.getWriteDay()), b.getViewCount());
  }
}
