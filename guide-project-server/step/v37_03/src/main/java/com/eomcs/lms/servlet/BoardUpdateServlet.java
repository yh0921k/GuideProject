package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardUpdateServlet implements Servlet {

  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호 : \n!{}!");
    int no = Integer.parseInt(in.nextLine());

    Board oldBoard;
    oldBoard = boardDao.findByNo(no);
    if (oldBoard == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    out.printf("제목(%s) : \n!{}!\n", oldBoard.getTitle());
    Board newBoard = new Board();
    newBoard.setNo(no);
    newBoard.setTitle(in.nextLine());

    if (boardDao.update(newBoard) > 0) {
      out.println("게시물을 변경했습니다.");

    } else {
      out.println("게시물 업데이트 실패");
    }
  }
}
