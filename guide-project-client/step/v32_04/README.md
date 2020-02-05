# 32_04 - 서버에 데이터를 요청하는 기능을 추가한다.

## 학습 목표

- 서버에 요청하고 응답 결과를 받을 수 있다.

## 실습 소스 및 결과

- src/main/java/com/eomcs/lms/domain/Board.java 추가
- src/main/java/com/eomcs/lms/ClientApp.java 변경

## 실습

### 실습 1 : v31 프로젝트에서 Board 클래스를 가져온다.

- com.eomcs.lms.domain 패키지를 생성한다.
- Board.java를 복사한다.

### 실습 2 : v31 프로젝트에서 게시물 관리를 처리하는 클래스 및 인터페이스를 가져온다. 

- BoardXXXXCommand.java를 com.eomcs.lms.handler에 복사한다.

### 실습 3 : Command 객체가 서버와 통신할 수 있도록 입출력 도구를 제공한다.

- ClientApp.java 변경
  - 서버와 연결하는 코드를 적용한다.
  - 서버와 통신할 수 있는 입출력 객체를 BoardXXXCommand 객체에 제공한다.

### 실습 4 : BoardListCommand가 작업할 때 서버와 통신하도록 처리한다.

- BoardListCommand.java 변경
  - 서버의 입출력 객체를 다룰 수 있도록 생성자를 변경한다.
  - 데이터를 입출력할 때 서버에 요청하도록 execute() 를 변경한다.

### 실습 5 : BoardAddCommand가 작업할 때 서버와 통신하도록 처리한다.
- BoardAddCommand.java 변경
  - 서버의 입출력 객체를 다룰 수 있도록 생성자를 변경한다.
  - 데이터를 입출력할 때 서버에 요청하도록 execute() 를 변경한다.

### 실습 6 : BoardDetailCommand가 작업할 때 서버와 통신하도록 처리한다.
- BoardDetailCommand.java 변경
  - 서버의 입출력 객체를 다룰 수 있도록 생성자를 변경한다.
  - 데이터를 입출력할 때 서버에 요청하도록 execute() 를 변경한다.

### 실습 7 : BoardUpdateCommand가 작업할 때 서버와 통신하도록 처리한다.
- BoardUpdateCommand.java 변경
  - 서버의 입출력 객체를 다룰 수 있도록 생성자를 변경한다.
  - 데이터를 입출력할 때 서버에 요청하도록 execute() 를 변경한다.

### 실습 8 : BoardDeleteCommand가 작업할 때 서버와 통신하도록 처리한다.
- BoardDeleteCommand.java 변경
  - 서버의 입출력 객체를 다룰 수 있도록 생성자를 변경한다.
  - 데이터를 입출력할 때 서버에 요청하도록 execute() 를 변경한다.

### 실습 9 : LessonXXXCommand가 작업할 때 서버와 통신하도록 처리한다.
- LessonXXXCommand.java 변경
  - 서버의 입출력 객체를 다룰 수 있도록 생성자를 변경한다.
  - 데이터를 입출력할 때 서버에 요청하도록 execute() 를 변경한다.

### 실습 10 : MemberXXXCommand가 작업할 때 서버와 통신하도록 처리한다.
- MemberXXXCommand.java 변경
  - 서버의 입출력 객체를 다룰 수 있도록 생성자를 변경한다.
  - 데이터를 입출력할 때 서버에 요청하도록 execute() 를 변경한다.

#### 추가사항
DataInputStream 객체와 DataOutputStream의 생성 위치를 바꾸면 프로그램이 blocking 된다. (서버도 마찬가지)
  - 내부적으로 in 객체가 생성되는 순간 특정 메시지를 읽기 위해 blocking 된다.
  - 프로토콜이 필요해짐

#### 추가사항
기존의 println() 메서드로 데이터를 전송하면 flush()를 따로 호출하지 않았다.
하지만 writeUTF() 메서드로 데이터를 전송할때는 flush()를 호출해야 한다.
- println() 메서드는 데이터를 보낼때 데이터 뒤에 개행문자를 붙인다.
- 개행문자가 있으면 출력 스트림은 자동으로 flush()를 수행한다.
  - 그렇다고 인위적으로 출력 데이터에 개행문자를 붙일 수 없다.
  - writeUTF()의 경우 개행문자 또한 바이트 배열이 변화한다.
- flush()는 NIC(Network Interface Card) 버퍼에 보관된 데이터가 전송된다.

#### 추가사항
- close()를 호출해도 자동으로 flush()가 호출된다.
- 소켓을 통한 객체 전달은 캐시된다. 
  - 따라서 출력 스트림의 reset() 메서드를 사용하여 기존에 출력한 직렬화 데이터를 무시하고 새로운 직렬화를 수행한다.