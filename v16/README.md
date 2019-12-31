# 16 - UI 코드와 데이터 처리 코드를 분리하기

## 학습 목표

- 캡슐화 기법을 이용하여 데이터 처리 코드를 별개의 클래스로 분리할 수 있다.
- 배열 복제를 통해 배열의 크기를 늘릴 수 있다.
- 역할에 따라 클래스를 분리하는 방법과 이점을 이해한다.  

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/handler/CourseList.java 추가
- src/main/java/com/eomcs/lms/handler/UserList.java 추가
- src/main/java/com/eomcs/lms/handler/BoardList.java 추가
- src/main/java/com/eomcs/lms/handler/CourseHandler.java 변경
- src/main/java/com/eomcs/lms/handler/UserHandler.java 변경
- src/main/java/com/eomcs/lms/handler/BoardHandler.java 변경

## 실습

### 작업1) CourseHandler에서 데이터 처리 코드를 분리하라.

- CourseList.java
    - `CourseHandler`에서 데이터 처리 코드를 이 클래스로 옮긴다.
    - 수업 데이터 배열을 리턴하는 toArray() 메서드를 정의한다.
    - 수업 데이터를 저장하는 add() 메서드를 정의한다.
    - 기본 생성자와 배열의 초기 크기를 설정하는 생성자를 정의한다.  
- CourseHandler.java
    - `CourseList` 클래스를 사용하여 데이터를 처리한다.

### 작업2) UserHandler에서 데이터 처리 코드를 분리하라.

- UserList.java
    - `UserHandler`에서 데이터 처리 코드를 이 클래스로 옮긴다.
    - 회원 데이터 배열을 리턴하는 toArray() 메서드를 정의한다.
    - 회원 데이터를 저장하는 add() 메서드를 정의한다.
    - 기본 생성자와 배열의 초기 크기를 설정하는 생성자를 정의한다.  
- UserHandler.java
    - `UserList` 클래스를 사용하여 데이터를 처리한다.

### 작업3) BoardHandler에서 데이터 처리 코드를 분리하라.

- BoardList.java
    - `BoardHandler`에서 데이터 처리 코드를 이 클래스로 옮긴다.
    - 게시물 데이터 배열을 리턴하는 toArray() 메서드를 정의한다.
    - 게시물 데이터를 저장하는 add() 메서드를 정의한다.
    - 기본 생성자와 배열의 초기 크기를 설정하는 생성자를 정의한다.  
- BoardHandler.java
    - `BoardList` 클래스를 사용하여 데이터를 처리한다.

### 추가사항
배열의 크기를 동적으로 늘려주는 작업을 추가함
배열을 복사할 때 반복문으로 구현하는 것이 아닌, Arrays.copyOf(), System.arraycopy(), clone()을 사용할 수 있다.
