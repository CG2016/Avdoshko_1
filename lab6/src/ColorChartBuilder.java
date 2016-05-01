import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by Evgeny on 21.03.2016.
 */
public class ColorChartBuilder {

    double[][] channelRed;
    double[][] channelGreen;
    double[][] channelBlue;

    double redAverage;
    double greenAverage;
    double blueAverage;

    public ColorChartBuilder(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);
        getChannels(dataBuffInt);
    }

    double[][] channelRed() {
        return channelRed;
    }

    double[][] channelGreen() {
        return channelGreen;
    }

    double[][] channelBlue() {
        return channelBlue;
    }

    double getRedAverage() {return redAverage;}

    double getGreenAverage() {return greenAverage;}

    double getBlueAverage() {return blueAverage;}

    public void getChannels(int[] pixels) {
        channelRed = new double[2][256];
        channelGreen = new double[2][256];
        channelBlue = new double[2][256];
        redAverage = greenAverage = blueAverage = 0;
        for (int i=0; i<256; i++) {
            channelRed[0][i] = channelGreen[0][i] = channelBlue[0][i] = (double) i;
            channelRed[1][i] = channelGreen[1][i] = channelBlue[1][i] = 0;
        }
        for (int pixel : pixels) {
            channelBlue[1][(pixel)&0xFF]++;
            channelGreen[1][(pixel>>8)&0xFF]++;
            channelRed[1][(pixel>>16)&0xFF]++;
            blueAverage += (pixel)&0xFF;
            greenAverage += (pixel>>8)&0xFF;
            redAverage += (pixel>>16)&0xFF;
        }
        redAverage /= pixels.length;
        greenAverage /= pixels.length;
        blueAverage /= pixels.length;
    }
}
