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
    
    class QueueIterator<T> implements Iterator<T> {
      Queue<T> queue;
      
      @SuppressWarnings("unchecked")
      public QueueIterator() {
        this.queue = (Queue<T>)Queue.this.clone();
      }
      
      @Override
      public boolean hasNext() {
        return queue.size() > 0;
      }
      
      @Override
      public T next() {
        return queue.poll();
      }
    }
    
    return new QueueIterator<>();
  }
}
