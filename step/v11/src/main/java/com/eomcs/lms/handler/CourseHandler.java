package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseHandler {
  static class Course {
    int    courseNum;
    String courseName;
    String courseContent;
    Date   startDate;
    Date   endDate;
    int    totalHours;
    int    dayHours;
  }  
  
  static final int COURSE_SIZE = 100;
  static Course[] courses = new Course[COURSE_SIZE];
  static int courseCnt = 0;   
  public static BufferedReader br;
  
  public static void addCourse() throws Exception {
    Course c = new Course();          

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

    courses[courseCnt++] = c;
    System.out.println("\nCourse Save Complete.");
  }
  public static void printCourseList() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%30s%4s%30s%30s%4s%30s%15s\n", "번호", " ", "과목", " ", " ", "기간", " ", "총시간");
    for(int i=0; i<courseCnt; i++) { 
      String start = new SimpleDateFormat("yyyy-MM-dd").format(courses[i].startDate);
      String end   = new SimpleDateFormat("yyyy-MM-dd").format(courses[i].endDate);
      System.out.printf("%4s%5s%-29s%10s ~ %10s%12s\n", 
          courses[i].courseNum, " ", courses[i].courseName, start, end, courses[i].totalHours);
    }
  }
}
