package vectorimage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Evgeny on 15.05.2016.
 */
public class ShapeVectorImage {
    public Shape shape;
    public Rectangle clippingWindow;

    public static ShapeVectorImage create(InputStream input) {
        ShapeVectorImage shapeVectorImage = new ShapeVectorImage();
        shapeVectorImage.shape = new Shape();
        shapeVectorImage.shape.lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        try {
            String[] points = reader.readLine().split(" ");
            for (int i=0; i<points.length-2; i+=2) {
                Point src = new Point(Integer.parseInt(points[i]), Integer.parseInt(points[i+1]));
                Point dest = new Point(Integer.parseInt(points[i+2]), Integer.parseInt(points[i+3]));
                shapeVectorImage.shape.lines.add(new Line(src, dest));
            }
            shapeVectorImage.shape.lines.add(new Line(shapeVectorImage.shape.lines.get(shapeVectorImage.shape.lines.size()-1).dest, shapeVectorImage.shape.lines.get(0).src));
            shapeVectorImage.clippingWindow = Rectangle.create(reader.readLine());
        } catch (IOException e) {

        }
        return shapeVectorImage;
    }
}
