# 32_06 - Command 패턴을 적용하여 요청 처리 메서드를 객체화 한다.

## 학습 목표

- Command 패턴의 동작 원리를 이해한다.
- Command 패턴을 코드에 적용할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습

### 실습 1 : Command 패턴의 인터페이스 정의 

- com.eomcs.lms.servlet 패키지를 생성한다
- com.eomcs.lms.servlet.Servlet 인터페이스를 정의한다.

### 실습 2 : 각각의 요청 처리 메서드를 인터페이스 규칙에 따라 클래스로 정의한다.

- listBoard()를 BoardListServlet 클래스로 정의한다.
- addBoard()를 BoardAddServlet 클래스로 정의한다.
- detailBoard()를 BoardDetailServlet 클래스로 정의한다.
- updateBoard()를 BoardUpdateServlet 클래스로 정의한다.
- deleteBoard()를 BoardDeleteServlet 클래스로 정의한다.
---
- listLesson()를 LessonListServlet 클래스로 정의한다.
- addLesson()를 LessonAddServlet 클래스로 정의한다.
- detailLesson()를 LessonDetailServlet 클래스로 정의한다.
- updateLesson()를 LessonUpdateServlet 클래스로 정의한다.
- deleteLesson()를 LessonDeleteServlet 클래스로 정의한다.
---
- listMember()를 MemberListServlet 클래스로 정의한다.
- addMember()를 MemberAddServlet 클래스로 정의한다.
- detailMember()를 MemberDetailServlet 클래스로 정의한다.
- updateMember()를 MemberUpdateServlet 클래스로 정의한다.
- deleteMember()를 MemberDeleteServlet 클래스로 정의한다.
---



