package code.actions;

import code.CoastGuardState;
import code.utils.Constants;

public class RetrieveAction extends Action<CoastGuardState> {

  @Override
  public CoastGuardState perform(CoastGuardState state) {
    // checks if the current cell is not a wreck
    if (state.getShipCell(state.getCurrentRow(), state.getCurrentCol()) > Constants.WRECK) {
      return null;
    }

    int[][] ships = state.getShips();
    int[][] shipsCopy = new int[ships.length][ships[0].length];
    for (int i = 0; i < shipsCopy.length; ++i) {
      for (int j = 0; j < shipsCopy[i].length; ++j) {
        shipsCopy[i][j] = ships[i][j];
        if (i == state.getCurrentRow() && j == state.getCurrentCol()) {
          shipsCopy[i][j] = Constants.EMPTY_CELL;
        }
      }
    }
    int[][] updatedShips = new int[ships.length][ships[0].length];
    int deaths = super.updateShips(shipsCopy, updatedShips);

    return new CoastGuardState(state.getCurrentRow(), state.getCurrentCol(), state.getCurrentCapacity(),
        state.getMaxCapacity(), state.getRetrieves() + 1, state.getDeaths() + deaths, updatedShips,
        state.getStations());
  }

  @Override
  public String toString() {
    return "retrieve";
  }

}
