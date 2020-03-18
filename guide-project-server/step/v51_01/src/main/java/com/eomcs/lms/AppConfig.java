package com.eomcs.lms;

import java.io.IOException;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

// 어플리케이션을 설정하는 클래스
// Spring IoC Container가 탐색할 패키지 설정
// > 지정한 패키지 및 그 하위 패키지를 모두 탐색하여
// > @Component 어노테이션이 붙은 클래스를 찾아 객체를 생성한다.
@ComponentScan(value = "com.eomcs.lms")

// Spring IoC Container에서 사용할 Properties 파일 로딩
@PropertySource("classpath:com/eomcs/lms/conf/jdbc.properties")

// Mybatis Dao 프록시를 자동생성할 인터페이시ㅡ를 지정한다.
@MapperScan("com.eomcs.lms.dao")
public class AppConfig {

  // @PropertySource 로 로딩한 .proerties 파일의 값을 사용하고 싶다면, 다음 어노테이션을 인스턴스 필드 앞에 붙인다.
  // Spring IoC 컨테이너가 이 클래스의 객체를 생성할 때 해당 필드에 프로퍼티 값을 자동으로 주입할 것이다.
  @Value("${jdbc.driver}")
  String jdbcDriver;

  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc.username}")
  String jdbcUsername;

  @Value("${jdbc.password}")
  String jdbcPassword;

  public AppConfig() throws IOException {
    // Mybatis 설정 파일을 로딩할 때 사용할 입력 스트림 준비
  }

  // Spring IoC Container에 수동으로 등록할 객체는 메서드를 만들어 리턴한다.
  // > 단 메서드 선언분에 @Bean 어노테이션을 붙여야 한다.
  // String Ioc Container에 수동으로 객체를 등ㄹ고하고 싶다면, 해당 객체를 만들어 주느 팩토리 메서들르 정의해야 한다.
  // > 그래야만 Spring IoC Container는 이 메서드를 호출하고 결과값을 저장한다.


  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

  // DB 커넥션 풀과 Spring IoC Container를 인자로 필요로함
  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx)
      throws Exception {

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("com.eomcs.lms.domain");
    sqlSessionFactoryBean
        .setMapperLocations(appCtx.getResources("classpath:com/eomcs/lms/mapper/*Mapper.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
