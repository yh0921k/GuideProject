package com.eomcs.lms.handler;

import java.util.Arrays;

public class ArrayList {
  private static final int DEFAULT_CAPACITY  = 2;  
  private Object[] list;
  private int size;
    
  public ArrayList() {
    this.list = new Object[DEFAULT_CAPACITY];
  }
  
  public ArrayList(int capacity) {
    if (capacity > DEFAULT_CAPACITY && capacity < 100000)
      this.list = new Object[capacity];
    else
      this.list = new Object[DEFAULT_CAPACITY];
  }

  public void add(Object obj) {
    if(this.size == this.list.length) {
      Object[] arr = new Object[this.list.length + (this.list.length >> 1)];
      System.arraycopy(this.list, 0, arr, 0, list.length);
      this.list = arr;
    }
    this.list[this.size++] = obj;
  }
  
  public Object get(int idx) {
    for(int i = 0; i < this.size; i++)      
      if(idx>=0 && idx<this.size && i==idx)
        return this.list[i];        
    return null;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }
}
