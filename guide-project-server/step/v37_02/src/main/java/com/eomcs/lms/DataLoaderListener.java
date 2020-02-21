package com.eomcs.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {

  Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb", "kyh", "1111");

      context.put("boardDao", new BoardDaoImpl(con));
      context.put("lessonDao", new LessonDaoImpl(con));
      context.put("memberDao", new MemberDaoImpl(con));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    try {
      con.close();
    } catch (Exception e) {

    }
    System.out.println("데이터를 저장합니다.");
  }
}
