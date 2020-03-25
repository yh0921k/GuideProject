package com.eomcs.lms;

import java.io.IOException;
import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring IoC 컨테이너가 이 클래스를 JavaConfig로 자동 인식하게 하려면 아래와 같이 사용
// 단, 이 클래스가 @ComponentScan에서 지정한 패키지 안에 있어야 한다.
@Configuration

// Mybatis Dao 프록시를 자동생성할 인터페이시ㅡ를 지정한다.
@MapperScan("com.eomcs.lms.dao")
public class MybatisConfig {

  static Logger logger = LogManager.getLogger(MybatisConfig.class);


  public MybatisConfig() throws IOException {
    LogFactory.useLog4JLogging();

    // Mybatis 설정 파일을 로딩할 때 사용할 입력 스트림 준비
    logger.debug("Create MybatisConfig");
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
}
