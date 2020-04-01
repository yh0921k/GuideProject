package com.eomcs.lms;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener // 서블릿 컨테이너가 이 객체를 관리
public class ContextLoaderListener implements ServletContextListener {

  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContextListener.super.contextInitialized(sce);

    ServletContext servletContext = sce.getServletContext();

    try {
      ApplicationContext iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
      printBeans(iocContainer);

      servletContext.setAttribute("iocContainer", iocContainer);
      logger.debug("--------------------------------------------------");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void printBeans(ApplicationContext appCtx) {
    logger.debug("--------------------------------------------------");
    logger.debug("Objects in Spring IoC Container");
    logger.debug("--------------------------------------------------");

    String[] beanNames = appCtx.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      logger.debug(
          String.format("%s ---> %s", beanName, appCtx.getBean(beanName).getClass().getName()));
    }

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {}
}
