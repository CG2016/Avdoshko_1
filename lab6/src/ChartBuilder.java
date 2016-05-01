import java.awt.*;
import java.awt.image.*;

/**
 * Created by Evgeny on 01.05.2016.
 */
public class ChartBuilder {

    BufferedImage image;
    double[][] channel;

    public ChartBuilder(BufferedImage image) {
        this.image = image;
    }

    public void build() {
        channel = new double[2][256];
        for (int i=0; i<256; i++) {
            channel[0][i] = (double) i;
            channel[1][i] = 0;
        }
        DataBuffer data = image.getData().getDataBuffer();
        for (int i=0; i<data.getSize(); i++) {
            channel[1][data.getElem(i)]++;
        }
    }

    public BufferedImage equalize() {
        double[] normalizedChannel = normalize();
        double[] integralHistogram = new double[256];
        for (int i=0; i<256; i++) {
            integralHistogram[i] = 0;
            for (int j=0; j<i; j++) {
                integralHistogram[i] += normalizedChannel[j];
            }
        }
        BufferedImage updatedImage = copyImage(image);
        DataBuffer data = image.getData().getDataBuffer();
        for (int i=0; i<data.getSize(); i++) {
            int gray = data.getElem(i);
            gray = (int) (integralHistogram[gray]*255);
            data.setElem(i, gray);
        }
        updatedImage.setData(Raster.createRaster(updatedImage.getData().getSampleModel(), data, new Point(0, 0)));
        return updatedImage;
    }

    private double[] normalize() {
        double[] normalizedChannel = new double[256];
        for (int i=0; i<256; i++) {
            normalizedChannel[i] = channel[1][i]/image.getData().getDataBuffer().getSize();
        }
        return normalizedChannel;
    }

    double[][] channel() {
        return channel;
    }

    private static BufferedImage copyImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
