# 31_01 - `Observer` 디자인 패턴을 적용하여 클래스 구조를 변경

## 학습목표
- 'Observer' 디자인 패턴의 용도와 이점을 이해한다.
- Observer 호출 규칙을 정의할 수 있다.
- Observer 구현체를 등록하고 제거하는 메서드를 추가할 수 있다.
- 특정 상태에서 Observer 호출할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/context/ApplicationContextListener.java 추가
- src/main/java/com/eomcs/lms/App.java 변경

## 실습 

### 실습 1 : App 클래스의 static 필드와 메서드를 instance 멤버로 전환한다.
- App.java (App.java.01)
  - static 필드와 static 메서드를 instance 필드와 instance 메서드로 전환한다.
  - 보통 실무에서는 클래스의 일반적인 구조로 인스턴스 필드와 메서드를 사용한다.

### 실습 2 : 애플리케이션 시작하거나 종료될 때 호출될 Observer의 규칙을 정의한다.
- ApplicationContextListener.java (ApplicationContextListener.java.01)
    - Observer가 갖춰야 할 규칙을 정의한다.
    - 애플리케이션이 시작할 때 자동으로 호출할 메서드의 규칙을 정의한다.
    - 애플리케이션을 종료하기 전에 자동으로 호출할 메서드의 규칙을 정의한다.

### 실습 3 : App 객체에 Observer를 등록하고 제거하는 기능을 추가한다.
- App.java
  - Observer를 등록하는 메서드를 추가한다.
  - Observer를 제거하는 메서드를 추가한다.
  - 어플리케이션을 시작할 때 Observer를 호출한다.
  - 어플리케이션을 종료할 때 Observer를 호출한다.

### 추가사항
Observer 패턴을 적용하기 위해 클래스의 구조를 변경했으며, 아직 Observer를 등록하고 사용하지 않았다.