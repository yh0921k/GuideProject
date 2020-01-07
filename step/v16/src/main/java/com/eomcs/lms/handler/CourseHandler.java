package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.eomcs.lms.domain.Course;

public class CourseHandler {
  
  private BufferedReader br;
  private CourseList courseList;
  
  public CourseHandler(BufferedReader br) {
    this.br = br;
    this.courseList = new CourseList();
  }
  
  public CourseHandler(BufferedReader br, int capacity) {
    this.br = br;
    this.courseList = new CourseList(capacity);
  }
  public void addCourse() throws Exception {
    Course c = new Course();          

    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("과목 번호 : ");
    c.setCourseNum(Integer.parseInt(br.readLine()));
    System.out.printf("과목 이름 : ");
    c.setCourseName(br.readLine());
    System.out.printf("과목 내용 : ");
    c.setCourseContent(br.readLine());

    System.out.printf("시작일 : ");
    c.setStartDate(new Date(String.join("/", br.readLine().split("-"))));
    System.out.printf("종료일 : ");
    c.setEndDate(new Date(String.join("/", br.readLine().split("-"))));
    System.out.printf("총수업시간 : ");
    c.setTotalHours(Integer.parseInt(br.readLine()));
    System.out.printf("하루수업시간 : ");
    c.setDayHours(Integer.parseInt(br.readLine()));

    courseList.add(c);
    System.out.println("\nCourse Save Complete.");
  }
  public void printCourseList() throws Exception {
    Course[] courses = courseList.toArray();
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%30s%4s%30s%30s%4s%30s%15s\n", "번호", " ", "과목", " ", " ", "기간", " ", "총시간");
    for(Course c : courses) { 
      String start = new SimpleDateFormat("yyyy-MM-dd").format(c.getStartDate());
      String end   = new SimpleDateFormat("yyyy-MM-dd").format(c.getEndDate());
      System.out.printf("%4s%5s%-29s%10s ~ %10s%12s\n", 
          c.getCourseNum(), " ", c.getCourseName(), start, end, c.getTotalHours());
    }
  }
}
