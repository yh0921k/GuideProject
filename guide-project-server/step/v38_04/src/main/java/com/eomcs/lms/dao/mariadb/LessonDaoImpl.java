package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoImpl implements LessonDao {

  Connection con;

  public LessonDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String sql = String.format(
          "insert into lms_lesson(sdt, edt, tot_hr, day_hr, titl, conts) values('%s', '%s', %d, %d, '%s', '%s')",
          lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours(), lesson.getDayHours(),
          lesson.getTitle(), lesson.getDescription());
      return stmt.executeUpdate(sql);
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select lesson_id, sdt, edt, tot_hr, day_hr, titl, conts from lms_lesson");) {

      ArrayList<Lesson> list = new ArrayList<>();
      while (rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lesson_id"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHours(rs.getInt("tot_hr"));
        lesson.setDayHours(rs.getInt("day_hr"));
        lesson.setTitle(rs.getString("titl"));
        lesson.setDescription(rs.getString("conts"));
        list.add(lesson);
      }
      return list;
    }
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select lesson_id, sdt, edt, tot_hr, day_hr, titl, conts from lms_lesson where lesson_id="
                + no);) {

      if (rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lesson_id"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHours(rs.getInt("tot_hr"));
        lesson.setDayHours(rs.getInt("day_hr"));
        lesson.setTitle(rs.getString("titl"));
        lesson.setDescription(rs.getString("conts"));
        return lesson;
      }
      return null;
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String sql = String.format(
          "update lms_lesson set titl='%s', conts='%s', sdt='%s', edt='%s', tot_hr=%d, day_hr=%d where lesson_id=%d",
          lesson.getTitle(), lesson.getDescription(), lesson.getStartDate(), lesson.getEndDate(),
          lesson.getTotalHours(), lesson.getDayHours(), lesson.getNo());
      return stmt.executeUpdate(sql);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from lms_lesson where lesson_id=" + no);
    }
  }
}
