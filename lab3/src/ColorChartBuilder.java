import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by Evgeny on 21.03.2016.
 */
public class ColorChartBuilder {

    double[][] channelRed;
    double[][] channelGreen;
    double[][] channelBlue;

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

    public void getChannels(int[] pixels) {
        channelRed = new double[2][256];
        channelGreen = new double[2][256];
        channelBlue = new double[2][256];
        for (int i=0; i<256; i++) {
            channelRed[0][i] = channelGreen[0][i] = channelBlue[0][i] = (double) i;
            channelRed[1][i] = channelGreen[1][i] = channelBlue[1][i] = 0;
        }
        for (int pixel : pixels) {
            channelRed[1][(pixel)&0xFF]++;
            channelGreen[1][(pixel>>8)&0xFF]++;
            channelBlue[1][(pixel>>16)&0xFF]++;
        }
    }
}
