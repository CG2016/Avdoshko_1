/**
 * Created by Evgeny on 21.02.2016.
 */
public class ColorFormat {

    private static final double RGB_MAX = 255.0;
    private static final double CMY_MAX = 255.0;
    private static final double HUE_MAX = 360.0;
    private static final double SV_MAX = 100.0;
    private static final double LUV_MAX = 100.0;

    // Observer= 2, Illuminant= D65
    private static final double X_MAX = 95.047;
    private static final double Y_MAX = 100.0;
    private static final double Z_MAX = 108.883;

    private double red;
    private double green;
    private double blue;

    private double cyan;
    private double magenta;
    private double yellow;

    private int hue;
    private double saturation;
    private double value;

    private double L;
    private double U;
    private double V;

    private double X;
    private double Y;
    private double Z;

    private ColorFormat() {

    }

    public static ColorFormat colorRgb(int red, int green, int blue) {
        ColorFormat colorFormat = new ColorFormat();
        colorFormat.setRed(red);
        colorFormat.setGreen(green);
        colorFormat.setBlue(blue);
        colorFormat.updateByRgb();
        return colorFormat;
    }

    public static ColorFormat colorCmy(int cyan, int mangetta, int yellow) {
        ColorFormat colorFormat = new ColorFormat();
        colorFormat.setCyan(cyan);
        colorFormat.setMagenta(mangetta);
        colorFormat.setYellow(yellow);
        colorFormat.updateByCmy();
        return colorFormat;
    }

    public static ColorFormat colorHsv(int hue, int saturation, int value) {
        ColorFormat colorFormat = new ColorFormat();
        colorFormat.setHue(hue);
        colorFormat.setSaturation(saturation);
        colorFormat.setValue(value);
        colorFormat.updateByHsv();
        return colorFormat;
    }

    public void updateByRgb() {
        convertRgbToHsv();
        convertRgbToCmy();
        convertRgbToLuv();
    }

    public void updateByHsv() {
        convertHsvToRgb();
        convertHsvToCmy();
        convertHsbToLuv();
    }

    public void updateByCmy() {
        convertCmyToRgb();
        convertCmyToHsv();
        convertCmyToLuv();
    }

    public void updateByLuv() {
        convertLuvToRgb();
     //   updateByRgb();
    }

    private void convertCmyToHsv() {
        convertCmyToRgb();
        convertRgbToHsv();
    }

    private void convertHsvToCmy() {
        convertHsvToRgb();
        convertRgbToCmy();
    }

    private void convertRgbToLuv() {
        convertRgbToXyz();
        convertXyzToLuv();
    }

    private void convertHsbToLuv() {
        convertHsvToRgb();
        convertRgbToXyz();
        convertXyzToLuv();
    }

    private void convertCmyToLuv() {
        convertCmyToRgb();
        convertRgbToXyz();
        convertXyzToLuv();
    }

    private void convertLuvToRgb() {
        convertLuvToXyz();
        convertXyzToRgb();
    }

    private void convertLuvToXyz() {
        if (Double.compare(U, 0) == 0 && Double.compare(V, 0) == 0 && Double.compare(L, -100) == 0) {
            X = 0;
            Y = 0;
            Z = 0;
            return;
        }
        double tmpY = (L + 16) / 116.0;
        tmpY = Double.compare(Math.pow(tmpY, 3), 0.008856) > 0 ? Math.pow(tmpY, 3) : (tmpY - 16.0 / 116) / 7.787;

        double refU = (4 * X_MAX) / (X_MAX + (15 * Y_MAX) + (3 * Z_MAX));
        double refV = (9 * Y_MAX) / (X_MAX + (15 * Y_MAX) + (3 * Z_MAX));

        double tmpU = U / (13 * L) + refU;
        double tmpV = V / (13 * L) + refV;

        Y = tmpY * 100;
        X = -(9 * Y * tmpU) / ((tmpU - 4) * tmpV - tmpU * tmpV);
        Z = (9 * Y - (15 * tmpV * Y) - (tmpV * X)) / (3 * tmpV);

        Y = (Y < Y_MAX && Y > 0.) ? Y : (Y < 0.) ? 0 : Y_MAX;
        X = (X < X_MAX && X > 0.) ? X : (X < 0.) ? 0 : X_MAX;
        Z = (Z < Z_MAX && Z > 0.) ? Z : (Z < 0.) ? 0 : Z_MAX;
    }

