package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  LessonDao lessonDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, LessonDao lessonDao,
      PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.lessonDao = lessonDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();

    out.println("제목 : \n!{}!");
    out.flush();
    photoBoard.setTitle(in.nextLine());

    out.println("수업번호 : \n!{}!");
    out.flush();

    int lessonNo = Integer.parseInt(in.nextLine());
    Lesson lesson = lessonDao.findByNo(lessonNo);
    if (lesson == null) {
      out.println("수업 번호가 유효하지 않습니다.");
      return;
    }
    photoBoard.setLesson(lesson);

    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 엔터를 입력하면 파일 추가를 마칩니다.");
      ArrayList<PhotoFile> photoFiles = new ArrayList<>();
      while (true) {
        out.println("사진 파일 : \n!{}!");
        out.flush();
        String filepath = in.nextLine();
        if (filepath.length() == 0) {
          if (photoFiles.size() > 0)
            break;
          out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        }
        // 기본 생성자 사용 (1)
        // PhotoFile photoFile = new PhotoFile();
        // photoFile.setFilepath(filepath);
        // photoFile.setBoardNo(photoBoard.getNo());
        // photoFiles.add(photoFile);

        // 초기 값을 설정하는 생성자 사용 (2)
        // photoFiles.add(new PhotoFile(filepath, photoBoard.getNo()));

        // setter 메서드를 통해 체인 방식으로 인스턴스 필드 값을 설정한다. (3)
        photoFiles.add(new PhotoFile().setFilepath(filepath).setBoardNo(photoBoard.getNo()));
      }
      for (PhotoFile photoFile : photoFiles) {
        photoFileDao.insert(photoFile);
      }
      out.println("새 게시글을 등록했습니다.");
    } else {
      out.println("게시글 등록에 실패했습니다.");
    }
  }
}
