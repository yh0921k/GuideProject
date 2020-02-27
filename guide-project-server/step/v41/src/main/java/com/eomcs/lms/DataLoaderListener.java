package com.eomcs.lms;

import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;
import com.eomcs.sql.DataSource;
import com.eomcs.sql.PlatformTransactionManager;

public class DataLoaderListener implements ApplicationContextListener {


  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
      String username = "kyh";
      String password = "1111";

      DataSource dataSource = new DataSource(jdbcUrl, username, password);
      context.put("dataSource", dataSource);

      PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
      context.put("transactionManager", txManager);

      context.put("boardDao", new BoardDaoImpl(dataSource));
      context.put("lessonDao", new LessonDaoImpl(dataSource));
      context.put("memberDao", new MemberDaoImpl(dataSource));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(dataSource));
      context.put("photoFileDao", new PhotoFileDaoImpl(dataSource));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    DataSource dataSource = (DataSource) context.get("dataSource");
    dataSource.clean();
  }
}
