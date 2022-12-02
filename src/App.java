import code.CoastGuard;

public class App {
    public static void main(String[] args) throws Exception {

        String grid0 = "5,6;50;0,1;0,4,3,3;1,1,90;";
        String sol = CoastGuard.solve(grid0, "BF", false);
        System.out.println(sol);
    }
}
