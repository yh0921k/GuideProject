# 35 - 스레드풀을 이용하여 스레드를 재사용하기

- `Flyweight` 디자인 패턴의 용도를 이해한다.
- `Flyweight` 디자인 패턴의 응용 기법인 `Pooling` 기법의 동작 원리를 이해한다.
- `Pooling` 기법을 사용하여 객체를 재활용할 수 있다.
- Thread를 종료시키지 않고 계속하여 활용할 수 있다.
- Thread의 wait() / notify() 메서드를 사용할 수 있다.
- Thread Pool을 적용할 수 있다. 

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습 1 : Thread Pool을 적용하여 Thread를 관리한다.

- com.eomcs.lms.ServerApp을 변경한다.
  - Thread Pool을 준비한다.
  - Thread를 생성할 때 Thread Pool을 사용한다. 


