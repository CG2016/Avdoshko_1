package rasterizer.circle;

import java.awt.*;

/**
 * Created by Evgeny on 24.04.2016.
 */
public interface CircleRasterizer {
    double[][] rasterize(Point center, int radius);
}
