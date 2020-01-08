package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;

public class UserHandler {

  private BufferedReader br;
  private ArrayList<Member> userList;
  
  public UserHandler(BufferedReader br) {
    this.br = br;
    this.userList = new ArrayList<>();
  }
  
  public UserHandler(BufferedReader br, int capacity) {
    this.br = br;
    this.userList = new ArrayList<>(capacity);
  }
  
  public void addUser() throws Exception {
    Member u = new Member();

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
  public void listUser() throws Exception {
    Member[] arr = userList.toArray(new Member[this.userList.size()]);
    arr = userList.toArray(arr);
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%3s%5s%26s%5s%36s%6s\n", "번호", " ", "이름", " ", "연락처", " ", "가입일");
    for(Member u : arr) {
      System.out.printf("%4d%5s%17s%13s%9s%6s\n", 
          u.getPersonNum(), u.getPersonName(), " ", u.getPhoneNum(), " " ,
          new SimpleDateFormat("yyyy-MM-dd").format(u.getJoinDay()));
    }
  }
  
  public void updateUser() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());
    
    Member oldUser = userList.get(index);
    if(oldUser == null) {
      System.out.println("User Number does not exists.");
      return;
    }
    
    Member newUser = new Member();
    newUser.setPersonNum(oldUser.getPersonNum());
    System.out.printf("이름(%s) : ", oldUser.getPersonName());
    String tmp = br.readLine();
    if(tmp.length() != 0) {
      newUser.setPersonName(tmp);
    } else {
      newUser.setPersonName(oldUser.getPersonName());
    }
        
    System.out.printf("Email : ");
    newUser.setEmail(br.readLine());
    System.out.printf("비밀번호 : ");
    newUser.setPasswd(br.readLine());
    System.out.printf("사진 : ");
    newUser.setPicture(br.readLine());
    System.out.printf("연락처 : ");
    newUser.setPhoneNum(br.readLine());
    newUser.setJoinDay(oldUser.getJoinDay());

    this.userList.set(index, newUser);
    System.out.println("\nUser Update Complete.");
  }
  
  public void deleteUser() throws Exception {
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());

    
    Member user = userList.get(index);
    if(user == null) {
      System.out.println("Board Number does not exists.");
      return;
    }
    this.userList.remove(index);
    System.out.println("Delete Complete.");
  }
}
