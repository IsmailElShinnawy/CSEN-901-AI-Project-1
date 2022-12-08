package code.actions;

import code.CoastGuard;
import code.CoastGuardState;
import code.SearchTreeNode;
import code.utils.Constants;

public class PickupAction extends Action<CoastGuardState> {

  @Override
  public SearchTreeNode<CoastGuardState> perform(SearchTreeNode<CoastGuardState> node) {
    // checks whether the current cell is a wreck or an empty cell or if we are at
    // full capacity
    if (node.getState().getCurrentCapacity() == CoastGuard.getMaxCapacity()
        || node.getState().getShipCell(node.getState().getCurrentRow(),
            node.getState().getCurrentCol()) <= Constants.EMPTY_CELL) {
      return null;
    }

    int maxPickup = CoastGuard.getMaxCapacity() - node.getState().getCurrentCapacity();
    int passengersToPickup = Math
        .min(node.getState().getShipCell(node.getState().getCurrentRow(), node.getState().getCurrentCol()), maxPickup);

    int ships[][] = node.getState().getShips();
    int rows = ships.length;
    int cols = ships[0].length;

    int updatedShips[][] = new int[rows][cols];

    int[][] shipsCopy = new int[rows][cols];
    int deathsOffset = 0;

    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        shipsCopy[i][j] = ships[i][j];
        if (i == node.getState().getCurrentRow() && j == node.getState().getCurrentCol()) {
          shipsCopy[i][j] = ships[i][j] - passengersToPickup;
          // This is a hack to guarantee that we turn this ship to a wreck in updateShips
          if (shipsCopy[i][j] == 0) {
            shipsCopy[i][j] = 1;
            deathsOffset = -1;
          }
        }
      }
    }

    int deaths = super.updateShips(shipsCopy, updatedShips);

    CoastGuardState resultState = new CoastGuardState(node.getState().getCurrentRow(), node.getState().getCurrentCol(),
        node.getState().getCurrentCapacity() + passengersToPickup, node.getState().getRetrieves(),
        node.getState().getDeaths() + deaths + deathsOffset, updatedShips);

    return new SearchTreeNode<CoastGuardState>(resultState, node, this, node.getDepth() + 1,
        resultState.getDeaths() + node.getDepth() + 1);
  }

  @Override
  public String toString() {
    return "pickup";
  }

}
