package code.actions;

import code.SearchTreeNode;
import code.utils.Constants;

public abstract class Action<T> {
  public abstract SearchTreeNode<T> perform(SearchTreeNode<T> node);

  public int updateShips(int oldShips[][], int updatedShips[][]) {
    int deaths = 0;
    for (int i = 0; i < updatedShips.length; ++i) {
      for (int j = 0; j < updatedShips[i].length; ++j) {
        if (oldShips[i][j] > Constants.EMPTY_CELL) {
          deaths++;
        }
        if (oldShips[i][j] != Constants.EMPTY_CELL) {
          updatedShips[i][j] = oldShips[i][j] - 1;
          if (updatedShips[i][j] == 0) {
            updatedShips[i][j] = Constants.WRECK;
          } else if (updatedShips[i][j] <= -20) {
            updatedShips[i][j] = Constants.EMPTY_CELL;
          }
        }
      }
    }
    return deaths;
  }

  @Override
  public String toString() {
    return "base_action";
  }
}
