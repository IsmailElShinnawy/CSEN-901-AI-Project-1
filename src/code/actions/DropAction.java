package code.actions;

import code.CoastGuard;
import code.CoastGuardState;
import code.SearchTreeNode;

public class DropAction extends Action<CoastGuardState> {

  @Override
  public SearchTreeNode<CoastGuardState> perform(SearchTreeNode<CoastGuardState> node) {
    if (!CoastGuard.isStationAt(node.getState().getCurrentRow(), node.getState().getCurrentCol())) {
      return null;
    }

    int[][] oldShips = node.getState().getShips();
    int[][] updatedShips = new int[oldShips.length][oldShips[0].length];

    int deaths = super.updateShips(oldShips, updatedShips);

    CoastGuardState resultState = new CoastGuardState(node.getState().getCurrentRow(), node.getState().getCurrentCol(),
        0, node.getState().getRetrieves(), node.getState().getDeaths() + deaths, updatedShips);

    return new SearchTreeNode<CoastGuardState>(resultState, node, this, resultState.getDeaths() + node.getDepth() + 1);
  }

  @Override
  public String toString() {
    return "drop";
  }

}
