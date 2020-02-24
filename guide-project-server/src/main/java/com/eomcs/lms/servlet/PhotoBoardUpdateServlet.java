package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호 : \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard old = photoBoardDao.findByNo(no);
    if (old == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }

    out.printf("제목(%s) : \n!{}!\n", old.getTitle());
    out.flush();
    PhotoBoard newPhotoBoard = new PhotoBoard();
    newPhotoBoard.setNo(no);
    newPhotoBoard.setTitle(in.nextLine());

    if (photoBoardDao.update(newPhotoBoard) > 0) {
      out.println("사진 게시글을 변경했습니다.");

    } else {
      out.println("사진 게시글 변경에 실패했습니다.");
    }
  }
}
