package code.utils;

public class Utils {
  public static int randomInt(int min, int max) {
    return (int) (Math.random() * (max - min + 1) + min);
  }

  public static String arrayToString(int arr[][]) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.length; ++i) {
      for (int j = 0; j < arr[i].length; ++j) {
        if (arr[i][j] != 0) {
          sb.append(i).append("#").append(j).append("#").append(arr[i][j]).append("#");
        }
      }
    }

    if (sb.length() == 0) {
      return "";
    }

    return sb.substring(0, sb.length() - 1);
  }
}
