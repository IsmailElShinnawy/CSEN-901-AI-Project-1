package code;

import code.utils.Utils;

public class CoastGuardState {
  private int currentRow, currentCol, currentCapacity, maxCapacity, retrieves, deaths;
  private int ships[][];
  private boolean stations[][];

  public CoastGuardState(int currentRow, int currentCol, int currentCapacity, int maxCapacity, int retrieves,
      int deaths, int ships[][], boolean stations[][]) {
    this.currentRow = currentRow;
    this.currentCol = currentCol;
    this.currentCapacity = currentCapacity;
    this.maxCapacity = maxCapacity;
    this.retrieves = retrieves;
    this.deaths = deaths;
    this.ships = ships;
    this.stations = stations;
  }

  public void setShipCell(int row, int col, int val) {
    this.ships[row][col] = val;
  }

  public int getShipCell(int row, int col) {
    return this.ships[row][col];
  }

  public void setStationCell(int row, int col, boolean val) {
    this.stations[row][col] = val;
  }

  public boolean getStationCell(int row, int col) {
    return this.stations[row][col];
  }

  public int[][] getShips() {
    return this.ships;
  }

  public boolean[][] getStations() {
    return this.stations;
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

  public int getMaxCapacity() {
    return this.maxCapacity;
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
