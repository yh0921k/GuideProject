# 46_01 - 객체 생성을 자동화하는 IoC 컨테이너 만들기

새 명령을 추가할 때마다 그 명령을 처리할 서블릿 객체를 생성하고 등록해야 한다.
또한 데이터를 다루는 DAO와 비즈니스 로직 및 트랜잭션을 관리하는 서비스 객체를 생성하고 등록해야 한다.
이를 자동화시키면 새 명령을 추가하거나 새 클래스를 만들 때마다 직접 코드를 추가할 필요가 없을 것이다.
객체 생성 및 등록을 자동화하는 객체를 IoC 컨테이너라 한다.
이번 버전의 목표는 IoC 컨테이너를 만드는 것이다.


## 학습목표
- IoC 컨테이너의 개념과 구동 원리를 이해한다.
- 리플렉션 API를 활용하여 클래스 정보를 다루고 객체를 생성할 수 있다.

### IoC(Inversion Of Control)
- `제어의 역전`이라 해석한다.

1) 의존 객체 생성
- 보통의 실행 흐름은 객체를 사용하는 쪽에서 해당 객체를 만드는 것이다.
- 시스템 구조가 복잡해지면, 위와같이 직접 개체를 만드는 방식이 비효율적이다.
- 복잡한 시스템에서는 사용할 객체를 외부에서 주입하는 것이 유지보수에 좋다.
- 객체를 외부에서 주입하는 것은 보통의 실행흐름을 역행하는 것이다.
- 이런 흐름의 역행을 `IoC`라 부른다.

2) 메서드 호출
- 보통 메서드를 생성하면 실행 흐름에 따라 호출한다.
  - 호출 이후 실행이 끝나면 리턴한다.
- 하지만 실행 계획에 따라 호출하는 것이 아니라, 특정 상태에 있을 때 자동으로 호출되게 하는 경우도 필요하다.
  - 시스템이 시작될 때 특정 메서드를 자동으로 호출되게 하는 것
  - 사용자가 마우스를 클릭했을 때 특정 메서드를 자동으로 호출되게 하는 것
- 즉, 개발재가 작성한 코드 흐름에 따라 호출하는 것이 아니라, 특정 상태에 놓여졌을 때 자동으로 호출하는 방식이 필요하다.
- 보통 이런 메서드를 `이벤트 핸들러`, `이벤트 리스터`라 부른다.
- 또는 시스템 쪽에서 호출하는 메서드라는 의미로 `콜백(callback) 메서드`라 부르기도 한다.
- 이런 호출 방식도 IoC의 한 예이다.

### IoC 컨테이너
- 개발자가 직접 객체를 생성하는 것이 아니다.
- 객체 생성을 전담하는 역할자를 통해 객체가 준비된다.
- 이 역할자를 `빈 컨테이터(bean container)`라 부른다.
- 여기에 객체가 사용할 의존 객체를 자동으로 주입하는 역할을 추가한다.
- 즉 객체 스스로ㅗ 자신이 사용할 객체를 만든느 것이 아니라, 외부의 빈 컨테이너로부터 의존 객체를 주입받는 것이다.
- 이런 역할까지 겸하는 것을 `IoC 컨테이너`라 부른다.
- 즉 `IoC 컨테이너 = 빈 컨테이너 + 의존 객체 주입` 이다.
- Spring Ioc Container


## 실습 소스 및 결과

- src/main/java/com/eomcs/util/Component.java 추가
- src/main/java/com/eomcs/util/ApplicationContext.java 추가
- src/main/java/com/eomcs/lms/service/impl/BoardServiceImpl2.java 삭제
- src/main/java/com/eomcs/lms/service/impl/XxxServiceImpl.java 변경
- src/main/java/com/eomcs/lms/servlet/XxxServlet.java 변경
- src/main/java/com/eomcs/lms/DataLoaderListener.java 의 이름 변경
  - ContextLoaderListener.java 로 이름 변경

## 실습 

### 실습 1 : IoC 컨테이너 클래스를 준비한다. (ApplicationContext01)
- com.eomcs.util.ApplicationContext 클래스 생성

