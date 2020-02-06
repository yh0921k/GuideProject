# 32_08 - DAO 클래스의 공통점을 추출해 super 클래스로 정의한다. (generalization 수행)

## 학습 목표

- 상속의 기법 중 generalization을 이해한다.
- generalization을 구현할 수 있다.

## 상속

- specialization
  - super class를 상속 받아 특별한 기능을 수행하는 sub class를 만드는 것

- generalization
  - 클래스들의 공통점을 추출해 super class로 만든 후에 상속 관계를 맺는 것

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/dao/AbstractObjectFileDao.java 추가
- src/main/java/com/eomcs/lms/dao/BoardObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/LessonObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/dao/MemberObjectFileDao.java 변경
- src/main/java/com/eomcs/lms/ServerApp.java 변경

## 실습

### 실습 1 : DAO의 공통점을 추출해 super class로 정의한다.

- com.eomcs.lms.dao.AbstractObjectFileDao 클래스를 생성한다.

### 실습 2 : BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경한다.

- com.eomcs.lms.dao.BoardObjectFileDao 변경
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf() 메서드를 오버라이딩 한다.

### 실습 3 : LessonObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경한다.

- com.eomcs.lms.dao.LessonObjectFileDao 변경
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf() 메서드를 오버라이딩 한다.

### 실습 4 : MemberObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경한다.

- com.eomcs.lms.dao.BoardMemberObjectFileDao 변경
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf() 메서드를 오버라이딩 한다.