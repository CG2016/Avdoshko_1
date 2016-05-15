package vectorimage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny on 15.05.2016.
 */
public class VectorImage {
    public List<Line> lines;
    public Rectangle clippingWindow;

    public static VectorImage create(InputStream input) {
        VectorImage vectorImage = new VectorImage();
        vectorImage.lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        try {
            int n = Integer.parseInt(reader.readLine());
            for (int i=0; i<n; i++) {
                vectorImage.lines.add(Line.create(reader.readLine()));
            }
            vectorImage.clippingWindow = Rectangle.create(reader.readLine());
        } catch (IOException e) {

        }
        return vectorImage;
    }
}
