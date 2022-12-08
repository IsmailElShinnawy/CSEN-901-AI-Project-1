package code;

import java.util.HashSet;

import code.actions.Action;
import code.interfaces.Hashable;
import code.utils.Tuple;

public class GeneralSearch {

  public static <T extends Hashable> Tuple<SearchTreeNode<T>, Integer> search(SearchProblem<T> problem,
      Store<SearchTreeNode<T>> store) {
    HashSet<String> visitedStates = new HashSet<String>();
    store.add(new SearchTreeNode<T>(problem.getInitialState(), null, null, 0, 0));

    int exploredNodes = 0;
    while (!store.isEmpty()) {
      SearchTreeNode<T> node = store.poll();
      exploredNodes++;
      if (problem.goalTest(node.getState())) {
        return new Tuple<SearchTreeNode<T>, Integer>(node, exploredNodes);
      }
      for (Action<T> action : problem.getActions()) {
        SearchTreeNode<T> resultNode = action.perform(node);
        String hash;
        if (resultNode != null && !visitedStates.contains(hash = resultNode.getState().getHash())) {
          visitedStates.add(hash);
          store.add(resultNode, resultNode.getDepth());
        }
      }
    }

    return new Tuple<SearchTreeNode<T>, Integer>(null, exploredNodes);
  }

}