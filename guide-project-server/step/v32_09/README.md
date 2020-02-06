# 32_09 - 파일에 데이터를 저장할 때 JSON 형식을 사용한다.

## 학습 목표

- JSON(JavaScript Object Notation) 형식을 이해한다.
- Gson 라이브러리를 이용하여 JSON 형식의 데이터를 다룰 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/json 패키지 추가
- src/main/java/com/eomcs/lms/dao/json/AbstractJsonFileDao.java 추가
- src/main/java/com/eomcs/lms/dao/json/BoardJsonFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/json/LessonJsonFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/json/MemberJsonFileDao.java 변경
- src/main/java/com/eomcs/lms/servlet/BoardXXXServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/LessonXXXServlet.java 변경
- src/main/java/com/eomcs/lms/servlet/MemberXXXServlet.java 변경
- src/main/java/com/eomcs/lms/DataLoaderListener.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습

### 실습 1 : JSON 형식으로 데이터를 저장하고 로딩할 super class를 정의한다.

- com.eomcs.lms.dao.json 패키지를 생성한다.
- com.eomcs.lms.dao.json.AbstractJsonFileDao 클래스를 생성한다.

### 실습 2 : BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경한다.

- com.eomcs.lms.dao.BoardObjectFileDao 변경
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf() 메서드를 오버라이딩 한다.

### 실습 3 : LessonObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경한다.

- com.eomcs.lms.dao.LessonObjectFileDao 변경
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf() 메서드를 오버라이딩 한다.

### 실습 4 : MemberObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경한다.

- com.eomcs.lms.dao.BoardMemberObjectFileDao 변경
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf() 메서드를 오버라이딩 한다.

### 실습 5: XXXObjectFileDao 대신 XXXJsonFileDao를 사용하도록 서블릿 클래스를 변경한다.

- com.eomcs.lms.servlet.BoardXXXServlet 변경한다.
- com.eomcs.lms.servlet.LessonXXXServlet 변경한다.
- com.eomcs.lms.servlet.MemberXXXServlet 변경한다.

### 실습 6: 애플리케이션이 시작할 때 XXXObjectFileDao 대신 XXXJsonFileDao를 준비한다.

- com.eomcs.lms.DataLoaderListener 변경한다.

### 실습 7: XXXObjectFileDao 대신 XXXJsonFileDao를 서블릿 객체에 주입한다.

- com.eocms.lms.ServerApp 변경한다.
 



  
  