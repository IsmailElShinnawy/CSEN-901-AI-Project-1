package code.actions;

import code.CoastGuardState;

public class DropAction extends Action<CoastGuardState> {

  @Override
  public CoastGuardState perform(CoastGuardState state) {
    if (!state.getStationCell(state.getCurrentRow(), state.getCurrentCol())) {
      return null;
    }

    int[][] oldShips = state.getShips();
    int[][] updatedShips = new int[oldShips.length][oldShips[0].length];

    int deaths = super.updateShips(oldShips, updatedShips);

    return new CoastGuardState(state.getCurrentRow(), state.getCurrentCol(), 0, state.getMaxCapacity(),
        state.getRetrieves(), state.getDeaths() + deaths, updatedShips, state.getStations());
  }

  @Override
  public String toString() {
    return "drop";
  }

}
