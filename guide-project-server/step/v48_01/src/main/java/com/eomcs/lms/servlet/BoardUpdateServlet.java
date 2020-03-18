package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;
import com.eomcs.util.Component;
import com.eomcs.util.Prompt;
import com.eomcs.util.RequestMapping;

@Component
public class BoardUpdateServlet {

  BoardService boardService;

  public BoardUpdateServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/update")
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호 : ");

    Board oldBoard;
    oldBoard = boardService.get(no);
    if (oldBoard == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Board newBoard = new Board();
    newBoard.setNo(no);
    newBoard.setTitle(Prompt.getString(in, out,
        String.format("제목(%s) : \n", oldBoard.getTitle(), oldBoard.getTitle())));

    if (boardService.update(newBoard) > 0) {
      out.println("게시물을 변경했습니다.");

    } else {
      out.println("게시물 업데이트 실패");
    }
  }
}
