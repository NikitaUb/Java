import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    //метод позволяет генератору фракталов определить наиболее «интересную» область
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }
    public int numIterations(double x, double y)
    {
        double Real = 0;
        double Image = 0;
        int Iteration = 0;
        while ((Real*Real + Image* Image < 4) && (Iteration < MAX_ITERATIONS))
        {
            Real = Real * Real - Image * Image  + x;
            Image = 2 * Real * Image  + y;
            Iteration ++;
        }
        if (Iteration == MAX_ITERATIONS) return -1;
        return Iteration;
    }

}