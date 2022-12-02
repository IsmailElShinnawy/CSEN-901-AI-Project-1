package code;

import code.actions.Action;

public class SearchTreeNode<T> {
  private T state;
  private SearchTreeNode<T> parent;
  private Action<T> generatingAction;
  private int depth;
  private double pathCost;

  public SearchTreeNode(T state, SearchTreeNode<T> parent, Action<T> generatingAction, int depth, double pathCost) {
    this.state = state;
    this.parent = parent;
    this.generatingAction = generatingAction;
    this.depth = depth;
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
    return this.depth;
  }

  public boolean isRoot() {
    return this.parent == null;
  }

}