    private void convertXyzToRgb() {
        double tmpX = X / 100.0;
        double tmpY = Y / 100.0;
        double tmpZ = Z / 100.0;

        double tmpR = tmpX * 3.2406 + tmpY * -1.5372 + tmpZ * -0.4986;
        double tmpG = tmpX * -0.9689 + tmpY * 1.8758 + tmpZ * 0.0415;
        double tmpB = tmpX * 0.0557 + tmpY * -0.2040 + tmpZ * 1.0570;

        tmpR = tmpR > 0.0031308 ? 1.055 * Math.pow(tmpR, 1 / 2.4) - 0.055 : 12.92 * tmpR;
        tmpG = tmpG > 0.0031308 ? 1.055 * Math.pow(tmpG, 1 / 2.4) - 0.055 : 12.92 * tmpG;
        tmpB = tmpB > 0.0031308 ? 1.055 * Math.pow(tmpB, 1 / 2.4) - 0.055 : 12.92 * tmpB;

        red = tmpR;
        green = tmpG;
        blue = tmpB;
    }

    private void convertRgbToXyz() {
        double tmpRed = red;
        double tmpBlue = blue;
        double tmpGreen = green;

        tmpRed = tmpRed > 0.04045 ? Math.pow((tmpRed + 0.055) / 1.055, 2.4) : tmpRed / 12.92;
        tmpBlue = tmpBlue > 0.04045 ? Math.pow((tmpBlue + 0.055) / 1.055, 2.4) : tmpBlue / 12.92;
        tmpGreen = tmpGreen > 0.04045 ? Math.pow((tmpGreen + 0.055) / 1.055, 2.4) : tmpGreen / 12.92;

        tmpRed *= 100;
        tmpGreen *= 100;
        tmpBlue *= 100;

        X = tmpRed * 0.4124 + tmpGreen * 0.3576 + tmpBlue * 0.1805;
        Y = tmpRed * 0.2126 + tmpGreen * 0.7152 + tmpBlue * 0.0722;
        Z = tmpRed * 0.0193 + tmpGreen * 0.1192 + tmpBlue * 0.9505;
    }

    private void convertXyzToLuv() {
        if (Double.compare(X, 0) == 0 && Double.compare(Y, 0) == 0 && Double.compare(Z, 0) == 0) {
            U = 0;
            V = 0;
            L = 0;
            return;
        }
        double tmpU = (4 * X) / (X + (15 * Y) + (3 * Z));
        double tmpV = (9 * Y) / (X + (15 * Y) + (3 * Z));
        double tmpY = Y / 100;
        tmpY = tmpY > 0.008856 ? Math.pow(tmpY, 1.0 / 3) : (7.787 * tmpY) + (16.0 / 116);

        double refU = (4 * X_MAX) / (X_MAX + (15 * Y_MAX) + (3 * Z_MAX));
        double refV = (9 * Y_MAX) / (X_MAX + (15 * Y_MAX) + (3 * Z_MAX));

        L = (116 * tmpY) - 16;
        U = 13 * L * (tmpU - refU);
        V = 13 * L * (tmpV - refV);
    }

    private void convertCmyToRgb() {
        red = 1 - cyan;
        green = 1 - magenta;
        blue = 1 - yellow;
    }

    private void convertRgbToCmy() {
        cyan = 1 - red;
        magenta = 1 - green;
        yellow = 1 - blue;
    }

