package com.eomcs.lms;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.util.RequestHandler;
import com.eomcs.util.RequestMapping;
import com.eomcs.util.RequestMappingHandlerMapping;

@WebListener // 서블릿 컨테이너가 이 객체를 관리
public class ContextLoaderListener implements ServletContextListener {

  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // ServletContextListener.super.contextInitialized(sce);

    ServletContext servletContext = sce.getServletContext();

    try {
      ApplicationContext iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
      printBeans(iocContainer);

      servletContext.setAttribute("iocContainer", iocContainer);
      logger.debug("--------------------------------------------------");
      logger.debug("@RequestMappting 메서드 찾기");

      RequestMappingHandlerMapping handlerMapper = new RequestMappingHandlerMapping();
      String[] beanNames = iocContainer.getBeanNamesForAnnotation(Component.class);
      for (String beanName : beanNames) {
        Object component = iocContainer.getBean(beanName);


        Iterator<Method> handlers = getRequestHandlers(component.getClass());
        while (handlers.hasNext()) {
          RequestHandler requestHandler = new RequestHandler(handlers.next(), component);
          handlerMapper.addHandler(requestHandler.getPath(), requestHandler);
          logger.debug(String.format("%s ==> %s.%s()", requestHandler.getPath(),
              component.getClass().getName(), requestHandler.getMethod().getName()));
        }
      }
      servletContext.setAttribute("handlerMapper", handlerMapper);

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

  private Iterator<Method> getRequestHandlers(Class<?> type) {
    // 클라이언트 명령을 처리할 메서드는 public 이기 때문에
    // 클래스에서 public 메서드만 조사한다.
    ArrayList<Method> handlers = new ArrayList<>();
    Method[] methods = type.getMethods();
    for (Method m : methods) {
      // 메서드에 @RequestMapping 애노테이션이 붙었는지 검사한다.
      RequestMapping anno = m.getAnnotation(RequestMapping.class);
      if (anno != null) {
        handlers.add(m);
      }
    }
    return handlers.iterator();
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {}
}
