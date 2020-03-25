package com.eomcs.lms;

import java.lang.reflect.Method;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.util.RequestHandler;
import com.eomcs.util.RequestMapping;
import com.eomcs.util.RequestMappingHandlerMapping;

public class ContextLoaderListener implements ApplicationContextListener {

  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {

      // Spring IoC Container의 설정 정보를 담고 있는 클래스 타입을 지정한다.
      ApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig.class);

      printBeans(appCtx);

      // ServerApp이 사용할 수 있게 context 맵에 담아 둔다.
      context.put("iocContainer", appCtx);

      logger.debug("--------------------------------------------------");
      RequestMappingHandlerMapping handlerMapper = new RequestMappingHandlerMapping();

      String[] beanNames = appCtx.getBeanNamesForAnnotation(Component.class);
      for (String beanName : beanNames) {
        Object component = appCtx.getBean(beanName);
        Method method = getRequestHandler(component.getClass());
        if (method != null) {
          RequestHandler requestHandler = new RequestHandler(component, method);

          // 명령을 처리할 메서드를 찾을 수 있도록 명령 이름으로 메서드 정보를 저장한다.
          handlerMapper.addHandler(requestHandler.getPath(), requestHandler);
        }
      }
      context.put("handlerMapper", handlerMapper);

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

  private Method getRequestHandler(Class<?> type) {
    // 클라이언트 명령을 처리할 메서드는 public이기 때문에 클래스에서 public 메서드만 조사한다.
    Method[] methods = type.getMethods();
    for (Method m : methods) {
      // 메서드에 @RequestMapping 어노테이션이 붙었는지 검사한다.
      RequestMapping anno = m.getAnnotation(RequestMapping.class);
      if (anno != null) {
        return m;
      }
    }
    return null;
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
