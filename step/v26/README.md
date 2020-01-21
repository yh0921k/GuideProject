# 26 - `커맨드(Command)` 디자인 패턴을 적용하기

## 학습 목표

- `Command` 디자인 패턴의 개념과 용도를 이해한다.
- `Command` 디자인 패턴을 활용할 수 있다.


## 'Command' Design Pattern
#### 메소드의 객체화 설계 기법
- 명령어가 추가될 때마다 클래스에 메소드를 추가하는 대신에 새 클래스를 추가한다.
- 기존의 소스를 수정하지 않아서 유지보수에 좋다.
- 즉 기존 소스에 영향을 끼치지 않고 새 기능을 추가하는 방법이다.
- 명령 처리를 별도의 객체로 분리하므로 실행 내역을 관리하기 좋고,
  명령 처리를 원래 상태로 되돌리는 기능 등을 구현하기 쉽다.
- 인터페이스를 이용하면 메소드 호출 규칙을 단일화 할 수 있다.
  - 따라서 일관성을 높일 수 있다.
- 단 기능을 추가할 때마다 해당 기능을 처리하는 새 클래스가 필요하다.
  - 클래스 개수가 많아지는 단점이 있다.
  - 하지만 유지보수 측면에서 클래스 개수가 늘어나는 것이 단점이 아니다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/handler/Command.java 추가
- src/main/java/com/eomcs/lms/handler/LessonAddCommand.java 추가
- src/main/java/com/eomcs/lms/handler/LessonListCommand.java 추가
- src/main/java/com/eomcs/lms/handler/LessonDetailCommand.java 추가
- src/main/java/com/eomcs/lms/handler/LessonUpdateCommand.java 추가
- src/main/java/com/eomcs/lms/handler/LessonDeleteCommand.java 추가
- src/main/java/com/eomcs/lms/handler/MemberAddCommand.java 추가
- src/main/java/com/eomcs/lms/handler/MemberListCommand.java 추가
- src/main/java/com/eomcs/lms/handler/MemberDetailCommand.java 추가
- src/main/java/com/eomcs/lms/handler/MemberUpdateCommand.java 추가
- src/main/java/com/eomcs/lms/handler/MemberDeleteCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardAddCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardListCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardDetailCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardUpdateCommand.java 추가
- src/main/java/com/eomcs/lms/handler/BoardDeleteCommand.java 추가
- src/main/java/com/eomcs/lms/handler/LessonHandler.java 삭제
- src/main/java/com/eomcs/lms/handler/MemberHandler.java 삭제
- src/main/java/com/eomcs/lms/handler/BoardHandler.java 삭제
- src/main/java/com/eomcs/lms/App.java 변경

## 실습

### 훈련1. 메서드를 호출하는 쪽과 실행 쪽 사이의 규칙을 정의하라.

- Command.java
    - `App` 클래스와 명령을 처리하는 클래스 사이의 호출 규칙을 정의한다.

### 훈련2. 명령을 처리하는 각 메서드를 객체로 분리하라.

- LessonHandler.java
    - 수업 CRUD 각 기능을 `Command` 규칙에 따라 객체로 분리한다.
- MemberHandler.java
    - 수업 CRUD 각 기능을 `Command` 규칙에 따라 객체로 분리한다.
- BoardHandler.java
    - 수업 CRUD 각 기능을 `Command` 규칙에 따라 객체로 분리한다.
- App.java (App.java.01)
    - 명령어가 입력되면 `Command` 규칙에 따라 객체를 실행한다.
    - `/board2/xxx` 명령 처리는 삭제한다.

### 훈련 3: `Map`으로 `Command` 객체를 관리하라.

- App.java
    - 명령어를 `key`, `Command` 객체를 `value`로 하여 Map에 저장한다.
    - 각 명령에 대해 조건문으로 분기하는 부분을 간략하게 변경한다.

### 훈련 4: '/hello' 명령을 추가하라.
- 명령 입력시, 이름을 입력 받고 '이름님 안녕하세요' 출력
```
동작예시)
$ /hello
이름 : 홍길동
홍길동님 안녕하세요.

$
```

### 훈련 5: '/compute/plus' 명령을 추가하라.
```
동작예시)
$ /compute/plus

수1 : 100
수2 : 200
계산결과는 300 입니다.

$ 
```