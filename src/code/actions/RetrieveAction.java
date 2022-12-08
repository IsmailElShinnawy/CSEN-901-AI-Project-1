package code.actions;

import code.CoastGuardState;
import code.SearchTreeNode;
import code.utils.Constants;

public class RetrieveAction extends Action<CoastGuardState> {

  @Override
  public SearchTreeNode<CoastGuardState> perform(SearchTreeNode<CoastGuardState> node) {
    // checks if the current cell is not a wreck
    if (node.getState().getShipCell(node.getState().getCurrentRow(),
        node.getState().getCurrentCol()) > Constants.WRECK) {
      return null;
    }

    int[][] ships = node.getState().getShips();
    int[][] shipsCopy = new int[ships.length][ships[0].length];
    for (int i = 0; i < shipsCopy.length; ++i) {
      for (int j = 0; j < shipsCopy[i].length; ++j) {
        shipsCopy[i][j] = ships[i][j];
        if (i == node.getState().getCurrentRow() && j == node.getState().getCurrentCol()) {
          shipsCopy[i][j] = Constants.EMPTY_CELL;
        }
      }
    }
    int[][] updatedShips = new int[ships.length][ships[0].length];
    int deaths = super.updateShips(shipsCopy, updatedShips);

    CoastGuardState resultState = new CoastGuardState(node.getState().getCurrentRow(), node.getState().getCurrentCol(),
        node.getState().getCurrentCapacity(), node.getState().getRetrieves() + 1, node.getState().getDeaths() + deaths,
        updatedShips);

    return new SearchTreeNode<CoastGuardState>(resultState, node, this, node.getDepth() + 1,
        resultState.getDeaths() + node.getDepth() + 1);
  }

  @Override
  public String toString() {
    return "retrieve";
  }

}
