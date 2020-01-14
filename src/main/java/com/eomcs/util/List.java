package com.eomcs.util;

// Generalization 1 단계:
// > ArrayList와 LinkedList의 공통 분모를 추출하여 super 클래스를 정의한다.
// > 각각의 하위 클래스는 여기서 정의한 super 클래스를 상속 받는다.
// > 
public class List<E> {
  protected int size;
  
  public int size() {
    return size;
  }
  
  public void add(E e) {
    // ArrayList나 LinkedList는 동작 방법이 다르기 때문에 이곳에서 구현하지 않는다.
  }
  
  public void add(int index, E e) {
    // 위와 동일
  }
  
  public E get(int index) {
    // 위와 동일
    return null;
  }
  
  public E set(int index, E e) {
    // 위와 동일
    return null;
  }
  
  public E remove(int index) {
    // 위와 동일
    return null;
  }
  
  public Object[] toArray() {
    // 위와 동일
    return null;
  }
  
  public E[] toArray(E[] arr) {
    // 위와 동일
    return null;
  }
}
