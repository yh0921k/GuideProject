package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.eomcs.lms.domain.Board;

public class BoardHandler {  
  private ArrayList boardList;
  private BufferedReader br;
  
  public BoardHandler(BufferedReader br) {
    this.br = br;
    boardList = new ArrayList();
  }
  
  public BoardHandler(BufferedReader br, int capacity) {
    this.br = br;
    boardList = new ArrayList(capacity);
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
    boardList.add(b);
    System.out.println("\nBoard Save Complete.");
  }
  public void printBoardList() throws Exception {
    Object[] arr = boardList.toArray();
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s\n", "번호", " ", "내용");
    for(Object obj : arr) { 
      Board b = (Board)obj;
      System.out.printf("%4d%4s%20s\n", 
          b.getPostNum(), " ", b.getPostContent());
    }
  }
  public void printDetailBoard() throws Exception {
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());
    Board board = (Board)boardList.get(index);

    
    if(board == null) {
      System.out.println("Board Number does not exists.");
      return;
    }
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s%26s%25s%12s%6s\n", "번호", " ", "내용", " ", "작성일", " ", "조회수");
    System.out.printf("%4d%4s%20s%10s%9s%8s\n", 
        board.getPostNum(), " ", board.getPostContent(), " ", 
          new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDay()), board.getViewCount());
  }
}
