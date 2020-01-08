# 19 - CRUD(Create/Retrieve/Update/Delete) 기능 완성

## 학습 목표

- 데이터를 등록/조회/수정/삭제하는 기능(CRUD)을 구현할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/util/ArrayList.java 변경
- src/main/java/com/eomcs/lms/handler/CourseHandler.java 변경
- src/main/java/com/eomcs/lms/handler/UserHandler.java 변경
- src/main/java/com/eomcs/lms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/lms/domain/Course.java 변경
- src/main/java/com/eomcs/lms/domain/User.java 변경
- src/main/java/com/eomcs/lms/domain/Board.java 변경
- src/main/java/com/eomcs/lms/App.java 변경

## 실습

### 작업1) ArrayList 클래스에 항목 값을 조회하고 변경하고 삭제하는 기능을 추가하라.

- ArrayList.java
  - 목록의 특정 위치에 저장된 항목을 꺼내주는 get()을 정의한다.
  - 목록의 특정 위치에 저장된 항목을 바꾸는 set()을 정의한다.
  - 목록의 특정 위치에 저장된 항목을 삭제하는 remove()를 정의한다.


### 작업2) 수업 데이터의 상세조회, 변경, 삭제 기능을 추가하라.

- CourseHandler.java (CourseHandler.java.01)
  - 상세조회 기능을 수행하는 detailCourse()을 정의한다.
  - 변경 기능을 수행하는 updateCourse()을 정의한다.
  - 삭제 기능을 수행하는 deleteCourse()을 정의한다.
- App.java
  - 상세조회, 변경, 삭제 명령에 대한 분기문을 추가한다.

#### 실행 결과

```
명령> /Course/list
1, 자바 프로젝트 실습     , 2019-01-02 ~ 2019-05-28, 1000
2, 자바 프로그래밍 기초    , 2019-02-01 ~ 2019-02-28,  160
3, 자바 프로그래밍 고급    , 2019-03-02 ~ 2019-03-30,  160
4, 서블릿/JSP 프로그래밍   , 2019-04-02 ~ 2019-05-30,  150

명령> /Course/detail
번호? 2
수업명: 자바 프로젝트 실습
수업내용: 자바 프로젝트를 통한 자바 언어 활용법 익히기
기간: 2019-01-02 ~ 2019-05-28
총수업시간: 1000
일수업시간: 8

명령> /Course/detail
번호? 20
해당 수업을 찾을 수 없습니다.

명령> /Course/update
번호? 2
수업명(자바 프로젝트 실습)? 자바 프로젝트 단계별 실습
수업내용?  <=== 입력하지 않으면 기존 값 사용
시작일(2019-01-02)?
종료일(2019-05-28)?
총수업시간(1000)? 960
일수업시간(8)?
수업을 변경했습니다.

명령> /Course/update
번호? 20
해당 수업을 찾을 수 없습니다.

명령> /Course/delete
번호? 2
수업을 삭제했습니다.

명령> /Course/delete
번호? 20
해당 수업을 찾을 수 없습니다.
```

### 작업3) CourseHandler 코드를 리팩토링하라.

- CourseHandler.java
    - 저장된 목록에서 수업 번호로 항목을 찾는 코드를 indexOfCourse() 메서드로 분리한다.
- Course.java
    - 인스턴스 복제를 할 수 있도록 java.lang.Cloneable 인터페이스를 구현한다.
    - clone()을 오버라이딩 한다.


### 작업4) 회원 데이터의 상세조회, 변경, 삭제 기능을 추가하라.

- UserHandler.java
    - 상세조회 기능을 수행하는 detailUser()을 정의한다.
    - 변경 기능을 수행하는 updateUser()을 정의한다.
    - 삭제 기능을 수행하는 deleteUser()을 정의한다.
    - 저장된 목록에서 회원 번호로 항목을 찾는 indexOfUser()를 정의한다.
- User.java
    - 인스턴스 복제를 할 수 있도록 java.lang.Cloneable 인터페이스를 구현한다.
    - clone()을 오버라이딩 한다.
- App.java
    - 상세조회, 변경, 삭제 명령에 대한 분기문을 추가한다.

#### 실행 결과

```
명령> /User/list
1, 홍길동 , hong@test.com       , 1111-2222      , 2019-01-01
2, 임꺽정 , lim@test.com        , 1111-2223      , 2019-01-01
3, 전봉준 , jeon@test.com       , 1111-2224      , 2019-01-01

명령> /User/detail
번호? 2
이름: 홍길동
이메일: hong@test.com
암호: 1111
사진: hong.png
전화: 1111-2222
가입일: 2019-01-01

명령> /User/detail
번호? 20
해당 학생을 찾을 수 없습니다.

명령> /User/update
번호? 1
이름(홍길동)?     <=== 입력하지 않으면 기존 값 사용
이메일(hong@test.com)?
암호(1111)?
사진(hong.png)?
전화(1111-2222)?
회원을 변경했습니다.

명령> /User/update
번호? 20
해당 회원을 찾을 수 없습니다.

명령> /User/delete
번호? 2
회원을 삭제했습니다.

명령> /User/delete
번호? 20
해당 회원을 찾을 수 없습니다.
```

### 작업5) 게시글 데이터의 상세조회, 변경, 삭제 기능을 추가하라.

- BoardHandler.java
    - 상세조회 기능을 수행하는 detailBoard()을 정의한다.
    - 변경 기능을 수행하는 updateBoard()을 정의한다.
    - 삭제 기능을 수행하는 deleteBoard()을 정의한다.
    - 저장된 목록에서 회원 번호로 항목을 찾는 indexOfBoard()를 정의한다.
- Board.java
    - 인스턴스 복제를 할 수 있도록 java.lang.Cloneable 인터페이스를 구현한다.
    - clone()을 오버라이딩 한다.
- App.java
    - 상세조회, 변경, 삭제 명령에 대한 분기문을 추가한다.

#### 실행 결과

```
명령> /board/list
1, 게시글입니다.                , 2019-01-01, 0
2, 두 번째 게시글입니다.        , 2019-01-01, 0
3, 세 번째 게시글입니다.        , 2019-01-01, 0

명령> /board/detail
번호? 1
내용: 게시글입니다.
작성일: 2019-01-01

명령> /board/detail
번호? 20
해당 게시글을 찾을 수 없습니다.

명령> /board/update
번호? 1
내용?      <=== 입력하지 않으면 기존 값 사용
게시글을 변경했습니다.

명령> /board/update
번호? 20
해당 게시글을 찾을 수 없습니다.

명령> /board/delete
번호? 2
게시글을 삭제했습니다.

명령> /board/delete
번호? 20
해당 게시글을 찾을 수 없습니다.
```
