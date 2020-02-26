package com.eomcs.lms;

import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;
import com.eomcs.util.ConnectionFactory;

public class DataLoaderListener implements ApplicationContextListener {


  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
      String username = "kyh";
      String password = "1111";

      ConnectionFactory conFactory = new ConnectionFactory(jdbcUrl, username, password);
      context.put("connectionFactory", conFactory);

      context.put("boardDao", new BoardDaoImpl(conFactory));
      context.put("lessonDao", new LessonDaoImpl(conFactory));
      context.put("memberDao", new MemberDaoImpl(conFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(conFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(conFactory));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
