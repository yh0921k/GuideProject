# 48_01 - 인터페이스 대신 어노테이션으로 메서드 구분하기

인터페이스는 규칙이기 때문에 구현이 매우 엄격하다. 
메서드 이름에서 파라미터 타입/개수, 리턴 타입까지 정확하게 구현해야 한다.
어노테이션을 사용하면, 인터페이스보다 더 유연하게 규칙을 처리할 수 있다.

## 학습목표

- 어노테이션을 정의하고 활용할 수 있다.
- 리플렉션 API를 활용하여 어노테이션 정보를 다룰 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/util/RequestMapping.java 추가
- src/main/java/com/eomcs/util/ApplicationContext.java 추가
- src/main/java/com/eomcs/lms/service/impl/BoardServiceImpl2.java 삭제
- src/main/java/com/eomcs/lms/service/impl/XxxServiceImpl.java 변경
- src/main/java/com/eomcs/lms/servlet/XxxServlet.java 변경
- src/main/java/com/eomcs/lms/DataLoaderListener.java 의 이름 변경
  - ContextLoaderListener.java 로 이름 변경

## 실습  

### 실습 1 : 명령어를 처리할 메서드를 지정할 때 사용할 어노테이션을 정의한다.

- com.eomcs.util.RequestMapping 어노테이션 추가

### 실습 2 : 명령어를 처리할 메서드에 @RequestMapping 어노테이션을 붙인다.

- com.eomcs.lms.servlet.Servlet 인터페이스 삭제
- com.eomcs.lms.servlet.XXXServlet 변경
  - 메서드 선언부에 @RequestMapping 어노테이션을 붙인다.

### 실습 3 : 특정 어노테이션이 붙은 객체의 이름 목록을 리턴하는 메서드를 추가한다.
- com.eomcs.util.ApplicationContext 변경
  - getBeanNamesForAnnotation()을 추가한다.

### 실습 4 : @Component 어노테이션이 붙은 객체를 찾는다.
- com.eomcs.lms.ContextLoaderListener 변경
  - ApplicationContext가 보관하고 있는 객체 중 @Component가 붙은 객체만 찾는다.

### 실습 5 : @Component 객체에서 @RequestMapping이 붙은 메서드가 있는지 찾는다.
- com.eomcs.lms.ContextLoaderListener 변경
  - @Component가 붙은 객체를 조사하여 @RequestMapping이 선언된 메서드가 있는지 알아낸다.
  - getRequestHandler()를 추가한다.

### 실습 6 : request handler의 정보를 저장할 클래스를 정의한다.
- com.eomcs.util.RequestHandler
  - 객체 주소와 메서드 정보, 객체 이름을 보관한다.
- com.eomcs.lms.ContextLoaderListener 변경
  - request handler를 RequestHandler 객체에 담는다.

### 실습 7 : request handler 목록을 보관할 클래스를 정의한다.
- com.eomcs.util.RequestMappingHandlerMapping 추가
  - @RequestMapping 어노테이션이 붙은 메서드의 정보를 보관한다.

### 실습 8 : 실습 6에서 찾은 request handler를 목록에 보관한다.
- com.eomcs.lms.ContextLoaderHandler 변경
  - RequestHandler 객체를 RequestMappingHandlerMapping 객체에 보관한다.

### 실습 9 : RequestMappingHandlerMapping에 보관된 객체를 사용하여 명령을 처리한다.
- com.eomcs.lms.ServerApp 변경
  - Map에서 이 객체를 꺼내 보관한다.
  - RequestMappingHandlerMapping 객체에서 명령을 처리할 메서드를 꺼낸다.
  - 해당 메서드를 호출하여 클라이언트에게 응답한다.