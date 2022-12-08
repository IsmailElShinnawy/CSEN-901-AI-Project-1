package code;

import java.util.HashSet;
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
import code.utils.Constants;
import code.utils.Utils;

// TODO: need to figure out what heuristics to implement
// h1 => remaining passengers that still need rescue
// (node1, node2) -> numberOfPassengers(node1.getState().getShips()) - numberOfPassengers(node2.getState().getShips())
// h2 => remaining black boxes that stil need retrievel 
// (node1, node2) -> numberOfBlackBoxes(node1.getState().getShips()) - numberOfBlackBoxes(node2.getState().getShips())
enum Heuristic {
  NUMBER_OF_PASSENGERS,
  NUMBER_OF_BLACK_BOXES
}

enum AStarHeuristic {
  // This heuristic aims at favoriting the nodes with more number of empty cells,
  // since our end goal is an empty grid where no more passengers can be saved and
  // no more black boxes can be retrieved. The problem tho is that this heuristic
  // does not care to favor nodes where we save more passengers or retrieve more
  // black boxes
  HURISTIC_1
}

public class CoastGuard extends SearchProblem<CoastGuardState> {

  private static final int MIN_ROWS = 5, MAX_ROWS = 15, MIN_COLS = 5, MAX_COLS = 15;
  private static final int MIN_CAPACITY = 30, MAX_CAPACITY = 100;
  private static final int MIN_PASSENGERS = 1, MAX_PASSENGERS = 100;

  private static int maxCapacity;
  private static boolean[][] stations;
  private HashSet<String> visitedStates;

