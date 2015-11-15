import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by igoryan on 15.11.15.
 */
public class Iteration implements Solver {
    private double x = 0.1;
    private double y = 0.1;
    private double eps;
    private long iterations;
    private final String FILE_NAME = "output.txt";

    @Override
    public long getIterations() {
        return iterations;
    }

    public Iteration(double eps) {
        this.eps = eps;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void solve() {
        File out = new File(FILE_NAME);
        try {
            PrintWriter writer = new PrintWriter(out);
            double r = getResidual();
            double xLast;
            double yLast;
            do {
                xLast = x;
                yLast = y;
                x = getIterationXValue();
                y = getIterationYValue();
                r = getResidual();
                ++iterations;
                writer.println(iterations + " " + Math.log(Math.log(r)));
            }
            while (Math.abs(getNorm(x, y) - getNorm(xLast, yLast)) > eps);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private double getNorm(double x, double y) {
        return Math.abs(x) + Math.abs(y);
    }

    private double getFirstEquationValue() {
        return Math.pow(2, 1.d / 2.d) * x * Math.pow(Math.E, x) - Math.cos(y - 0.1);
    }

    private double getSecondEquationValue() {
        return 2 * y * Math.pow(Math.E, y) - Math.sin(x - 0.1);
    }

    private double getIterationXValue() {
        return Math.pow(2, 1.d / 2.d) * Math.cos(y - 0.1) * Math.pow(Math.E, -x);
    }

    private double getIterationYValue() {
        return Math.pow(2, -1) * Math.sin(x - 0.1) * Math.pow(Math.E, -y);
    }

    private double getResidual() {
        return Math.abs(getFirstEquationValue()) + Math.abs(getSecondEquationValue());
    }
}