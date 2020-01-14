package com.eomcs.util;

public abstract class AbstractList<E> {
  protected int size;
  
  public  int size() {
    return size;
  }
  
  // ArrayList나 LinkedList는 동작 방법이 다르기 때문에 이곳에서 구현하지 않는다.
  // 추상 메서드로 정의하여 하위 클래스에서 각 기능 구현 강요
  public abstract void add(E e);
  public abstract void add(int index, E e);
  public abstract E get(int index);
  public abstract E set(int index, E e);
  public abstract E remove(int index);
  public abstract Object[] toArray();
  public abstract E[] toArray(E[] arr);
}
