package code;

public interface Store<T> {

  public void add(T x);

  public void add(T x, int depth);

  public T poll();

  public boolean isEmpty();
}
