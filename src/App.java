/**
 * Created by igoryan on 15.11.15.
 */
public class App {
    private static double EPSILON = Math.pow(10, -8);
    public static void main(String[] args) {
        Solver solver = new Iteration(EPSILON);
        solver.solve();
        System.out.println("x = " + solver.getX());
        System.out.println("y = " + solver.getY());
        System.out.println("iterations = " + solver.getIterations());
    }
}
