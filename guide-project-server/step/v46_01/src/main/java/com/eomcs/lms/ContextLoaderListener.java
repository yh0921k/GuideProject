package com.eomcs.lms;

import java.io.InputStream;
import java.util.HashMap;
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
import com.eomcs.sql.MybatisDaoFactory;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.SqlSessionFactoryProxy;
import com.eomcs.util.ApplicationContext;

public class ContextLoaderListener implements ApplicationContextListener {


  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {

      // ApplicationContext에서 자동으로 생성하지 못하는 객체는 따로 생성하여 Map에 보관한다.
      HashMap<String, Object> beans = new HashMap<>();

      // Mybatis 설정 파일을 로딩할 때 사용할 입력 스트림 준비
      InputStream inputStream =
          Resources.getResourceAsStream("com/eomcs/lms/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      beans.put("sqlSessionFactory", sqlSessionFactory);
      beans.put("transactionManager", txManager);

      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      beans.put("boardDao", daoFactory.createDao(BoardDao.class));
      beans.put("lessonDao", daoFactory.createDao(LessonDao.class));
      beans.put("memberDao", daoFactory.createDao(MemberDao.class));
      beans.put("photoBoardDao", daoFactory.createDao(PhotoBoardDao.class));
      beans.put("photoFileDao", daoFactory.createDao(PhotoFileDao.class));

      ApplicationContext appCtx = new ApplicationContext("com.eomcs.lms", beans);
      appCtx.printBeans();

      // ServerApp이 사용할 수 있게 context 맵에 담아 둔다.
      context.put("iocContainer", appCtx);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
