package clipping;

import org.jfree.data.xy.DefaultXYDataset;
import vectorimage.ShapeVectorImage;

/**
 * Created by Evgeny on 15.05.2016.
 */
public interface ShapeClippingAlgorithm {
    ShapeVectorImage clip(ShapeVectorImage shapeVectorImage);
    double getExecutingTime();
}
