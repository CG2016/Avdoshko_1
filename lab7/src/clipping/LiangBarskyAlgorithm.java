package clipping;

import org.jfree.data.xy.DefaultXYDataset;
import vectorimage.Line;
import vectorimage.Point;
import vectorimage.VectorImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Evgeny on 15.05.2016.
 */
public class LiangBarskyAlgorithm implements ClippingAlgorithm {

    VectorImage vectorImage;

    private double time;

    @Override
    public VectorImage clip(VectorImage vectorImage) {
        long startTime = System.nanoTime();
        VectorImage clippedVectorImage = new VectorImage();
        clippedVectorImage.clippingWindow = vectorImage.clippingWindow;
        clippedVectorImage.lines = new ArrayList<>();
        this.vectorImage = vectorImage;
        for (Line line : vectorImage.lines) {
            Line clippedLine = clipLine(line);
            if (clippedLine != null) {
                clippedVectorImage.lines.add(clippedLine);
            }
        }
        long finishTime = System.nanoTime();
        time = (finishTime - startTime) / 1.E9;
        return clippedVectorImage;
    }

    private Line clipLine(Line line) {
        if (((getMask(line.src)) & (getMask(line.dest))) != 0b00000000) {
            return null;
        }
        if ((getMask(line.src) == getMask(line.dest)) && (getMask(line.src) == 0b00000000)) {
            return line;
        }
        double dy = line.dest.y - line.src.y;
        double dx = line.dest.x - line.src.x;
        double[] t = new double[]{-1, -1, -1, -1};
        if (dy != 0) {
            t[0] = (vectorImage.clippingWindow.lowerLeft.y - line.src.y) / dy;
            t[2] = (vectorImage.clippingWindow.upperRight.y - line.src.y) / dy;
        }
        if (dx != 0) {
            t[1] = (vectorImage.clippingWindow.lowerLeft.x - line.src.x) / dx;
            t[3] = (vectorImage.clippingWindow.upperRight.x - line.src.x) / dx;
        }
        Line clippedLine = null;
        for (int i = 0; i < 4; i++) {
            if ((t[i] >= 0) && (t[i] <= 1)) {
                for (int j = i + 1; j < 4; j++) {
                    if ((t[j] >= 0) && (t[j] <= 1)) {
                        clippedLine = new Line(
                                new Point(line.src.x + t[i] * dx, line.src.y + t[i] * dy),
                                new Point(line.src.x + t[j] * dx, line.src.y + t[j] * dy)
                        );
                        break;
                    }
                }
            }
        }
        if (clippedLine == null) {
            if (getMask(line.src) == 0b00000000) {
                for (int i = 0; i < 4; i++) {
                    if ((t[i] >= 0) && (t[i] <= 1)) {
                        clippedLine = new Line(
                                line.src,
                                new Point(line.src.x + t[i] * dx, line.src.y + t[i] * dy)
                        );
                        break;
                    }
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if ((t[i] >= 0) && (t[i] <= 1)) {
                        clippedLine = new Line(
                                new Point(line.src.x + t[i] * dx, line.src.y + t[i] * dy),
                                line.dest
                                );
                        break;
                    }
                }
            }
        }
        return clippedLine;
    }

    @Override
    public double getExecutingTime() {
        return time;
    }

    private byte getMask(Point point) {
        byte b = 0b0;
        if (point.y > vectorImage.clippingWindow.upperRight.y)
            b |= 0b00001000;
        if (point.y < vectorImage.clippingWindow.lowerLeft.y)
            b |= 0b00000100;
        if (point.x > vectorImage.clippingWindow.upperRight.x)
            b |= 0b00000010;
        if (point.x < vectorImage.clippingWindow.lowerLeft.x)
            b |= 0b00000001;
        return b;
    }
}
