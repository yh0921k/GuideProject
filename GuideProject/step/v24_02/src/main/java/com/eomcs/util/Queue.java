package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {
  
  public void offer(E value) {
    this.add(value);
  }
  
  public E poll() {
    return this.remove(0);
  }
  
  @Override
  public Queue<E> clone() {
    try {
      Queue<E> temp = new Queue<>();
      for(int i = 0; i < this.size(); i++)
        temp.offer(this.get(i));
      return temp;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public Iterator<E> iterator() {
    return new QueueIterator<>(this);
  }
  
  static class QueueIterator<E> implements Iterator<E> {
    Queue<E> queue;
    
    public QueueIterator(Queue<E> queue) {
      this.queue = queue.clone();
    }
    
    @Override
    public boolean hasNext() {
      return queue.size() > 0;
    }
    
    @Override
    public E next() {
      return queue.poll();
    }
  }
}
