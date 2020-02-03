package com.eomcs.lms.context;

// 어플리케이션의 상태가 변경되었을 때 호출할 메서드 규칙 정의
public interface ApplicationContextListener {
  void contextInitialized();

  void contextDestroyed();
}
