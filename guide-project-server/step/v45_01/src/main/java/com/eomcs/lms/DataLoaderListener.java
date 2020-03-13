package com.eomcs.lms;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.service.impl.BoardServiceImpl;
import com.eomcs.lms.service.impl.LessonServiceImpl;
import com.eomcs.lms.service.impl.MemberServiceImpl;
import com.eomcs.lms.service.impl.PhotoBoardServiceImpl;
import com.eomcs.sql.MybatisDaoFactory;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {


  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      InputStream inputStream =
          Resources.getResourceAsStream("com/eomcs/lms/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      context.put("sqlSessionFactory", sqlSessionFactory);

      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      BoardDao boardDao = daoFactory.createDao(BoardDao.class);
      LessonDao lessonDao = daoFactory.createDao(LessonDao.class);
      MemberDao memberDao = daoFactory.createDao(MemberDao.class);
      PhotoBoardDao photoBoardDao = daoFactory.createDao(PhotoBoardDao.class);
      PhotoFileDao photoFileDao = daoFactory.createDao(PhotoFileDao.class);

      context.put("boardService", new BoardServiceImpl(boardDao));
      context.put("lessonService", new LessonServiceImpl(lessonDao));
      context.put("memberService", new MemberServiceImpl(memberDao));
      context.put("photoBoardService",
          new PhotoBoardServiceImpl(txManager, photoBoardDao, photoFileDao));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
