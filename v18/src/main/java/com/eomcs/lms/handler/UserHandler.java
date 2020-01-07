package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.eomcs.lms.domain.User;
import com.eomcs.util.ArrayList;

public class UserHandler {

  private BufferedReader br;
  private ArrayList<User> userList;
  
  public UserHandler(BufferedReader br) {
    this.br = br;
    this.userList = new ArrayList<>();
  }
  
  public UserHandler(BufferedReader br, int capacity) {
    this.br = br;
    this.userList = new ArrayList<>(capacity);
  }
  
  public void addUser() throws Exception {
    User u = new User();

    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("번호 : ");
    u.setPersonNum(Integer.parseInt(br.readLine()));
    System.out.printf("이름 : ");
    u.setPersonName(br.readLine());
    System.out.printf("Email : ");
    u.setEmail(br.readLine());

    System.out.printf("비밀번호 : ");
    u.setPasswd(br.readLine());
    System.out.printf("사진 : ");
    u.setPicture(br.readLine());
    System.out.printf("연락처 : ");
    u.setPhoneNum(br.readLine());
    u.setJoinDay(new Date());
    userList.add(u);
    System.out.println("\nUser Save Complete.");
  }
  public void printUserList() throws Exception {
    User[] arr = userList.toArray(User[].class);
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%3s%5s%26s%5s%36s%6s\n", "번호", " ", "이름", " ", "연락처", " ", "가입일");
    for(User u : arr) {
      System.out.printf("%4d%5s%17s%13s%9s%6s\n", 
          u.getPersonNum(), u.getPersonName(), " ", u.getPhoneNum(), " " ,
          new SimpleDateFormat("yyyy-MM-dd").format(u.getJoinDay()));
    }
  }
}
