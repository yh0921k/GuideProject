# 28_03 - 파일 포맷으로 JSON 도입하기

## 학습 목표

- 외부 라이브러리를 가져와서 프로젝트에 적용할 수 있다.
- JSON 포맷을 이해하고 이점을 이해한다.
- Google JSON 라이브러리를 사용할 수 있다.

## 실습 소스 및 결과

- build.gradle 변경
- src/main/java/com/eomcs/lms/App.java 변경

## 실습

### 훈련 1 : Gradle 스크립트 파일(build.gradle)에 Google JSON 라이브러리를 추가한다.

- mvnrepository.com 에서 라이브러리 검색
  - json.org 사이트에서 자바 라이브러리 확인
  - 'gson' 키워드로 검색

- build.gradle 을 편집한다.
  - 의존 라이브러리 블럭(dependencies)에 gson 정보를 추가한다.

- 이클립스 설정 파일을 갱신한다.
  - 'gradle eclipse' 실행
  - 이클립스에서 해당 프로젝트를 'refresh'
  - 'Referenced Libraries' 노드에서 gson 라이브러리 파일이 추가된 것을 확인한다.

### 훈련 2 : 게시물 데이터를 저장할 때 JSON 형식으로 저장한다.

- App.java
  - saveBoardData() 변경
  - loadBoardData() 변경

### 훈련 3 : 회원 데이터를 저장하고 읽을 때 SON 형식을 사용한다.

- App.java
  - saveMemberDate().java 변경
  - loadMemberData().java 변경

### 훈련 4 : 수업 데이터를 저장하고 읽을 때 SON 형식을 사용한다.

- App.java
  - saveLessonData().java 변경
  - loadLessonData().java 변경

### 훈련 5 : Arrays의 메서드를 활용하여 배열을 List 객체로 만든다.
- App.java
  - 현재 코드는 객체 배열에서 객체를 하나씩 빼내 XXXList에 add() 하는 방식으로 구성되어 있다.
  ```java
  // loadBoardData() 예시
  Board[] boards = new Gson().fromJson(in, Board[].class);

      for (Board board : boards) {
        boardList.add(board);
      }
  ```
  - 해당 부분의 코드를 변경한다.

#### 추가사항
JSON을 사용하는 이유는, 입출력 데이터에 "내용,내용" 형식의 데이터가 있다면 csv 파일은 해당 구분자로 데이터를 구분하기 때문에 문제가 발생한다.
따라서 JSON 파일 포맷을 사용한다. JSON에서 대괄호는 리스트(배열)을 나타내며 중괄호는 객체를 나타낸다.

## JSON 데이터 포맷 특징
- 문자열로 데이터를 표현한다.
- '프로퍼티:값' 방식으로 객체의 값을 저장한다.
- 바이너리 방식에 비해 데이터가 커지는 문제가 있지만 모든 프로그래밍 언어에서 다룰 수 있다.
  - 따라서 이기종 플랫폼(OS, 프로그래밍 언어) 간에 데이터를 교환할 때 많이 사용한다.
