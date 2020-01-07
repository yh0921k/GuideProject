package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<T> {
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
  public void add(T obj) {
    if(this.size == this.list.length) {
      Object[] arr = new Object[this.list.length + (this.list.length >> 1)];
      System.arraycopy(this.list, 0, arr, 0, list.length);
      this.list = arr;
    }
    this.list[this.size++] = obj;
  }
  
  @SuppressWarnings("unchecked")
  public T get(int idx) {
    for(int i = 0; i < this.size; i++)      
      if(idx>=0 && idx<this.size && i==idx)
        return (T)this.list[i];        
    return null;
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  public T[] toArray(Class arrayType) {
    return (T[])Arrays.copyOf(this.list,  this.size, arrayType);
  }
}
