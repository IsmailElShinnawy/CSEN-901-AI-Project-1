package code.actions;

import code.CoastGuard;
import code.CoastGuardState;
import code.utils.Constants;

public class PickupAction extends Action<CoastGuardState> {

  @Override
  public CoastGuardState perform(CoastGuardState state) {
    // checks whether the current cell is a wreck or an empty cell or if we are at
    // full capacity
    if (state.getCurrentCapacity() == CoastGuard.getMaxCapacity()
        || state.getShipCell(state.getCurrentRow(), state.getCurrentCol()) <= Constants.EMPTY_CELL) {
      return null;
    }

    int maxPickup = CoastGuard.getMaxCapacity() - state.getCurrentCapacity();
    int passengersToPickup = Math.min(state.getShipCell(state.getCurrentRow(), state.getCurrentCol()), maxPickup);

    int ships[][] = state.getShips();
    int rows = ships.length;
    int cols = ships[0].length;

    int updatedShips[][] = new int[rows][cols];

    int[][] shipsCopy = new int[rows][cols];
    int deathsOffset = 0;

    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        shipsCopy[i][j] = ships[i][j];
        if (i == state.getCurrentRow() && j == state.getCurrentCol()) {
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

    return new CoastGuardState(state.getCurrentRow(), state.getCurrentCol(),
        state.getCurrentCapacity() + passengersToPickup, state.getRetrieves(),
        state.getDeaths() + deaths + deathsOffset, updatedShips);
  }

  @Override
  public String toString() {
    return "pickup";
  }

}
