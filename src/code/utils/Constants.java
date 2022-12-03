package code.utils;

public abstract class Constants {
  public static final String BreadthFirstSearch = "BF";
  public static final String DepthFirstSearch = "DF";
  public static final String IterativeDeepeningSearch = "ID";
  public static final String GreedySearchWithManhattanDistanceSearch = "GR1";
  public static final String GreedySearchWithEuclideanDistanceSearch = "GR2";
  public static final String AStarSearchWithManhattanDistanceSearch = "AS1";
  public static final String AStarSearchWithEuclideanDistanceSearch = "AS2";

  public static final int EMPTY_CELL = 0;
  public static final int WRECK = -1;

  public static final String NO_PATH = "NO_PATH";
}
