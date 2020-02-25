package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateServlet implements Servlet {

  LessonDao lessonDao;

  public LessonUpdateServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호 : \n!{}!");
    int no = Integer.parseInt(in.nextLine());

    Lesson oldLesson;
    oldLesson = lessonDao.findByNo(no);
    if (oldLesson == null) {
      out.println("해당 번호의 수업이 없습니다.");
      return;
    }

    Lesson newLesson = new Lesson();
    newLesson.setNo(oldLesson.getNo());
    out.printf("제목(%s) : \n!{}!\n", oldLesson.getTitle());
    newLesson.setTitle(in.nextLine());
    out.printf("설명(%s) : \n!{}!\n", oldLesson.getDescription());
    newLesson.setDescription(in.nextLine());
    out.printf("시작일(%s) : \n!{}!\n", oldLesson.getStartDate());
    newLesson.setStartDate(Date.valueOf(in.nextLine()));
    out.printf("종료일(%s) : \n!{}!\n", oldLesson.getEndDate());
    newLesson.setEndDate(Date.valueOf(in.nextLine()));
    out.printf("총수업시간(%s) : \n!{}!\n", oldLesson.getTotalHours());
    newLesson.setTotalHours(Integer.parseInt(in.nextLine()));
    out.printf("일수업시간(%s) : \n!{}!\n", oldLesson.getDayHours());
    newLesson.setDayHours(Integer.parseInt(in.nextLine()));

    if (lessonDao.update(newLesson) > 0) {
      out.println("수업을 변경했습니다.");

    } else {
      out.println("수업 업데이트 실패");
    }
  }
}
