package code.adapters;

import java.util.Stack;

import code.Store;

public class StackAdapter<T> implements Store<T> {
  private Stack<T> stack;
  private int maxDepth;

  public StackAdapter(Stack<T> stack) {
    this.stack = stack;
    this.maxDepth = Integer.MAX_VALUE;
  }

  public StackAdapter(Stack<T> stack, int maxDepth) {
    this.stack = stack;
    this.maxDepth = maxDepth;
  }

  @Override
  public void add(T x) {
    stack.push(x);
  }

  @Override
  public void add(T x, int depth) {
    if (depth <= maxDepth) {
      this.add(x);
    }
  }

  @Override
  public T poll() {
    return stack.pop();
  }

  @Override
  public boolean isEmpty() {
    return stack.isEmpty();
  }
}
