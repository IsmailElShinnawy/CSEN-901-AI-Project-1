package code;

import code.utils.Utils;

public class CoastGuardState {
  private int currentRow, currentCol, currentCapacity, retrieves, deaths;
  private int ships[][];

  public CoastGuardState(int currentRow, int currentCol, int currentCapacity, int retrieves, int deaths,
      int ships[][]) {
    this.currentRow = currentRow;
    this.currentCol = currentCol;
    this.currentCapacity = currentCapacity;
    this.retrieves = retrieves;
    this.deaths = deaths;
    this.ships = ships;
  }

  public void setShipCell(int row, int col, int val) {
    this.ships[row][col] = val;
  }

  public int getShipCell(int row, int col) {
    return this.ships[row][col];
  }

  public int[][] getShips() {
    return this.ships;
  }

  public int getCurrentRow() {
    return this.currentRow;
  }

  public int getCurrentCol() {
    return this.currentCol;
  }

  public int getCurrentCapacity() {
    return this.currentCapacity;
  }

  public int getRetrieves() {
    return this.retrieves;
  }

  public int getDeaths() {
    return this.deaths;
  }

  public String getHash() {
    // TODO: check if hashing function needs enhancement
    return currentRow + "_" + currentCol + "_" + currentCapacity + "_" + retrieves + "_" + deaths + "_"
        + Utils.arrayToString(ships);
  }
}
