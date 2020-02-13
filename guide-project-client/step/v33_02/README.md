# 33_02 - 매 요청마다 Proxy와 Command를 생성하는 코드를 개선한다.(리팩토링)

## 학습목표

- 리팩토링의 목적을 이해한다.
   
## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/proxy/XXXDaoProxy.java 변경
- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습
### 실습 1 : Proxy 클래스 생성 부분을 변경한다.

- com.eomcs.lms.dao.proxy.XXXDaoProxy를 변경한다.
  - 요청할 때 서버에 연결한다.
