package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class BoardList {
  private static final int DEFAULT_CAPACITY  = 2;  
  private Board[] list;
  private int size;
    
  public BoardList() {
    this.list = new Board[DEFAULT_CAPACITY];
  }
  
  public BoardList(int capacity) {
    if (capacity > DEFAULT_CAPACITY && capacity < 100000)
      this.list = new Board[capacity];
    else
      this.list = new Board[DEFAULT_CAPACITY];
  }

  public void add(Board board) {
    if(this.size == this.list.length) {
      Board[] arr = new Board[this.list.length + (this.list.length >> 1)];
      System.arraycopy(this.list, 0, arr, 0, list.length);
      this.list = arr;
    }
    this.list[this.size++] = board;
  }
  
  public Board get(int number) {
    for(int i = 0; i < this.size; i++)
      if(this.list[i].getPostNum() == number)
        return this.list[i];        
    return null;
  }

  public Board[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }
}
