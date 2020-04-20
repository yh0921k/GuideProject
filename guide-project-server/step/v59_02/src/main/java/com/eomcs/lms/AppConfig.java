package com.eomcs.lms;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// 어플리케이션을 설정하는 클래스
// Spring IoC Container가 탐색할 패키지 설정
// > 지정한 패키지 및 그 하위 패키지를 모두 탐색하여
// > @Component 어노테이션이 붙은 클래스를 찾아 객체를 생성한다.
@ComponentScan(value = "com.eomcs.lms")
@EnableWebMvc
public class AppConfig {

  static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() throws IOException {
    // Mybatis 설정 파일을 로딩할 때 사용할 입력 스트림 준비
    logger.debug("[Appconfig.java] :: constructor called");
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver( //
        "/WEB-INF/jsp/", // prefix
        ".jsp" // suffix
    );
    return vr;
    // => prefix + 페이지컨트롤러 리턴 값 + suffix
    // 에) "/WEB-INF/jsp/" + "board/list" + ".jsp" = /WEB-INF/jsp2/board/list.
  }

  @Bean
  public MultipartResolver multipartResolver() {
    // 스프링 WebMVC에서 파일 업로드를 처리하고 싶다면,
    // 이 메서드를 통해 MultipartResolver 구현체를 생성해야 한다.
    // 그래야 request handler는 MultipartFile 객체를 받을 수 있다.
    CommonsMultipartResolver mr = new CommonsMultipartResolver();
    mr.setMaxUploadSize(10000000);
    mr.setMaxInMemorySize(2000000);
    mr.setMaxUploadSizePerFile(5000000);
    return mr;
  }
}
