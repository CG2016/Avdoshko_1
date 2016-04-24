package rasterizer.line;

import java.awt.*;

/**
 * Created by Evgeny on 24.04.2016.
 */
public interface LineRasterizer {

    double[][] rasterize(Point start, Point end);

}
