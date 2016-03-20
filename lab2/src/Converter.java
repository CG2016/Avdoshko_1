import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Created by Evgeny on 02.03.2016.
 */
public class Converter {

    public static BufferedImage convert(BufferedImage image, ColorFormat colorBefore, ColorFormat colorAfter, double distance) {
        colorBefore.updateByRgb();
        colorAfter.updateByRgb();
        BufferedImage updatedImage = copyImage(image);
        Color c;
        ColorFormat tmpColor;
        int w = image.getWidth();
        int h = image.getHeight();
        int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);
        for (int i=0; i<dataBuffInt.length; i++) {
            c = new Color(dataBuffInt[i]);
            tmpColor = ColorFormat.colorRgb(c.getRed(), c.getGreen(), c.getBlue());
            tmpColor.updateByRgb();
            double[] distances = distance(colorBefore, tmpColor);
            if (checkDistances(distances, distance)) {
                tmpColor.setL(colorAfter.getL()+(int) (distances[0]));
                tmpColor.setU(colorAfter.getU()+(int) (distances[1]));
                tmpColor.setV(colorAfter.getV()+(int) (distances[2]));
                tmpColor.updateByLuv();
            }
            c = new Color(tmpColor.getRed(), tmpColor.getGreen(), tmpColor.getBlue());
            dataBuffInt[i] = c.getRGB();
        }
        updatedImage.setRGB(0, 0, w, h, dataBuffInt, 0, w);
        return updatedImage;
    }

    private static boolean checkDistances(double[] distances, double radius) {
        return (Math.abs(distances[0]) < radius && Math.abs(distances[1]) < radius && Math.abs(distances[2]) < radius);
    }

    private static double[] distance(ColorFormat a, ColorFormat b) {
        double[] distance = new double[3];
        distance[0] = Math.abs(a.getL() - b.getL());
        distance[1] = Math.abs(a.getU() - b.getU());
        distance[2] = Math.abs(a.getV() - b.getV());
        return distance;
    }

    private static BufferedImage copyImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
