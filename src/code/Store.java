package code;

public interface Store<T> {
  public void add(T x);

  public T poll();

  public boolean isEmpty();
}
