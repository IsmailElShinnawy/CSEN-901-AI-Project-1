package code.actions;

import code.CoastGuardState;

public class MoveAction extends Action<CoastGuardState> {

  private MoveDirection direction;

  public MoveAction(MoveDirection direction) {
    this.direction = direction;
  }

  @Override
  public CoastGuardState perform(CoastGuardState state) {
    int newRow = state.getCurrentRow();
    int newCol = state.getCurrentCol();

    switch (this.direction) {
      case UP:
        newRow -= 1;
        break;
      case DOWN:
        newRow += 1;
        break;
      case LEFT:
        newCol -= 1;
        break;
      default:
        newCol += 1;
    }

    int ships[][] = state.getShips();
    int rows = ships.length;
    int cols = ships[0].length;
    int updatedShips[][] = new int[rows][cols];

    if (newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols) {
      return null;
    }

    int deaths = super.updateShips(ships, updatedShips);

    return new CoastGuardState(newRow, newCol, state.getCurrentCapacity(), state.getRetrieves(),
        state.getDeaths() + deaths, updatedShips);
  }

  @Override
  public String toString() {
    return direction.toString().toLowerCase();
  }

}
