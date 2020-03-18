package com.eomcs.lms;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.sql.MybatisDaoFactory;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.SqlSessionFactoryProxy;

// 어플리케이션을 설정하는 클래스
// Spring IoC Container가 탐색할 패키지 설정
// > 지정한 패키지 및 그 하위 패키지를 모두 탐색하여
// > @Component 어노테이션이 붙은 클래스를 찾아 객체를 생성한다.
@ComponentScan(value = "com.eomcs.lms")
public class AppConfig {

  MybatisDaoFactory daoFactory;

  public AppConfig() throws IOException {
    // Mybatis 설정 파일을 로딩할 때 사용할 입력 스트림 준비
  }

  // Spring IoC Container에 수동으로 등록할 객체는 메서드를 만들어 리턴한다.
  // > 단 메서드 선언분에 @Bean 어노테이션을 붙여야 한다.
  // String Ioc Container에 수동으로 객체를 등ㄹ고하고 싶다면, 해당 객체를 만들어 주느 팩토리 메서들르 정의해야 한다.
  // > 그래야만 Spring IoC Container는 이 메서드를 호출하고 결과값을 저장한다.


  @Bean
  public SqlSessionFactory sqlSessionFactory() throws IOException {
    InputStream inputStream =
        Resources.getResourceAsStream("com/eomcs/lms/conf/mybatis-config.xml");
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
  }

  @Bean
  public MybatisDaoFactory daoFactory(SqlSessionFactory sqlSessionFactory) {
    // 이 메서드를 호출할 때 스프링 컨테이너에 들어 있는 객체를 원한다면 위와 같이 파라미터로 선언한다.
    // 그러면 스프링 컨테이너가 이 팩토리 메서드를 호출하기 전에 SqlSessionFactory를 먼저 준비한 다음 이 메서들르 실행할 것이다.
    return new MybatisDaoFactory(sqlSessionFactory);
  }

  @Bean
  public PlatformTransactionManager transactionManager(SqlSessionFactory sqlSessionFactory) {
    return new PlatformTransactionManager(sqlSessionFactory);
  }

  @Bean
  public BoardDao boardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(BoardDao.class);
  }

  @Bean
  public LessonDao lessonDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(LessonDao.class);
  }

  @Bean
  public MemberDao memberDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(MemberDao.class);
  }

  @Bean
  public PhotoBoardDao photoBoardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoBoardDao.class);
  }

  @Bean
  public PhotoFileDao photoFileDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoFileDao.class);
  }

}
