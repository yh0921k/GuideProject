package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class App {  
  public static void main(String[] args) throws Exception {
    /* 변수 정의 영역 */ 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 대신 Scanner 사용 가능
    final int SIZE = 100; // 100개의 과목을 저장가능, 이후 더 많이 필요하면 이 변수의 크기만 늘리면 됨
    int tmpCnt = 0;       // 배열 인덱스로 사용할 변수
    
    class Course {
      int courseNum;
      String courseName;
      String courseContent;
      Date startDate;
      Date endDate;
      int totalHours;
      int dayHours;
    }
    Course[] courses = new Course[SIZE];

    /* 입력부 */
    for(int i = 0; i < SIZE; i++) { // 조건이 항상 참이므로 반복문을 계속 수행, while문 대신 do~while이나 for문으로 변경 가능
      Course c = new Course();
      tmpCnt++;
      
      System.out.printf("-----------------------------------------------------------------------------\n");
      System.out.printf("과목 번호 : ");
      c.courseNum = Integer.parseInt(br.readLine());
      System.out.printf("과목 이름 : ");
      c.courseName = br.readLine();
      System.out.printf("과목 내용 : ");
      c.courseContent = br.readLine();
      
      System.out.printf("시작일 : ");
      c.startDate = new Date(String.join("/", br.readLine().split("-")));
      System.out.printf("종료일 : ");
      c.endDate = new Date(String.join("/", br.readLine().split("-")));
      System.out.printf("총수업시간 : ");
      c.totalHours = Integer.parseInt(br.readLine());
      System.out.printf("하루수업시간 : ");
      c.dayHours = Integer.parseInt(br.readLine());
      courses[i] = c;
      
      System.out.printf("계속 입력하시겠습니까? (Y/n) : ");
      String tmp = br.readLine();
      if(tmp.equalsIgnoreCase("y")) // y가 입력되면 반복문의 조건 검사로 이동
        continue;
      else if(tmp.equalsIgnoreCase("n")) // 만약 n이 입력되면 break 문으로 반복문을 빠져나감
        break;
      else {
        System.out.println("잘못 입력하셨습니다.\n프로그램을 종료합니다.\n");
        return;
      }
    }

    /* 출력부 */ 
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%30s%4s%30s%30s%4s%30s%15s\n", "번호", " ", "과목", " ", " ", "기간", " ", "총시간");
    for(int i=0; i<tmpCnt; i++) { // j가 0 ~ 위에서 증가한 index 까지 반복문 수행
      String start = new SimpleDateFormat("yyyy-MM-dd").format(courses[i].startDate);
      String end   = new SimpleDateFormat("yyyy-MM-dd").format(courses[i].endDate);
      System.out.printf("%4s%5s%-29s%10s ~ %10s%12s\n", 
          courses[i].courseNum, " ", courses[i].courseName, start, end, courses[i].totalHours);
    }
    System.out.printf("-----------------------------------------------------------------------------\n");
  }
}
