# 32_04 - 클라이언트의 데이터 관리 요청을 처리한다.

## 학습 목표

- 클라이언트의 요청을 받을 수 있다.
- 클라이언트의 요청에 대해 데이터를 보낼 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습

### 실습 1 : 서비스를 시작할 때 클라이언트의 연결을 기다리는 코드를 작성한다.

- ServerApp.java 변경
  - ServerSocket을 준비한다.
  - 클라이언트 연결을 준비한다.

### 실습 2 : 클라이언트의 게시물 목록 요청("/board/list")을 처리한다.
- ServerApp.java 변경
  - processRequest() 메서드 변경
- ServerAppTest.java 추가
  - 서버의 응답 기능을 테스트 한다.
- Board.java 변경
  - 통신을 테스트할 때 게시물 필드 정보를 확인할 수 있도록 toString() 메서드를 오버라이딩한다.

### 실습 3 : 클라이언트의 게시물 등록 요청("/board/add")을 처리한다.
- ServerApp.java 변경
  - processRequest() 메서드 변경

### 실습 4 : 클라이언트의 게시물 조회 요청("/board/detail")을 처리한다.
- ServerApp.java 변경
  - processRequest() 메서드 변경

### 실습 5 : 클라이언트의 게시물 변경 요청("/board/update")을 처리한다.
- ServerApp.java 변경
  - processRequest() 메서드 변경

### 실습 6 : 클라이언트의 게시물 삭제 요청("/board/delete")을 처리한다.
- ServerApp.java 변경
  - processRequest() 메서드 변경

### 실습 7 : 클라이언트의 수업 관리 요청("/lesson/*)을 처리한다.
- ServerApp.java 변경
  - processRequest() 메서드 변경

### 실습 8 : 클라이언트의 회원 관리 요청("/member/*)을 처리한다.
- ServerApp.java 변경
  - processRequest() 메서드 변경