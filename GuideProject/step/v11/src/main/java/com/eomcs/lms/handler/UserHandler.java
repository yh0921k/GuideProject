package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserHandler {
  static class User {
    int    personNum;
    String personName;
    String PhoneNum;
    String picture;
    String passwd;
    String email;
    Date   joinDay;
  }
  static final int USER_SIZE   = 100;
  static int userCnt   = 0;
  static User[]   users   = new User[USER_SIZE];
  public static BufferedReader br;
  
  public static void addUser() throws Exception {
    User u = new User();

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
    users[userCnt++] = u;
    System.out.println("\nUser Save Complete.");
  }
  public static void printUserList() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%3s%5s%26s%5s%36s%6s\n", "번호", " ", "이름", " ", "연락처", " ", "가입일");
    for(int i=0; i<userCnt; i++) { 
      System.out.printf("%4d%5s%17s%13s%9s%6s\n", 
          users[i].personNum, users[i].personName, " ", users[i].PhoneNum, " " ,
          new SimpleDateFormat("yyyy-MM-dd").format(users[i].joinDay));
    }
  }
}
