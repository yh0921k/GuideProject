# 33_03 - 서버 연결 부분을 캡슐화하기

## 학습목표

- 리팩토링의 목적을 이해한다.
   
## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/proxy/Worker.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/DaoProxyHelper.java 추가
- src/main/java/com/eomcs/lms/dao/proxy/XXXDaoProxy.java 변경
- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습
### 실습 1 : 서버와 통신을 담당하는 실제 작업의 규칙을 정의한다.

- com.eomcs.lms.dao.proxy.Worker 인터페이스를 정의한다.

### 실습 2 : DaoProxy를 도와 서버와의 연결을 담당할 객체를 정의한다.

- com.eomcs.lms.dao.proxy.DaoProxyHelper 클래스를 정의한다.

### 실습 3 : DaoProxyHelper를 사용하도록 XXXDaoProxy를 변경한다.

- com.eomcs.lms.dao.proxy.XXXDaoProxy를 변경한다.

### 실습 4 : DaoProxyHelper가 추가된 것에 맞춰 ClientApp을 변경한다.

- com.eomcs.lms.ClientApp을 변경한다.

