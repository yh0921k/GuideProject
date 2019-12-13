package com.eomcs.lms;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
public class App2 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("번호 : ");
    int personNum = Integer.parseInt(scan.nextLine());
    System.out.print("이름 : ");
    String personName = scan.nextLine();
    System.out.print("이메일 : ");
    String email = scan.nextLine();
    System.out.print("암호 : ");
    String passwd = scan.nextLine();
    System.out.print("사진 : ");
    String picture = scan.nextLine();
    System.out.print("전화 : ");
    String phoneNum = scan.nextLine();
    
    System.out.println("------------------------------");
    System.out.printf("번호 : %d\n", personNum);
    System.out.printf("이름 : %s\n", personName);
    System.out.printf("이메일 : %s\n", email);
    System.out.printf("암호 : %s\n", passwd);
    System.out.printf("사진 : %s\n", picture);
    System.out.printf("전화 : %s\n", phoneNum);
    System.out.printf("가입일 : %s\n", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    scan.close();
  }

}
