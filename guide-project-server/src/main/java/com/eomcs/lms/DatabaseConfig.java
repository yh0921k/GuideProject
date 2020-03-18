package com.eomcs.lms;

import java.io.IOException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Spring IoC 컨테이너가 이 클래스를 JavaConfig로 자동 인식하게 하려면 아래와 같이 사용
// 단, 이 클래스가 @ComponentScan에서 지정한 패키지 안에 있어야 한다.
@Configuration

// @Transactional이 붙은 메서드가 있을 경우
// 트랜잭션 제어 코드가 삽입된 프록시 객체를 자동 생성한다.
// PhotoBoardDaoImpl 객체 생성되는 것 확인
// >> photoBoardServiceImpl ---> com.sun.proxy.$Proxy31 형태로 생성됨
@EnableTransactionManagement

// Spring IoC Container에서 사용할 Properties 파일 로딩
@PropertySource("classpath:com/eomcs/lms/conf/jdbc.properties")
public class DatabaseConfig {

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

  public DatabaseConfig() throws IOException {
    // Mybatis 설정 파일을 로딩할 때 사용할 입력 스트림 준비
    System.out.println("Create DatabaseConfig");
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

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
