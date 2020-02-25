package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardUpdateServlet implements Servlet {

  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호 : ");

    Board oldBoard;
    oldBoard = boardDao.findByNo(no);
    if (oldBoard == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Board newBoard = new Board();
    newBoard.setNo(no);
    newBoard.setTitle(Prompt.getString(in, out,
        String.format("제목(%s) : \n", oldBoard.getTitle(), oldBoard.getTitle())));

    if (boardDao.update(newBoard) > 0) {
      out.println("게시물을 변경했습니다.");

    } else {
      out.println("게시물 업데이트 실패");
    }
  }
}
