package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.eomcs.lms.domain.Course;
import com.eomcs.lms.domain.Course;
import com.eomcs.util.ArrayList;

public class CourseHandler {
  
  private BufferedReader br;
  private ArrayList<Course> courseList;
  
  public CourseHandler(BufferedReader br) {
    this.br = br;
    this.courseList = new ArrayList<>();
  }
  
  public CourseHandler(BufferedReader br, int capacity) {
    this.br = br;
    this.courseList = new ArrayList<>(capacity);
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
  public void listCourse() throws Exception {
    Course[] arr = courseList.toArray(new Course[this.courseList.size()]);
    arr = courseList.toArray(arr);
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("%4s%30s%4s%30s%30s%4s%30s%15s\n", "번호", " ", "과목", " ", " ", "기간", " ", "총시간");
    for(Course c : arr) {
      String start = new SimpleDateFormat("yyyy-MM-dd").format(c.getStartDate());
      String end   = new SimpleDateFormat("yyyy-MM-dd").format(c.getEndDate());
      System.out.printf("%4s%5s%-29s%10s ~ %10s%12s\n", 
          c.getCourseNum(), " ", c.getCourseName(), start, end, c.getTotalHours());
    }
  }
  
  public void updateCourse() throws Exception {
    System.out.printf("-----------------------------------------------------------------------------\n");
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());
    Course oldCourse = courseList.get(index);
    
    if(oldCourse == null) {
      System.out.println("Board Number does not exists.");
      return;
    }
    // 시작일 종료일 총 수업시간 일 수업시간
    Course newCourse = new Course();  
    System.out.printf("과목 이름 : ");
    newCourse.setCourseName(br.readLine());
    System.out.printf("과목 내용(%s) : ", oldCourse.getCourseContent());
    String tmp = br.readLine();
    if(tmp.length() != 0) {
      newCourse.setCourseContent(tmp);
    } else {
      newCourse.setCourseContent(oldCourse.getCourseContent());
    }
    
    System.out.printf("시작일 : ");
    newCourse.setStartDate(new Date(String.join("/", br.readLine().split("-"))));
    System.out.printf("종료일 : ");
    newCourse.setEndDate(new Date(String.join("/", br.readLine().split("-"))));
    System.out.printf("총수업시간 : ");
    newCourse.setTotalHours(Integer.parseInt(br.readLine()));;
    System.out.printf("하루수업시간 : ");
    newCourse.setDayHours(Integer.parseInt(br.readLine()));

    this.courseList.set(index, newCourse);
    System.out.println("\nCourse Update Complete.");
  }
  
  public void deleteCourse() throws Exception {
    System.out.printf("Index : ");
    int index = Integer.parseInt(br.readLine());

    
    Course course = courseList.get(index);
    if(course == null) {
      System.out.println("Course Number does not exists.");
      return;
    }
    this.courseList.remove(index);
    System.out.println("Delete Complete.");
  }
}
