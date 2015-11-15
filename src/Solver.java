/**
 * Created by igoryan on 15.11.15.
 */
public interface Solver {
    double getX();
    double getY();
    void solve();
    long getIterations();
}
