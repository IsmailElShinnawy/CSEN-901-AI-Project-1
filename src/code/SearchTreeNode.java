package code;

import code.actions.Action;

public class SearchTreeNode<T> {
  private T state;
  private SearchTreeNode<T> parent;
  private Action<T> generatingAction;
  private double pathCost;

  private int memoizedDepth = -1;

  public SearchTreeNode(T state, SearchTreeNode<T> parent, Action<T> generatingAction, double pathCost) {
    this.state = state;
    this.parent = parent;
    this.generatingAction = generatingAction;
    this.pathCost = pathCost;
  }

  public double getPathCost() {
    return this.pathCost;
  }

  public T getState() {
    return this.state;
  }

  public SearchTreeNode<T> getParent() {
    return this.parent;
  }

  public Action<T> getGeneratingAction() {
    return this.generatingAction;
  }

  public int getDepth() {
    if (memoizedDepth == -1)
      this.memoizedDepth = isRoot() ? 0 : this.getParent().getDepth() + 1;
    return this.memoizedDepth;
  }

  public boolean isRoot() {
    return this.parent == null;
  }

}
