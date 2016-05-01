import java.awt.*;
import java.awt.image.*;

/**
 * Created by Evgeny on 01.05.2016.
 */
public class LinearImageConverter {

    private BufferedImage image;
    private int maxGray;
    private int minGray;

    public LinearImageConverter() {
    }

    public LinearImageConverter(BufferedImage input) {
        this.image = input;
        findMaxGray();
        findMinGray();
    }

    public BufferedImage add(int x) {
        BufferedImage result = copyImage(image);
        DataBuffer data = result.getData().getDataBuffer();
        for (int i=0; i<data.getSize(); i++) {
            int gray = data.getElem(i);
            gray += x;
            if (gray > 255) gray = 255;
            if (gray < 0)  gray = 0;
            data.setElem(i, gray);
        }
        result.setData(Raster.createRaster(result.getData().getSampleModel(), data, new Point(0, 0)));
        return result;
    }

    public BufferedImage multiple(double x) {
        BufferedImage result = copyImage(image);
        DataBuffer data = result.getData().getDataBuffer();
        for (int i=0; i<data.getSize(); i++) {
            int gray = data.getElem(i);
            gray *= x;
            if (gray > 255) gray = 255;
            if (gray < 0)  gray = 0;
            data.setElem(i, gray);
        }
        result.setData(Raster.createRaster(result.getData().getSampleModel(), data, new Point(0, 0)));
        return result;
    }

    public BufferedImage degree(double x) {
        BufferedImage result = copyImage(image);
        DataBuffer data = result.getData().getDataBuffer();
        for (int i=0; i<data.getSize(); i++) {
            int gray = data.getElem(i);
            gray = (int) (255 * Math.pow(gray*1.0/maxGray*1.0, x));
            if (gray > 255) gray = 255;
            if (gray < 0)  gray = 0;
            data.setElem(i, gray);
        }
        result.setData(Raster.createRaster(result.getData().getSampleModel(), data, new Point(0, 0)));
        return result;
    }

    public BufferedImage logarithm() {
        BufferedImage result = copyImage(image);
        DataBuffer data = result.getData().getDataBuffer();
        for (int i=0; i<data.getSize(); i++) {
            int gray = data.getElem(i);
            gray = (int) (255 * (Math.log(1 + gray)/Math.log(1 + maxGray)));
            if (gray > 255) gray = 255;
            if (gray < 0)  gray = 0;
            data.setElem(i, gray);
        }
        result.setData(Raster.createRaster(result.getData().getSampleModel(), data, new Point(0, 0)));
        return result;
    }

    public BufferedImage makeNegative() {
        BufferedImage result = copyImage(image);
        DataBuffer data = result.getData().getDataBuffer();
        for (int i=0; i<data.getSize(); i++) {
            int gray = data.getElem(i);
            gray = 255 - gray;
            data.setElem(i, gray);
        }
        result.setData(Raster.createRaster(result.getData().getSampleModel(), data, new Point(0, 0)));
        return result;
    }

    public BufferedImage contrast() {
        BufferedImage result = copyImage(image);
        DataBuffer data = result.getData().getDataBuffer();
        for (int i=0; i<data.getSize(); i++) {
            int gray = data.getElem(i);
            gray = (255 / (maxGray - minGray))*(gray - minGray);
            data.setElem(i, gray);
        }
        result.setData(Raster.createRaster(result.getData().getSampleModel(), data, new Point(0, 0)));
        return result;
    }

    private static BufferedImage copyImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        findMaxGray();
        findMinGray();
    }

    private void findMinGray() {
        DataBuffer data = image.getData().getDataBuffer();
        minGray = data.getElem(0);
        for (int i=0; i<data.getSize(); i++) {
            if (data.getElem(i) < minGray)
                minGray = data.getElem(i);
        }
    }

    private void findMaxGray() {
        DataBuffer data = image.getData().getDataBuffer();
        maxGray = 0;
        for (int i=0; i<data.getSize(); i++) {
            if (data.getElem(i) > maxGray)
                maxGray = data.getElem(i);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
