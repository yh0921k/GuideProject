package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;
  
  public int size() {
    return size;
  }
  
  @Override
  public Iterator<E> iterator() {    
    // List 객체에서 값을 꺼내주는 일을 할 Iterator 구현체를 준비하여 리턴한다.
    return new Iterator<E>() {
      List<E> list;
      int cursor;

      {
        this.list = (List<E>)AbstractList.this;
      }
      @Override
      public boolean hasNext() {
        return cursor < list.size();
      }
      @Override
      public E next() {
        return list.get(cursor++);
      }
    };
  }
}