### 실습 2: 특정 패키지의 클래스 이름을 수집한다. (ApplicationContext02)
- com.eomcs.util.ApplicationContext 클래스 변경
  - 패키지명을 입력받아서 해당 패키지를 탐색하여 모든 클래스의 이름을 가져온다.
- com.eomcs.lms.DataLoaderListener 의 이름 변경
  - 이제 이 클래스는 데이터를 저장하고 로딩하는 역할을 넘어섰다.
  - 어플리케이션을 실행할 때 사용할 객체나 환경을 준비하는 일을 한다.
  - 따라서 이름을 `ContextLoaderListener`로 변경한다.
- com.eomcs.lms.ContextLoaderListener 변경
  - ApplicationContext 객체를 생성하여 맵에 보관한다.

### 실습 3 : 객체를 생성할 수 있는 concrete class만 추출한다.(ApplicationContext03)
- com.eomcs.util.ApplicationContext 클래스 변경
  - reflection API를 사용하여 인터페이스와 추상클래스 등을 구분한다.

### 실습 4 : concrete class의 생성자 정보를 알아낸다.(ApplicationContext04)
- com.eomcs.util.ApplicationContext 클래스 변경
  - reflection API를 사용하여 클래스의 생성자를 알아낸다.
  - 생성자의 파라미터 정보를 알아낸다.

### 실습 5 : concrete class의 생성자를 호출하여 객체를 준비한다.(ApplicationContext05)
- com.eomcs.util.ApplicationContext 클래스 변경
  - concrete class만 따로 로딩하여 목록을 관리한다.
  - reflection API를 사용하여 생성자의 파라미터 정보를 알아낸다.
  - 파라미터 객체를 준비하여 생성자를 호출한다.
  - 생성된 객체를 객체 보관소(objPool)에 저장한다.

### 실습 6 : 애노테이션을 이용하여 생성된 객체의 이름을 관리한다.(ApplicationContext06)
- com.eomcs.util.Component 애노테이션 추가
  - 빈의 이름을 설정하는 애노테이션을 정의한다.
- com.eomcs.lms.servlet.XXXServlet 변경
  - 클래스에 Component 애노테이션을 적용하여 이름을 지정한다.
- com.eomcs.util.ApplicationContext 클래스 변경
  - 객체를 객체풀에 저장할 때 Component 애노테이션에서 이름을 가져와서 저장한다.
  - Component 애노테이션이 없으면 그냥 클래스 이름으로 저장한다.
  - 외부에서 생성한 객체를 저장할 수 있도록 생성자 변경한다.
  - 외부에서 저장된 객체를 꺼낼 수 있도록 getBean() 메서드 추가한다.
- com.eomcs.util.ApplicationContext 클래스 변경
  - 외부에서 생성한 객체를 등록한 addBean() 메서드를 추가한다.
  - 내부에서 생성한 객체를 꺼낼 수 있도록 getBean() 메서드를 추가한다.
- com.eomcs.lms.ServerApp 변경
  - ApplicationContext를 사용하여 객체를 관리한다.

### 실습 7 : @Component 애노테이션이 붙은 객체만 관리한다.(ApplicationContext07)
- com.eomcs.lms.servlet.impl.XXXServletImpl 변경
  - 클래스에 Component 애노테이션을 적용한다.
- com.eomcs.util.ApplicationContext 변경
  - @Component가 붙은 클래스만 찾아내 객체를 생성한다.
  - 내부에 보관된 객체 정보를 출력하는 printBeans()를 추가한다.
- com.eomcs.ContextLoaderListener 변경
  - ApplicationContext를 생성한 후 printBeans()를 호출하여 보관된 객체 정보를 조회한다.

### 실습 8 : IoC 컨테이너의 이점을 활용해본다.
  - 클라이언트가 `/hello` 명령을 요청하면  `안녕하세요!` 로 응답한다.
  - IoC 컨테이너를 도입하면, 새 명령을 처리하는 서블릿이 추가되어도 기존 코드(ex. ServerApp)를 변경할 필요가 없다.

### 실습 9 : IoC 컨테이너의 이점을 활용해본다 - 2
- eom.eomcs.lms.servlet.HelloServlet 삭제
  - 기존 코드를 삭제하고 싶다면 해당 클래스를 삭제한다.
  - 기존의 다른 코드를 건드릴 필요가 없다.