package com.eomcs.lms;
import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
public class App2 {
  public static void main(String[] args) throws Exception {
    /* 변수 정의 영역 */ 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    final int SIZE = 100; 
    int tmpCnt = 0;       

    class User {
      int personNum;
      String personName;
      String PhoneNum;
      String picture;
      String passwd;
      String email;
      Date joinDay;
    }
    User[] users = new User[SIZE];
    /* 입력부 */
    for(int i = 0; i < SIZE; i++) {
      User u = new User();
      tmpCnt++;
      
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("번호 : ");
      u.personNum = Integer.parseInt(br.readLine());
      System.out.printf("이름 : ");
      u.personName= br.readLine();
      System.out.printf("Email : ");
      u.email = br.readLine();
      
      System.out.printf("비밀번호 : ");
      u.passwd = br.readLine();
      System.out.printf("사진 : ");
      u.picture = br.readLine();
      System.out.printf("연락처 : ");
      u.PhoneNum = br.readLine();
      u.joinDay = new Date();
      users[i] = u;
      
      System.out.printf("계속 입력하시겠습니까? (Y/n) : ");
      String tmp = br.readLine();
      if(tmp.equalsIgnoreCase("y")) 
        continue;
      else if(tmp.equalsIgnoreCase("n")) 
        break;
      else {
        System.out.println("잘못 입력하셨습니다.\n프로그램을 종료합니다.\n");
        return;
      }
    }

    /* 출력부 */ 
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%3s%5s%26s%5s%36s%6s\n", "번호", " ", "이름", " ", "연락처", " ", "가입일");
    for(int i=0; i<tmpCnt; i++) { 
      System.out.printf("%4d%5s%17s%13s%9s%6s\n", 
          users[i].personNum, users[i].personName, " ", users[i].PhoneNum, " " ,
          new SimpleDateFormat("yyyy-MM-dd").format(users[i].joinDay));
    }
    System.out.printf("-----------------------------------------------------------------------------\n");
  }

}
