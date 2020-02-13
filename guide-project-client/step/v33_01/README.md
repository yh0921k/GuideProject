# 33_01 - 서버의 `Stateless` 통신 방식에 맞춰 클라이언트 변경

## 학습목표
- `Stateful`을 `Stateless` 통신 방식으로 변경할 수 있다.
- `Stateless` 통신 방식의 특징과 장단점을 이해한다.
   
## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습
### 실습 1 : `Stateful` 통신 방식을 `Stateless` 통신 방식으로 변경한다.

- com.eomcs.lms.ClientApp을 변경한다.
  - 한 번 연결에 하나의 요청/응답을 수행한다.