    private void convertRgbToHsv() {
        double min = red < blue && red < green ? red : (blue < green ? blue : green);
        double max = red > blue && red > green ? red : (blue > green ? blue : green);
        value = max;
        saturation = max == 0 ? 0 : 1 - min / max;
        if (Double.compare(max, min) == 0) {
            hue = 0;
        } else if (Double.compare(max, red) == 0)
            if (Double.compare(green, blue) > 0) {
                hue = (int) (60 * (green - blue) / (max - min));
            } else if (Double.compare(green, blue) == 0) {
                hue = 0;
            } else {
                hue = (int) (60 * (green - blue) / (max - min)) + 360;
            }
        else if (Double.compare(max, green) == 0) {
            hue = (int) (60 * (blue - red) / (max - min)) + 120;
        } else if (Double.compare(max, blue) == 0) {
            hue = (int) (60 * (red - green) / (max - min)) + 240;
        }
        hue = hue % 360;
    }

    private void convertHsvToRgb() {
        int n = ((int) (hue / 60.0)) % 6;
        double v_min = (100 - (saturation * 100)) * (value * 100) / 100;
        double a = ((value * 100) - v_min) * ((hue % 60) / 60.0);
        double v_inc = v_min + a;
        double v_dec = (value * 100) - a;
        switch (n) {
            case 0:
                red = (value * 100);
                green = v_inc;
                blue = v_min;
                break;
            case 1:
                red = v_dec;
                green = (value * 100);
                blue = v_min;
                break;
            case 2:
                red = v_min;
                green = (value * 100);
                blue = v_inc;
                break;
            case 3:
                red = v_min;
                green = v_dec;
                blue = (value * 100);
                break;
            case 4:
                red = v_inc;
                green = v_min;
                blue = (value * 100);
                break;
            case 5:
                red = (value * 100);
                green = v_min;
                blue = v_dec;
                break;
        }
        red /= 100;
        green /= 100;
        blue /= 100;
    }

    public int getRed() {
        return (red <= 1. && red>=0.) ? (int) (red * RGB_MAX) : (red < 0 ) ? 0 :(int) RGB_MAX;
    }

    public void setRed(int red) {
        this.red = red * 1.0 / RGB_MAX;
    }

    public int getGreen() {
        return (green <= 1. && green>=0.) ? (int) (green * RGB_MAX) : (green < 0 ) ? 0 :(int) RGB_MAX;
    }

    public void setGreen(int green) {
        this.green = green * 1.0 / RGB_MAX;
    }

    public int getBlue() {

        return (blue <= 1. && blue>=0.) ? (int) (blue * RGB_MAX) : (blue < 0 ) ? 0 :(int) RGB_MAX;
    }

    public void setBlue(int blue) {
        this.blue = blue * 1.0 / RGB_MAX;
    }

    public int getYellow() {
        return (yellow <= 1.) ? (int) (yellow * CMY_MAX) : (int) yellow;
    }

    public void setYellow(double yellow) {
        this.yellow = yellow * 1.0 / CMY_MAX;
    }

    public int getMagenta() {
        return (magenta <= 1.) ? (int) (magenta * CMY_MAX) : (int) CMY_MAX;
    }

    public void setMagenta(double magenta) {
        this.magenta = magenta * 1.0 / CMY_MAX;
    }

    public int getCyan() {
        return (cyan <= 1.) ? (int) (cyan * CMY_MAX) : (int) CMY_MAX;
    }

    public void setCyan(double cyan) {
        this.cyan = cyan * 1.0 / CMY_MAX;
    }

    public int getHue() {
        return (hue <= HUE_MAX) ? hue : (int) HUE_MAX;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public int getSaturation() {
        return (saturation <= 1.) ? (int) (saturation * SV_MAX) : (int) saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation * 1.0 / SV_MAX;
    }

    public int getValue() {
        return (value <= 1.) ? (int) (value * SV_MAX) : (int) SV_MAX;
    }

    public void setValue(int value) {
        this.value = value * 1.0 / SV_MAX;
    }

    public int getL() {
        return (int) L;
    }

    public void setL(int l) {
        L = l;
    }

    public int getU() {
        return (int) U;
    }

    public void setU(int u) {
        U = u;
    }

    public int getV() {
        return (int) V;
    }

    public void setV(int v) {
        V = v;
    }
}
