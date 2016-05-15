package clipping;

import vectorimage.Line;
import vectorimage.Point;
import vectorimage.Shape;
import vectorimage.ShapeVectorImage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Evgeny on 15.05.2016.
 */
public class SutherlandHodgmanAlgorithm implements ShapeClippingAlgorithm {

    private ShapeVectorImage shapeVectorImage;

    private double time;

    @Override
    public ShapeVectorImage clip(ShapeVectorImage shapeVectorImage) {
        long startTime = System.nanoTime();
        this.shapeVectorImage = shapeVectorImage;
        ShapeVectorImage clipped = new ShapeVectorImage();
        clipped.clippingWindow = shapeVectorImage.clippingWindow;
        clipped.shape = new Shape();
        clipped.shape.lines = shapeVectorImage.shape.lines;
        clipByRightBound(clipped);
        clipByDownBound(clipped);
        clipByLeftBound(clipped);
        clipByUpperBound(clipped);
        long finishTime = System.nanoTime();
        time = (finishTime - startTime) / 1.E9;
        return clipped;
    }

    private void clipByUpperBound(ShapeVectorImage clipped) {
        Iterator<Line> i = clipped.shape.lines.iterator();
        while (i.hasNext()) {
            Line line = i.next();
            if ((line.src.y <= clipped.clippingWindow.upperRight.y) && (line.dest.y <= clipped.clippingWindow.upperRight.y)) {

            } else if ((line.src.y > clipped.clippingWindow.upperRight.y) && (line.dest.y > clipped.clippingWindow.upperRight.y)) {
                i.remove();
            } else {
                double t = (clipped.clippingWindow.upperRight.y - line.src.y) / (line.dest.y - line.src.y);
                double x = line.src.x + t*(line.dest.x - line.src.x);
                double y = line.src.y + t*(line.dest.y - line.src.y);
                if ((line.src.y <= clipped.clippingWindow.upperRight.y) && (line.dest.y > clipped.clippingWindow.upperRight.y)) {
                    line.dest.x = x;
                    line.dest.y = y;
                } else {
                    line.src.x = x;
                    line.src.y = y;
                }
            }
        }
    }

    private void clipByLeftBound(ShapeVectorImage clipped) {
        Iterator<Line> i = clipped.shape.lines.iterator();
        while (i.hasNext()) {
            Line line = i.next();
            if ((line.src.x >= clipped.clippingWindow.lowerLeft.x) && (line.dest.x >= clipped.clippingWindow.lowerLeft.x)) {

            } else if ((line.src.x < clipped.clippingWindow.lowerLeft.x) && (line.dest.x < clipped.clippingWindow.lowerLeft.x)) {
                i.remove();
            } else {
                double t = (clipped.clippingWindow.lowerLeft.x - line.src.x) / (line.dest.x - line.src.x);
                double x = line.src.x + t*(line.dest.x - line.src.x);
                double y = line.src.y + t*(line.dest.y - line.src.y);
                if ((line.src.x >= clipped.clippingWindow.lowerLeft.x) && (line.dest.x < clipped.clippingWindow.lowerLeft.x)) {
                    line.dest.x = x;
                    line.dest.y = y;
                } else {
                    line.src.x = x;
                    line.src.y = y;
                }
            }
        }
    }

    private void clipByDownBound(ShapeVectorImage clipped) {
        Iterator<Line> i = clipped.shape.lines.iterator();
        while (i.hasNext()) {
            Line line = i.next();
            if ((line.src.y >= clipped.clippingWindow.lowerLeft.y) && (line.dest.y >= clipped.clippingWindow.lowerLeft.y)) {

            } else if ((line.src.y < clipped.clippingWindow.lowerLeft.y) && (line.dest.y < clipped.clippingWindow.lowerLeft.y)) {
                i.remove();
            } else {
                double t = (clipped.clippingWindow.lowerLeft.y - line.src.y) / (line.dest.y - line.src.y);
                double x = line.src.x + t*(line.dest.x - line.src.x);
                double y = line.src.y + t*(line.dest.y - line.src.y);
                if ((line.src.y >= clipped.clippingWindow.lowerLeft.y) && (line.dest.y < clipped.clippingWindow.lowerLeft.y)) {
                    line.dest.x = x;
                    line.dest.y = y;
                } else {
                    line.src.x = x;
                    line.src.y = y;
                }
            }
        }
    }

    private void clipByRightBound(ShapeVectorImage clipped) {
        Iterator<Line> i = clipped.shape.lines.iterator();
        while (i.hasNext()) {
            Line line = i.next();
            if ((line.src.x <= clipped.clippingWindow.upperRight.x) && (line.dest.x <= clipped.clippingWindow.upperRight.x)) {

            } else if ((line.src.x > clipped.clippingWindow.upperRight.x) && (line.dest.x > clipped.clippingWindow.upperRight.x)) {
                i.remove();
            } else {
                double t = (clipped.clippingWindow.upperRight.x - line.src.x) / (line.dest.x - line.src.x);
                double x = line.src.x + t*(line.dest.x - line.src.x);
                double y = line.src.y + t*(line.dest.y - line.src.y);
                if ((line.src.x <= clipped.clippingWindow.upperRight.x) && (line.dest.x > clipped.clippingWindow.upperRight.x)) {
                    line.dest.x = x;
                    line.dest.y = y;
                } else {
                    line.src.x = x;
                    line.src.y = y;
                }
            }
        }
    }

    @Override
    public double getExecutingTime() {
        return time;
    }
}
