package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Prompt;

public class LessonUpdateServlet implements Servlet {

  LessonDao lessonDao;

  public LessonUpdateServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호 : ");

    Lesson oldLesson;
    oldLesson = lessonDao.findByNo(no);
    if (oldLesson == null) {
      out.println("해당 번호의 수업이 없습니다.");
      return;
    }

    Lesson newLesson = new Lesson();
    newLesson.setNo(oldLesson.getNo());
    newLesson.setTitle(Prompt.getString(in, out, String.format("제목(%s) ; ", oldLesson.getTitle())));

    newLesson.setDescription(
        Prompt.getString(in, out, String.format("설명(%s) : ", oldLesson.getDescription())));

    newLesson.setStartDate(
        Prompt.getDate(in, out, String.format("시작일(%s) : ", oldLesson.getStartDate())));

    newLesson
        .setEndDate(Prompt.getDate(in, out, String.format("종료일(%s) : ", oldLesson.getEndDate())));

    newLesson.setTotalHours(
        Prompt.getInt(in, out, String.format("총수업시간(%s) : ", oldLesson.getTotalHours())));

    newLesson.setDayHours(
        Prompt.getInt(in, out, String.format("일수업시간(%s) : ", oldLesson.getDayHours())));

    if (lessonDao.update(newLesson) > 0) {
      out.println("수업을 변경했습니다.");

    } else {
      out.println("수업 업데이트 실패");
    }
  }
}
