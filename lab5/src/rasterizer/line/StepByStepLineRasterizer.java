package rasterizer.line;

import java.awt.*;

/**
 * Created by Evgeny on 24.04.2016.
 */
public class StepByStepLineRasterizer implements LineRasterizer {

    @Override
    public double[][] rasterize(Point start, Point end) {
        int deltaX = Math.abs(end.x - start.x);

        double k = (end.y - start.y)*1.0 / (end.x - start.x);
        double b = start.y*1.0 - start.x * k;

        double[][] points = new double[2][deltaX];
        points[0][0] = start.x;
        points[1][0] = start.y;
//        for (int i=1; i<deltaX-1; i++) {
        for (int i=1; i<deltaX; i++) {
            int x = start.x + i;
            int y = (int) (k * x + b);
            points[0][i] = x;
            points[1][i] = y;
        }
//        points[0][deltaX-1] = end.x;
//        points[1][deltaX-1] = end.y;
        return points;
    }

    @Override
    public String toString() {
        return "Step-By-Step Algorithm";
    }
}
