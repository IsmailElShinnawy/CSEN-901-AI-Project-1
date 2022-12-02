package code;

import java.util.LinkedList;

import code.actions.Action;

public abstract class SearchProblem<T> {
  private LinkedList<Action<T>> actions;
  private T initialState;

  public SearchProblem(T initialState) {
    this.initialState = initialState;
    this.actions = new LinkedList<Action<T>>();
  }

  public abstract boolean goalTest(T state);

  public abstract double getPathCost(LinkedList<Action<T>> actions);

  public void addAction(Action<T> action) {
    actions.add(action);
  }

  public LinkedList<Action<T>> getActions() {
    return this.actions;
  }

  public T getInitialState() {
    return this.initialState;
  }
}
