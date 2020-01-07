package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Course;

public class CourseList {
  private static final int DEFAULT_CAPACITY = 100;
  private Course[] list;
  private int size;
  
  public CourseList() {
    this.list = new Course[DEFAULT_CAPACITY];
  }
  
  public CourseList(int capacity) {
    if(capacity > DEFAULT_CAPACITY && capacity < 10000)
      this.list = new Course[capacity];
    else
      this.list = new Course[DEFAULT_CAPACITY];
  }
  
  public void add(Course course) {
    if(this.size == this.list.length) {
      Course[] arr = new Course[this.list.length + (this.list.length >> 1)];
      System.arraycopy(this.list, 0, arr, 0, list.length);
      this.list = arr;
    }
    this.list[this.size++] = course;
  }
  
  public Course[] toArray() {
    return Arrays.copyOf(this.list, this.size);

  }
}