  public CoastGuard(CoastGuardState initialState) {
    super(initialState);
    visitedStates = new HashSet<String>();
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

  private String breadthFirstSearch(SearchTreeNode<CoastGuardState> root) {
    Queue<SearchTreeNode<CoastGuardState>> q = new LinkedList<SearchTreeNode<CoastGuardState>>();

    q.add(root);

    StringBuilder sb = new StringBuilder();
    int exploredNodes = 0;
    while (!q.isEmpty()) {
      SearchTreeNode<CoastGuardState> node = q.poll();
      exploredNodes++;
      if (this.goalTest(node.getState())) {
        String plan = constructPlan(node);
        sb.append(plan).append(";");
        sb.append(node.getState().getDeaths()).append(";");
        sb.append(node.getState().getRetrieves()).append(";");
        sb.append(exploredNodes);
        break;
      }
      for (Action<CoastGuardState> action : this.getActions()) {
        CoastGuardState resultState = action.perform(node.getState());
        String hash;
        if (resultState != null && !visitedStates.contains(hash = resultState.getHash())) {
          visitedStates.add(hash);
          SearchTreeNode<CoastGuardState> resultNode = new SearchTreeNode<CoastGuardState>(resultState, node, action,
              node.getDepth() + 1, resultState.getDeaths() + node.getDepth() + 1);
          q.add(resultNode);
        }
      }
    }

    return sb.toString();
  }

  private String limitedDepthFirstSearch(SearchTreeNode<CoastGuardState> root, int limit) {
    Stack<SearchTreeNode<CoastGuardState>> stack = new Stack<SearchTreeNode<CoastGuardState>>();
    stack.push(root);

    StringBuilder sb = new StringBuilder();
    int exploredNodes = 0;
    while (!stack.isEmpty()) {
      SearchTreeNode<CoastGuardState> node = stack.pop();
      exploredNodes++;
      if (this.goalTest(node.getState())) {
        String plan = constructPlan(node);
        sb.append(plan).append(";");
        sb.append(node.getState().getDeaths()).append(";");
        sb.append(node.getState().getRetrieves()).append(";");
        sb.append(exploredNodes);
        break;
      }
      if (node.getDepth() + 1 <= limit) {
        for (Action<CoastGuardState> action : this.getActions()) {
          CoastGuardState resultState = action.perform(node.getState());
          String hash;
          if (resultState != null && !visitedStates.contains(hash = resultState.getHash())) {
            visitedStates.add(hash);
            SearchTreeNode<CoastGuardState> resultNode = new SearchTreeNode<CoastGuardState>(resultState, node, action,
                node.getDepth() + 1, resultState.getDeaths() + node.getDepth() + 1);
            stack.push(resultNode);
          }
        }
      }
    }
    return sb.length() > 0 ? sb.toString() : Constants.NO_PATH;
  }

  private String depthFirstSearch(SearchTreeNode<CoastGuardState> root) {
    return limitedDepthFirstSearch(root, Integer.MAX_VALUE);
  }

  private String iterativeDeepeningSearch(SearchTreeNode<CoastGuardState> root) {
    String sol;
    for (int i = 0; i < Integer.MAX_VALUE; ++i) {
      sol = limitedDepthFirstSearch(root, i);
      // need to clear visited states after each call to Limited DFS
      visitedStates.clear();
      if (sol != Constants.NO_PATH) {
        return sol;
      }
    }
    return Constants.NO_PATH;
  }

  private String uniformCostSearch(SearchTreeNode<CoastGuardState> root) {
    PriorityQueue<SearchTreeNode<CoastGuardState>> q = new PriorityQueue<SearchTreeNode<CoastGuardState>>(
        (node1, node2) -> (int) (node1.getPathCost() - node2.getPathCost()));
    q.add(root);
    StringBuilder sb = new StringBuilder();
    int exploredNodes = 0;
    while (!q.isEmpty()) {
      SearchTreeNode<CoastGuardState> node = q.poll();
      exploredNodes++;
      if (this.goalTest(node.getState())) {
        String plan = constructPlan(node);
        sb.append(plan).append(";");
        sb.append(node.getState().getDeaths()).append(";");
        sb.append(node.getState().getRetrieves()).append(";");
        sb.append(exploredNodes);
        break;
      }
      for (Action<CoastGuardState> action : this.getActions()) {
        CoastGuardState resultState = action.perform(node.getState());
        String hash;
        if (resultState != null && !visitedStates.contains(hash = resultState.getHash())) {
          visitedStates.add(hash);
          SearchTreeNode<CoastGuardState> resultNode = new SearchTreeNode<CoastGuardState>(resultState, node, action,
              node.getDepth() + 1, resultState.getDeaths() + node.getDepth() + 1);
          q.add(resultNode);
        }
      }
    }
    return sb.toString();
  }

  private String greedySearch(SearchTreeNode<CoastGuardState> root, Heuristic heuristic) {
    // TODO: implement greedy search
    return "";
  }

  private String aStarSearch(SearchTreeNode<CoastGuardState> root, AStarHeuristic heuristic) {
    PriorityQueue<SearchTreeNode<CoastGuardState>> pq = new PriorityQueue<SearchTreeNode<CoastGuardState>>(
        (node1, node2) -> {
          if (heuristic == AStarHeuristic.HURISTIC_1) {
            int h1 = Utils.getNumberOfNonEmptyCells(node1.getState().getShips())
                + node1.getState().getCurrentCapacity() > 0 ? 1 : 0;
            double g1 = node1.getPathCost();
            double f1 = g1 + h1;

            int h2 = Utils.getNumberOfNonEmptyCells(node2.getState().getShips())
                + node2.getState().getCurrentCapacity() > 0 ? 1 : 0;
            double g2 = node2.getPathCost();
            double f2 = g2 + h2;

            return (int) (f1 - f2);
          }
          return 0;
        });

    pq.add(root);
    StringBuilder sb = new StringBuilder();
    int exploredNodes = 0;
    while (!pq.isEmpty()) {
      SearchTreeNode<CoastGuardState> node = pq.poll();
      exploredNodes++;
      if (this.goalTest(node.getState())) {
        String plan = constructPlan(node);
        sb.append(plan).append(";");
        sb.append(node.getState().getDeaths()).append(";");
        sb.append(node.getState().getRetrieves()).append(";");
        sb.append(exploredNodes);
        break;
      }
      for (Action<CoastGuardState> action : this.getActions()) {
        CoastGuardState resultState = action.perform(node.getState());
        String hash;
        if (resultState != null && !visitedStates.contains(hash = resultState.getHash())) {
          visitedStates.add(hash);
          SearchTreeNode<CoastGuardState> resultNode = new SearchTreeNode<CoastGuardState>(resultState, node, action,
              node.getDepth() + 1, resultState.getDeaths() + node.getDepth() + 1);
          pq.add(resultNode);
        }
      }
    }

    return sb.toString();
  }

  public static String solve(String grid, String searchStrategy, boolean visualise) {
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

    // Solving
    CoastGuardState rootState = new CoastGuardState(cgRow, cgCol, 0, 0, 0, shipsGrid);
    SearchTreeNode<CoastGuardState> rootNode = new SearchTreeNode<CoastGuardState>(rootState, null, null, 0, 0);

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
        res = coastGuardSearchProblem.breadthFirstSearch(rootNode);
        break;
      case Constants.DepthFirstSearch:
        res = coastGuardSearchProblem.depthFirstSearch(rootNode);
        break;
      case Constants.IterativeDeepeningSearch:
        res = coastGuardSearchProblem.iterativeDeepeningSearch(rootNode);
        break;
      case Constants.UniformCostSearch:
        res = coastGuardSearchProblem.uniformCostSearch(rootNode);
        break;
      // case Constants.GreedySearchWithEuclideanDistanceSearch:
      // res = coastGuardSearchProblem.greedySearch(rootNode, Heuristic.EUCLIDIAN);
      // break;
      // case Constants.GreedySearchWithManhattanDistanceSearch:
      // res = coastGuardSearchProblem.greedySearch(rootNode, Heuristic.MANHATTAN);
      // break;
      case Constants.AStarSearchWithNonEmptyCellsHeuristic:
        res = coastGuardSearchProblem.aStarSearch(rootNode, AStarHeuristic.HURISTIC_1);
        break;
      // case Constants.AStarSearchWithManhattanDistanceSearch:
      // res = coastGuardSearchProblem.aStarSearch(rootNode, Heuristic.MANHATTAN);
      // break;
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
}
