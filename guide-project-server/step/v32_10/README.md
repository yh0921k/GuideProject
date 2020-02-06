# 32_10 - 인터페이스를 이용하여 DAO 호출 규칙을 통일하기

## 학습 목표

- 인터페이스 문법의 존재 이유를 안다.
- 인터페이스를 정의하고 구현할 수 있다.

### 인터페이스

- 사용자(예 : Servlet)와 피사용자(예 : DAO) 사이에 호출 규칙을 정의하는 문법이다.
- 호출 규칙을 정의해두면 사용자 입장에서 피사용자측이 다른 객체로 바뀌더라도 코드를 변경할 필요가 없기 때문에 유지보수에 좋다.
- 피사용자 객체를 정의하는 개발자 입장에서도 인터페이스 규칙에 따라 만들면 되기 때문에 코드작성과 테스트가 용이하다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/BoardDao.java 인터페이스 추가
- src/main/java/com/eomcs/lms/dao/BoardObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/json/BoardJsonFileDao.java 변경

- src/main/java/com/eomcs/lms/dao/LessonDao.java 인터페이스 추가
- src/main/java/com/eomcs/lms/dao/LessonObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/json/LessonJsonFileDao.java 변경

- src/main/java/com/eomcs/lms/dao/MemberDao.java 인터페이스 추가
- src/main/java/com/eomcs/lms/dao/MemberObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/json/MemberJsonFileDao.java 변경

- src/main/java/com/eomcs/lms/servlet/BoardXXXServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/LessonXXXServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/MemberXXXServlet.java 변경
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습

### 실습 1 : BoardXXXServlet이 사용할 DAO 호출 규칙을 정의하고 구현한다.

- com.eomcs.lms.dao.BoardDao 인터페이스를 생성한다.
- com.eomcs.lms.dao.BoardObjectFileDao 클래스를 변경한다.
  - BoardDao 인터페이스를 구현한다.

- com.eomcs.lms.dao.json.BoardJsonFileDao 클래스를 변경한다.
  - BoardDao 인터페이스를 구현한다.

- com.eomcs.lms.servlet.BoardXXXServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 BoardDao 인터페이스로 변경한다.

- com.eomcs.lms.DataLoaderListener를 변경한다.
- com.eomcs.lms.ServerApp을 변경한다.

### 실습 2: LessonXXXServlet이 사용할 DAO 호출 규칙을 정의하고 구현한다.

- com.eomcs.lms.dao.LessonDao 인터페이스 생성한다.
- com.eomcs.lms.dao.LessonObjectFileDao 클래스를 변경한다.
  - LessonDao 인터페이스를 구현한다.

- com.eomcs.lms.dao.json.LessonJsonFileDao 클래스를 변경한다.
  - LessonDao 인터페이스를 구현한다.

- com.eomcs.lms.servlet.LessonXXXServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 LessonDao 인터페이스로 변경한다.

- com.eomcs.lms.DataLoaderListener 변경한다.
- com.eocms.lms.ServerApp 변경한다.

### 실습 3: MemberXXXServlet이 사용할 DAO 호출 규칙을 정의하고 구현한다.

- com.eomcs.lms.dao.MemberDao 인터페이스 생성한다.
- com.eomcs.lms.dao.MemberObjectFileDao 클래스를 변경한다.
  - MemberDao 인터페이스를 구현한다.

- com.eomcs.lms.dao.json.MemberJsonFileDao 클래스를 변경한다.
  - MemberDao 인터페이스를 구현한다.

- com.eomcs.lms.servlet.MemberXXXServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 MemberDao 인터페이스로 변경한다.

- com.eomcs.lms.DataLoaderListener 변경한다.
- com.eocms.lms.ServerApp 변경한다.
