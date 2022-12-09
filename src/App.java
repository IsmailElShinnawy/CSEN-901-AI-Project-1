import code.CoastGuard;

public class App {
    public static void main(String[] args) throws Exception {

        String grid0 = "7,5;40;2,3;3,6;1,1,10,4,5,90;";
        String sol1 = CoastGuard.solve(grid0, "BF", true);
        System.out.println(sol1);
        // String sol2 = CoastGuard.solve(grid0, "AS1", false);
        // System.out.println(sol2);
        // String sol3 = CoastGuard.solve(grid0, "AS2", false);
        // System.out.println(sol3);
    }
}
