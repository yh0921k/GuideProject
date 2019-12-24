package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.eomcs.lms.domain.Board;

public class BoardHandler {  
  static final int BOARD_SIZE  = 100;  
  static int boardCnt  = 0;
  static Board[]  boards  = new Board[BOARD_SIZE];
  public static BufferedReader br;
  
  public static void addBoard() throws Exception {
    Board b = new Board();          
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    b.postNum = Integer.parseInt(br.readLine());
    System.out.printf("내용 : ");
    b.postContent = br.readLine();
    b.writeDay = new Date();
    b.viewCount = 0;
    boards[boardCnt++] = b;
    System.out.println("\nBoard Save Complete.");
  }
  public static void printBoardList() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s%26s%25s%12s%6s\n", "번호", " ", "내용", " ", "작성일", " ", "조회수");
    for(int i=0; i<boardCnt; i++) { 
      System.out.printf("%4d%4s%20s%10s%9s%8s\n", 
          boards[i].postNum, " ", boards[i].postContent, " ", 
          new SimpleDateFormat("yyyy-MM-dd").format(boards[i].writeDay), boards[i].viewCount);
    }
  }
}
