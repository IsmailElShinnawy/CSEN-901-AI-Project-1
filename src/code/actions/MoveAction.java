package code.actions;

import code.CoastGuardState;
import code.SearchTreeNode;

public class MoveAction extends Action<CoastGuardState> {

  private MoveDirection direction;

  public MoveAction(MoveDirection direction) {
    this.direction = direction;
  }

  @Override
  public SearchTreeNode<CoastGuardState> perform(SearchTreeNode<CoastGuardState> node) {
    int newRow = node.getState().getCurrentRow();
    int newCol = node.getState().getCurrentCol();

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

    int ships[][] = node.getState().getShips();
    int rows = ships.length;
    int cols = ships[0].length;
    int updatedShips[][] = new int[rows][cols];

    if (newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols) {
      return null;
    }

    int deaths = super.updateShips(ships, updatedShips);

    CoastGuardState resultState = new CoastGuardState(newRow, newCol, node.getState().getCurrentCapacity(),
        node.getState().getRetrieves(), node.getState().getDeaths() + deaths, updatedShips);

    return new SearchTreeNode<CoastGuardState>(resultState, node, this, resultState.getDeaths() + node.getDepth() + 1);
  }

  @Override
  public String toString() {
    return direction.toString().toLowerCase();
  }

}
