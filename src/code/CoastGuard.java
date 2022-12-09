package code;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import code.actions.Action;
import code.actions.DropAction;
import code.actions.MoveAction;
import code.actions.MoveDirection;
import code.actions.PickupAction;
import code.actions.RetrieveAction;
import code.adapters.QueueAdapter;
import code.adapters.StackAdapter;
import code.utils.Constants;
import code.utils.Tuple;
import code.utils.Utils;

enum Heuristic {
  HEURISTIC_1,
  HEURISTIC_2
}

public class CoastGuard extends SearchProblem<CoastGuardState> {

  private static final int MIN_ROWS = 5, MAX_ROWS = 15, MIN_COLS = 5, MAX_COLS = 15;
  private static final int MIN_CAPACITY = 30, MAX_CAPACITY = 100;
  private static final int MIN_PASSENGERS = 1, MAX_PASSENGERS = 100;

  private static int maxCapacity;
  private static boolean[][] stations;
  private static boolean visualise;

  public CoastGuard(CoastGuardState initialState) {
    super(initialState);
  }

  @Override
  public boolean goalTest(CoastGuardState state) {
    int[][] ships = state.getShips();
    int rows = ships.length, cols = ships[0].length;

    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        if (ships[i][j] != Constants.EMPTY_CELL) {
          return false;
        }
      }
    }

    return state.getCurrentCapacity() == 0;
  }

  @Override
  public double getPathCost(LinkedList<Action<CoastGuardState>> actions) {
    // TODO implement path cost from actions path or remove
    return 0;
  }

  private static void genStations(boolean mask[][], StringBuilder sb, int numberOfStations, int rows, int cols) {
    while (numberOfStations > 0) {
      int stationRow = Utils.randomInt(0, rows - 1);
      int stationCol = Utils.randomInt(0, cols - 1);
      if (!mask[stationRow][stationCol]) {
        mask[stationRow][stationCol] = true;
        sb.append(stationCol).append(",").append(stationRow);
        numberOfStations--;
        if (numberOfStations == 0) {
          sb.append(";");
        } else {
          sb.append(",");
        }
      }
    }
  }

  private static void genShips(boolean mask[][], StringBuilder sb, int numberOfShips, int rows, int cols) {
    while (numberOfShips > 0) {
      int shipRow = Utils.randomInt(0, rows - 1);
      int shipCol = Utils.randomInt(0, cols - 1);
      int passengers = Utils.randomInt(MIN_PASSENGERS, MAX_PASSENGERS);
      if (!mask[shipRow][shipCol]) {
        mask[shipRow][shipCol] = true;
        sb.append(shipCol).append(",").append(shipRow).append(",").append(passengers);
        numberOfShips--;
        if (numberOfShips == 0) {
          sb.append(";");
        } else {
          sb.append(",");
        }
      }
    }
  }

  public static String genGrid() {
    StringBuilder sb = new StringBuilder();

    int rows = Utils.randomInt(MIN_ROWS, MAX_ROWS);
    int cols = Utils.randomInt(MIN_COLS, MAX_COLS);
    int capacity = Utils.randomInt(MIN_CAPACITY, MAX_CAPACITY);
    // TODO: check against TCs whether X is col and Y is row or is it the other way
    // around
    int cgX = Utils.randomInt(0, cols - 1);
    int cgY = Utils.randomInt(0, rows - 1);

    sb.append(cols).append(",").append(rows).append(";").append(capacity).append(";").append(cgX).append(",")
        .append(cgY).append(";");

    boolean mask[][] = new boolean[rows][cols];
    mask[cgY][cgX] = true;

    // the max is how many cells we have minus 2 (1 for the initial position of the
    // guard and 1 for at least one ship to present)
    int numberOfStations = Utils.randomInt(1, rows * cols - 2);
    genStations(mask, sb, numberOfStations, rows, cols);

    // again, the max is how many cells we have minus how many cells were taken by
    // stations minus 1 for the initial position of the guard
    int numberOfShips = Utils.randomInt(1, rows * cols - numberOfStations - 1);
    genShips(mask, sb, numberOfShips, rows, cols);

    return sb.toString();
  }

  private static void populateStationsGrid(String stations, boolean stationsGrid[][]) {
    StringTokenizer helperTokenizer = new StringTokenizer(stations, ",");
    while (helperTokenizer.hasMoreTokens()) {
      int stationRow = Integer.parseInt(helperTokenizer.nextToken());
      int stationCol = Integer.parseInt(helperTokenizer.nextToken());
      stationsGrid[stationRow][stationCol] = true;
    }
  }

  private static void populateShipsGrid(String ships, int shipsGrid[][]) {
    StringTokenizer helperTokenizer = new StringTokenizer(ships, ",");
    while (helperTokenizer.hasMoreTokens()) {
      int shipRow = Integer.parseInt(helperTokenizer.nextToken());
      int shipCol = Integer.parseInt(helperTokenizer.nextToken());
      int passengers = Integer.parseInt(helperTokenizer.nextToken());
      shipsGrid[shipRow][shipCol] = passengers;
    }
  }

  private String constructPlan(SearchTreeNode<CoastGuardState> node) {
    LinkedList<String> ll = new LinkedList<String>();
    SearchTreeNode<CoastGuardState> current = node;
    while (!current.isRoot()) {
      ll.addFirst(current.getGeneratingAction().toString());
      current = current.getParent();
    }

    StringBuilder sb = new StringBuilder();
    for (String s : ll) {
      sb.append(s).append(",");
    }

    if (sb.length() == 0) {
      return "";
    }

    return sb.substring(0, sb.length() - 1);
  }

  private String breadthFirstSearch() {
    Queue<SearchTreeNode<CoastGuardState>> q = new LinkedList<SearchTreeNode<CoastGuardState>>();

    QueueAdapter<SearchTreeNode<CoastGuardState>> qAdapter = new QueueAdapter<SearchTreeNode<CoastGuardState>>(q);

    Tuple<SearchTreeNode<CoastGuardState>, Integer> goalTuple = GeneralSearch.search(this, qAdapter);
    SearchTreeNode<CoastGuardState> goalNode = goalTuple.first;
    int exploredNodes = goalTuple.second;

    if (goalNode == null) {
      return Constants.NO_PATH;
    }

    StringBuilder sb = new StringBuilder();
    String plan = constructPlan(goalNode);
    sb.append(plan).append(";");
    sb.append(goalNode.getState().getDeaths()).append(";");
    sb.append(goalNode.getState().getRetrieves()).append(";");
    sb.append(exploredNodes);

    return sb.toString();
  }

  private String limitedDepthFirstSearch(int limit) {
    Stack<SearchTreeNode<CoastGuardState>> stack = new Stack<SearchTreeNode<CoastGuardState>>();
    StackAdapter<SearchTreeNode<CoastGuardState>> stackAdapter = new StackAdapter<SearchTreeNode<CoastGuardState>>(
        stack, limit);

    Tuple<SearchTreeNode<CoastGuardState>, Integer> goalTuple = GeneralSearch.search(this, stackAdapter);
    SearchTreeNode<CoastGuardState> goalNode = goalTuple.first;
    int exploredNodes = goalTuple.second;

    if (goalNode == null) {
      return Constants.NO_PATH;
    }

    StringBuilder sb = new StringBuilder();
    String plan = constructPlan(goalNode);
    sb.append(plan).append(";");
    sb.append(goalNode.getState().getDeaths()).append(";");
    sb.append(goalNode.getState().getRetrieves()).append(";");
    sb.append(exploredNodes);

    return sb.toString();
  }

  private String depthFirstSearch() {
    return limitedDepthFirstSearch(Integer.MAX_VALUE);
  }

  private String iterativeDeepeningSearch() {
    String sol;
    for (int i = 0; i < Integer.MAX_VALUE; ++i) {
      sol = limitedDepthFirstSearch(i);
      if (sol != Constants.NO_PATH) {
        return sol;
      }
    }
    return Constants.NO_PATH;
  }

  private String uniformCostSearch() {
    PriorityQueue<SearchTreeNode<CoastGuardState>> q = new PriorityQueue<SearchTreeNode<CoastGuardState>>(
        (node1, node2) -> (int) (node1.getPathCost() - node2.getPathCost()));
    QueueAdapter<SearchTreeNode<CoastGuardState>> qAdapter = new QueueAdapter<SearchTreeNode<CoastGuardState>>(q);

    Tuple<SearchTreeNode<CoastGuardState>, Integer> goalTuple = GeneralSearch.search(this, qAdapter);
    SearchTreeNode<CoastGuardState> goalNode = goalTuple.first;
    int exploredNodes = goalTuple.second;

    if (goalNode == null) {
      return Constants.NO_PATH;
    }

    StringBuilder sb = new StringBuilder();
    String plan = constructPlan(goalNode);
    sb.append(plan).append(";");
    sb.append(goalNode.getState().getDeaths()).append(";");
    sb.append(goalNode.getState().getRetrieves()).append(";");
    sb.append(exploredNodes);

    return sb.toString();
  }

  /**
   * Relaxes the problem by removing the following constraints:
   * 
   * - Agent can pickup passengers only when they are at the same cell
   * - Agent can pickup passengers only if they have capacity
   * - Agent can drop passengers only at a station
   * - Agent can retrieve BBs only if they are in the same cell as the wreck
   */
  private double heuristic1Score(SearchTreeNode<CoastGuardState> node) {
    int score = 0;
    int ships[][] = node.getState().getShips();
    for (int i = 0; i < ships.length; ++i) {
      for (int j = 0; j < ships[i].length; ++j) {
        if (ships[i][j] > 0) {
          score += 2;
        } else {
          score += 1;
        }
      }
    }

    return score + node.getState().getCurrentCapacity() > 0 ? 1 : 0;
  }

  /**
   * Relaxes the problem by removing the following constraints:
   * 
   * - Agent can pickup passengers only when they are at the same cell
   * - Agent can drop passengers only at a station
   * - Agent can retrieve BBs only if they are in the same cell as the wreck
   */
  private double heuristic2Score(SearchTreeNode<CoastGuardState> node) {
    int ships[][] = node.getState().getShips();
    double score = 0;

    for (int i = 0; i < ships.length; ++i) {
      for (int j = 0; j < ships[i].length; ++j) {
        if (ships[i][j] > 0) {
          score += ships[i][j] / CoastGuard.maxCapacity + 1;
        } else if (ships[i][j] < 0) {
          score += 1;
        }
      }
    }

    return score + node.getState().getCurrentCapacity() > 0 ? 1 : 0;
  }

  private String greedySearch(Heuristic heuristic) {
    PriorityQueue<SearchTreeNode<CoastGuardState>> q = new PriorityQueue<SearchTreeNode<CoastGuardState>>(
        (node1, node2) -> {
          if (heuristic == Heuristic.HEURISTIC_1) {
            return (int) (heuristic1Score(node1) - heuristic1Score(node2));
          } else if (heuristic == Heuristic.HEURISTIC_2) {
            return (int) (heuristic2Score(node1) - heuristic2Score(node2));
          }
          return 0;
        });

    QueueAdapter<SearchTreeNode<CoastGuardState>> qAdapter = new QueueAdapter<SearchTreeNode<CoastGuardState>>(q);

    Tuple<SearchTreeNode<CoastGuardState>, Integer> goalTuple = GeneralSearch.search(this, qAdapter);
    SearchTreeNode<CoastGuardState> goalNode = goalTuple.first;
    int exploredNodes = goalTuple.second;

    if (goalNode == null) {
      return Constants.NO_PATH;
    }

    StringBuilder sb = new StringBuilder();
    String plan = constructPlan(goalNode);
    sb.append(plan).append(";");
    sb.append(goalNode.getState().getDeaths()).append(";");
    sb.append(goalNode.getState().getRetrieves()).append(";");
    sb.append(exploredNodes);

    return sb.toString();
  }

  private String aStarSearch(Heuristic heuristic) {
    PriorityQueue<SearchTreeNode<CoastGuardState>> pq = new PriorityQueue<SearchTreeNode<CoastGuardState>>(
        (node1, node2) -> {
          double g1 = node1.getPathCost();
          double g2 = node2.getPathCost();

          if (heuristic == Heuristic.HEURISTIC_1) {
            double h1 = heuristic1Score(node1);
            double f1 = g1 + h1;
            double h2 = heuristic1Score(node2);
            double f2 = g2 + h2;

            return (int) (f1 - f2);
          } else if (heuristic == Heuristic.HEURISTIC_2) {
            double h1 = heuristic2Score(node1);
            double f1 = g1 + h1;
            double h2 = heuristic2Score(node2);
            double f2 = g2 + h2;

            return (int) (f1 - f2);
          }
          return 0;
        });

    QueueAdapter<SearchTreeNode<CoastGuardState>> qAdapter = new QueueAdapter<SearchTreeNode<CoastGuardState>>(pq);

    Tuple<SearchTreeNode<CoastGuardState>, Integer> goalTuple = GeneralSearch.search(this, qAdapter);
    SearchTreeNode<CoastGuardState> goalNode = goalTuple.first;
    int exploredNodes = goalTuple.second;

    if (goalNode == null) {
      return Constants.NO_PATH;
    }

    StringBuilder sb = new StringBuilder();
    String plan = constructPlan(goalNode);
    sb.append(plan).append(";");
    sb.append(goalNode.getState().getDeaths()).append(";");
    sb.append(goalNode.getState().getRetrieves()).append(";");
    sb.append(exploredNodes);

    return sb.toString();
  }

  public static String solve(String grid, String searchStrategy, boolean vis) {
    // Token consuming
    StringTokenizer mainTokenizer = new StringTokenizer(grid, ";");
    StringTokenizer helperTokenizer;

    String rowsAndCols = mainTokenizer.nextToken();
    helperTokenizer = new StringTokenizer(rowsAndCols, ",");
    int cols = Integer.parseInt(helperTokenizer.nextToken());
    int rows = Integer.parseInt(helperTokenizer.nextToken());

    int capacity = Integer.parseInt(mainTokenizer.nextToken());

    maxCapacity = capacity;

    String initialRowAndCol = mainTokenizer.nextToken();
    helperTokenizer = new StringTokenizer(initialRowAndCol, ",");
    int cgRow = Integer.parseInt(helperTokenizer.nextToken());
    int cgCol = Integer.parseInt(helperTokenizer.nextToken());

    boolean stationsGrid[][] = new boolean[rows][cols];
    String stationsToken = mainTokenizer.nextToken();
    populateStationsGrid(stationsToken, stationsGrid);

    stations = stationsGrid;

    int shipsGrid[][] = new int[rows][cols];
    String shipsToken = mainTokenizer.nextToken();
    populateShipsGrid(shipsToken, shipsGrid);

    visualise = vis;

    // Solving
    CoastGuardState rootState = new CoastGuardState(cgRow, cgCol, 0, 0, 0, shipsGrid);

    CoastGuard coastGuardSearchProblem = new CoastGuard(rootState);
    coastGuardSearchProblem.addAction(new PickupAction());
    coastGuardSearchProblem.addAction(new DropAction());
    coastGuardSearchProblem.addAction(new RetrieveAction());
    coastGuardSearchProblem.addAction(new MoveAction(MoveDirection.UP));
    coastGuardSearchProblem.addAction(new MoveAction(MoveDirection.DOWN));
    coastGuardSearchProblem.addAction(new MoveAction(MoveDirection.LEFT));
    coastGuardSearchProblem.addAction(new MoveAction(MoveDirection.RIGHT));

    String res;
    switch (searchStrategy) {
      case Constants.BreadthFirstSearch:
        res = coastGuardSearchProblem.breadthFirstSearch();
        break;
      case Constants.DepthFirstSearch:
        res = coastGuardSearchProblem.depthFirstSearch();
        break;
      case Constants.IterativeDeepeningSearch:
        res = coastGuardSearchProblem.iterativeDeepeningSearch();
        break;
      case Constants.UniformCostSearch:
        res = coastGuardSearchProblem.uniformCostSearch();
        break;
      case Constants.AStarSearchWithFirstHeuristic:
        res = coastGuardSearchProblem.aStarSearch(Heuristic.HEURISTIC_1);
        break;
      case Constants.AStarSearchWithSecondHeuristic:
        res = coastGuardSearchProblem.aStarSearch(Heuristic.HEURISTIC_2);
        break;
      case Constants.GreedySearchWithFirstHeuristic:
        res = coastGuardSearchProblem.greedySearch(Heuristic.HEURISTIC_1);
        break;
      case Constants.GreedySearchWithSecondHeuristic:
        res = coastGuardSearchProblem.greedySearch(Heuristic.HEURISTIC_2);
        break;
      default:
        res = "SEARCH_STRATEGY_NOT_SUPPORTED";
    }

    return res;
  }

  public static boolean isStationAt(int row, int col) {
    return stations == null ? false : stations[row][col];
  }

  public static int getMaxCapacity() {
    return maxCapacity;
  }

  public static boolean isVisualising() {
    return visualise;
  }
}
