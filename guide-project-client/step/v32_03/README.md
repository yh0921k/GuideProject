# 32_03 - LMS 명령을 입력 받는 기능을 추가한다.

## 학습 목표

- 사용자로부터 명령을 입력 받을 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/util 패키지 추가
- src/main/java/com/eomcs/lms/util/Prompt.java 추가
- src/main/java/com/eomcs/lms/handler 패키지 추가
- src/main/java/com/eomcs/lms/handler/Command.java 추가
- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습

### 실습 1 : v31 프로젝트의 App 클래스에서 명령을 입력 받는 부분을 가져온다.

- com.eomcs.util 패키지를 생성한다.
- com.eomcs.util.Prompt 클래스를 가져온다.

- com.eomcs.lms.handler 패키지를 생성한다.
- com.eomcs.lms.handler.Command 인터페이스를 가져온다.

- ClientApp.java 변경
  - 사용자가 입력한 명령을 반복하여 처리하는 코드를 가져온다.

### 추가사항
- 현재 클라이언트의 명령어는 `quit`, `history`, `history2`가 가능하다.
- 커맨드 관련 코드들은 추가하지 않았으므로 아직 불가능하다.