// 게시글 번호로 객첼르 찾는 코드를 관리하기 쉽게 별도의 메소드로 분리한다.
// ==> indexOfBoard(int) 메소드 추가
package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ArrayList;

public class BoardHandler {  
  private ArrayList<Board> boardList;
  private BufferedReader br;
  
  public BoardHandler(BufferedReader br) {
    this.br = br;
    boardList = new ArrayList<>();
  }
  
  public BoardHandler(BufferedReader br, int capacity) {
    this.br = br;
    boardList = new ArrayList<>(capacity);
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
  public void listBoard() throws Exception {
    Board[] arr = boardList.toArray(new Board[this.boardList.size()]);
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%36s%4s\n", "번호", " ", "내용");
    for(Board b : arr) { 
      if (b == null)
        break;
      System.out.printf("%4d%4s%20s\n", 
          b.getPostNum(), " ", b.getPostContent());
    }
  }
  public void datailBoard() throws Exception {
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());

    // 게시글 인덱스가 아니라 번호로 찾기, 업데이트 메소드에도 추가해야함 delete도 번호로 바꿔야됨
    // 변수명도 적절히 수정
    Board board = null;
    int index = -1;
    for(int i = 0; i < this.boardList.size(); i++) {
      Board temp = this.boardList.get(i);
      if(this.boardList.get(i) == no) {
        board=temp;
        index = i;
        break;
      }
    }
    
    // 위에서 아래로 변환 -- 업데이트와 delete도 동일하게, 
    int index = indexOfBoard(no);
    if(index == -1); // 게시글 없음
    
    
    Board board = boardList.get(index);
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
  
  public void updateBoard() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());
    Board oldBoard = boardList.get(index);
    
    if(oldBoard == null) {
      System.out.println("Board Number does not exists.");
      return;
    }
    System.out.printf("내용(%s) : ", oldBoard.getPostContent());
    String tmp = br.readLine();
    if(tmp.length() == 0) {
      System.out.println("Board Update Cancel.");
      return;
    }
    
    Board newBoard = new Board();  
    newBoard.setPostContent(tmp);
    newBoard.setPostNum(oldBoard.getPostNum());
    newBoard.setWriteDay(new Date());
    newBoard.setViewCount(oldBoard.getViewCount());
    this.boardList.set(index, newBoard);
    System.out.println("\nBoard Update Complete.");
  }
  
  public void deleteBoard() throws Exception {
    System.out.printf("번호 : ");
    int index = Integer.parseInt(br.readLine());    
    
    Board board = boardList.get(index);
    if(board == null) {
      System.out.println("Board Number does not exists.");
      return;
    }
    this.boardList.remove(index);
    System.out.println("Delete Complete.");
  }
  
  private int indexOfBoard(int no) {
    for(int i = 0; i < this.boardList.size(); i++) {     
      if(this.boardList.get(i).getPostNum() == no) {
       return i;
      }
    }
    return -1;
  }
}
