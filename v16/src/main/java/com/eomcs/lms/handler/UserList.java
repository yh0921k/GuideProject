package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.User;

public class UserList {
  private static final int DEFAULT_CAPACITY = 100;
  private User[] list;
  private int size;
    
  public UserList() {
    this.list = new User[DEFAULT_CAPACITY];
  }
  
  public UserList(int capacity) { 
    if(capacity > DEFAULT_CAPACITY && capacity < 10000)
      this.list = new User[capacity];
    else
      this.list = new User[DEFAULT_CAPACITY];
  }
  
  public void add(User user) {
    if(this.size == this.list.length) {
      User[] arr = new User[this.list.length + (this.list.length >> 1)];
      System.arraycopy(this.list, 0, arr, 0, list.length);
      this.list = arr;
    }
    this.list[this.size++] = user;
  }
  
  public User[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }
}
