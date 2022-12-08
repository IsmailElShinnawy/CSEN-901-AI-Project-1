package code.utils;

public class Tuple<T, K> {
  public T first;
  public K second;

  public Tuple(T first, K second) {
    this.first = first;
    this.second = second;
  }
}
