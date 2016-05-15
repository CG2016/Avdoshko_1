package clipping;

import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import vectorimage.Line;
import vectorimage.Point;
import vectorimage.VectorImage;

import java.util.*;

/**
 * Created by Evgeny on 15.05.2016.
 */
public class MidpointAlgorithm implements ClippingAlgorithm {

    private static final double MIN_LENGTH = 0.01;

    VectorImage vectorImage;

    private List<Line> fitLines = new ArrayList<>();
    private double time;

    @Override
    public VectorImage clip(VectorImage vectorImage) {
        long startTime = System.nanoTime();
        VectorImage clippedVectorImage = new VectorImage();
        clippedVectorImage.clippingWindow = vectorImage.clippingWindow;
        clippedVectorImage.lines = new ArrayList<>();
        this.vectorImage = vectorImage;
        for (Line line : vectorImage.lines) {

            fitLines = new ArrayList<>();
            clipLine(line);
            if (fitLines.size() != 0) {
                clippedVectorImage.lines.add(new Line(fitLines.get(0).src, fitLines.get(fitLines.size()-1).dest));
            }
        }
        long finishTime = System.nanoTime();
        time = (finishTime - startTime)/1.E9;
        return clippedVectorImage;
    }

    @Override
    public double getExecutingTime() {
        return time;
    }

    private void clipLine(Line line) {
        if (line.length() < MIN_LENGTH) {
            fitLines.add(line);
            return;
        }
        if ( ((getMask(line.src)) & (getMask(line.dest))) != 0b00000000 ) {
            return;
        }
        if ((getMask(line.src) == getMask(line.dest)) && (getMask(line.src) == 0b00000000)) {
            fitLines.add(line);
            return;
        }
        clipLine(new Line(line.src, new Point(
                    (line.src.x + line.dest.x)/2,
                    (line.src.y + line.dest.y)/2
        )));
        clipLine(new Line(new Point(
                (line.src.x + line.dest.x)/2,
                (line.src.y + line.dest.y)/2),
                line.dest
        ));
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
