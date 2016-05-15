package clipping;

import org.jfree.data.xy.DefaultXYDataset;
import vectorimage.VectorImage;

/**
 * Created by Evgeny on 15.05.2016.
 */
public interface ClippingAlgorithm {
    VectorImage clip(VectorImage vectorImage);
    double getExecutingTime();
}
