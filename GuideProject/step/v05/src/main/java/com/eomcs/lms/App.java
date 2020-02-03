package com.eomcs.lms;
import java.util.Scanner;

public class App {  
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("번호 : ");
    int subNum = Integer.parseInt(scan.nextLine());
    System.out.print("수업명 : ");
    String subName = scan.nextLine();
    System.out.print("수업내용 : ");
    String subContents = scan.nextLine();
    System.out.print("시작일 : ");
    String sDate = scan.nextLine();
    System.out.print("종료일 : ");
    String eDate = scan.nextLine();
    System.out.print("총수업시간 : ");
    int totalTime = Integer.parseInt(scan.nextLine());
    System.out.print("일수업시간 : ");
    int dayTime = Integer.parseInt(scan.nextLine());
    
    System.out.println("------------------------------");
    System.out.printf("번호 : %d\n", subNum);
    System.out.printf("수업명 : %s\n", subName);
    System.out.printf("설명 : %s\n", subContents);
    System.out.printf("기간 : %s ~ %s\n", sDate, eDate);
    System.out.printf("총수업시간 : %d hours\n", totalTime);
    System.out.printf("일수업시간 : %d hours\n", dayTime);
    
    scan.close();
  }
}
