package code.adapters;

import java.util.Queue;

import code.Store;

public class QueueAdapter<T> implements Store<T> {

  private Queue<T> q;

  public QueueAdapter(Queue<T> q) {
    this.q = q;
  }

  @Override
  public void add(T x) {
    q.add(x);
  }

  @Override
  public T poll() {
    return q.poll();
  }

  @Override
  public boolean isEmpty() {
    return q.isEmpty();
  }

}
