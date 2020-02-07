# 32_11 - 서버에서 제공한 프록시 객체를 사용하여 데이터를 처리하기

## 학습 목표

- 프록시 패턴의 이점을 이해한다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/proxy 패키지 생성
- src/main/java/com/eomcs/lms/dao/BoardDao.java 추가
- src/main/java/com/eomcs/lms/dao/LessonDao.java 추가
- src/main/java/com/eomcs/lms/dao/MemberDao.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/BoardDaoProxy.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/LessonDaoProxy.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/MemberDaoProxy.java 추가
- src/main/java/com/eomcs/lms/handler/BoardXXXCommand.java 변경
- src/main/java/com/eomcs/lms/handler/LessonXXXCommand.java 변경
- src/main/java/com/eomcs/lms/handler/MemberXXXCommand.java 변경
- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습

### 실습 1 : 서버 프로젝트(v32_11)에서 DAO 프록시 클래스를 가져온다.

- com.eomcs.lms.dao.proxy 패키지와 그 하위 클래스를 모두 가져온다.
- com.eomcs.lms.dao.XXXDao 인터페이스를 가져온다.

### 실습 2 : BoardXXXCommand 객체가 BoardDaoProxy 객체를 사용하여 데이터를 처리하게 한다.

- com.eomcs.lms.handler.BoardXXXCommand 클래스를 변경한다.
  - 입출력 스트림 필드를 제거한다.
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리한다.

- com.eomcs.lms.ClientApp 변경
  - BoardDaoProxy 객체를 생성한다.
  - BoardXXXCommand 객체에 주입한다.

### 실습 2 : LessonXXXCommand 객체가 LessonDaoProxy 객체를 사용하여 데이터를 처리하게 한다.

- com.eomcs.lms.handler.LessonXXXCommand 클래스를 변경한다.
  - 입출력 스트림 필드를 제거한다.
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리한다.

- com.eomcs.lms.ClientApp 변경
  - LessonDaoProxy 객체를 생성한다.
  - LessonXXXCommand 객체에 주입한다.

### 실습 2 : MemberXXXCommand 객체가 MemberDaoProxy 객체를 사용하여 데이터를 처리하게 한다.

- com.eomcs.lms.handler.MemberXXXCommand 클래스를 변경한다.
  - 입출력 스트림 필드를 제거한다.
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리한다.

- com.eomcs.lms.ClientApp 변경
  - MemberDaoProxy 객체를 생성한다.
  - MemberXXXCommand 객체에 주입한다.

