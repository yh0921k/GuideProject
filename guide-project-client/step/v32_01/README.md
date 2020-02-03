# 32_01 - 자바 클라이언트 프로젝트 만들기

## 학습 목표
- gradle을 이용하여 자바 프로젝트를 생성한다.
- 만든 프로젝트를 eclipse에서 import 할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ClientApp.java 추가

## 실습

### 실습 1 : 프로젝트 폴더를 생성한다.

- `guide-project-client` 디렉토리를 생성한다.

### 실습 2 : 프로젝트 폴더에 자바 프로젝트로 초기화한다.

- `$ gradle init` 실행

### 실습 3 : 이클립스 IDE에서 프로젝트를 추가한다.

- `build.gradle` 파일 변경
  - `eclipse` gradle 플러그인을 추가한다.
  - `javaCompile`을 설정한다.

- `$ gradle eclipse` 실행
  - gradle을 실행하여 이클립스 설정파일을 생성한다.

- 이클립스에서 프롤젝트 폴더를 추가한다.

#### 실습 4 : 프로젝트 시작 클래스를 변경한다.

- `ClientApp.java` 생성
  - 기존 `App.java`의 이름을 변경한다.
  - 소스코드를 정리한다.
  - "클라이언트 수업 관리 시스템입니다"를 출력한다.

- `src/test/java/ClientAppTest.java` 생성
  - 기존 `AppTest.java`의 이름을 변경한다.
  - 소스코드를 정리한다.

- `ClientApp.java`를 실행해 결과를 확인한다.

#### 추가사항

- `src/`, `README.md`, `build.gradle` 파일 및 폴더를 `v32_01/`로 백업한다.