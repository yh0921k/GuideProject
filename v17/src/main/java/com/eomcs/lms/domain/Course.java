package com.eomcs.lms.domain;

import java.util.Date;

public class Course {
  private int    courseNum;
  private String courseName;
  private String courseContent;
  private Date   startDate;
  private Date   endDate;
  private int    totalHours;
  private int    dayHours;
  
  public int getCourseNum() {
    return courseNum;
  }
  public void setCourseNum(int courseNum) {
    this.courseNum = courseNum;
  }
  public String getCourseName() {
    return courseName;
  }
  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }
  public String getCourseContent() {
    return courseContent;
  }
  public void setCourseContent(String courseContent) {
    this.courseContent = courseContent;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public int getTotalHours() {
    return totalHours;
  }
  public void setTotalHours(int totalHours) {
    this.totalHours = totalHours;
  }
  public int getDayHours() {
    return dayHours;
  }
  public void setDayHours(int dayHours) {
    this.dayHours = dayHours;
  } 
}
