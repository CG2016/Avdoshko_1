import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Evgeny on 21.02.2016.
 */
public class ColorPicker {
    private JLabel labelRgb;
    private JSlider sliderRed;
    private JSlider sliderGreen;
    private JSlider sliderBlue;
    private JPanel panelMain;
    private JLabel labelHsv;
    private JSlider sliderHue;
    private JSlider sliderSaturation;
    private JSlider sliderValue;
    private JPanel panelColor;
    private JSlider sliderCyan;
    private JSlider sliderMagenta;
    private JSlider sliderYellow;
    private JSlider sliderL;
    private JSlider sliderU;
    private JSlider sliderV;
    private JLabel labelCmy;
    private JLabel labelLuv;

    private boolean isUpdating = false;

    private ColorFormat colorFormat = ColorFormat.colorRgb(50, 100, 200);

    public ColorPicker() {
        updateRgb();
        updateHsv();
        updateCmy();
        updateLuv();
        panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
        sliderRed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setRed(source.getValue());
                    colorFormat.updateByRgb();
                    updateHsv();
                    updateCmy();
                    updateLuv();
                    labelRgb.setText(String.format("RGB (%s, %s, %s)", colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });
        sliderBlue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setBlue(source.getValue());
                    colorFormat.updateByRgb();
                    updateHsv();
                    updateCmy();
                    updateLuv();
                    labelRgb.setText(String.format("RGB (%s, %s, %s)", colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });
        sliderGreen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setGreen(source.getValue());
                    colorFormat.updateByRgb();
                    updateHsv();
                    updateCmy();
                    updateLuv();
                    labelRgb.setText(String.format("RGB (%s, %s, %s)", colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });

        sliderHue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setHue(source.getValue());
                    colorFormat.updateByHsv();
                    updateRgb();
                    updateCmy();
                    updateLuv();
                    labelHsv.setText(String.format("HSV (%s, %s, %s)", colorFormat.getHue(), colorFormat.getSaturation(), colorFormat.getValue()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                    if (source.getValue() == 360) {
                        source.setValue(0);
                    }
                }
            }
        });
        sliderSaturation.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setSaturation(source.getValue());
                    colorFormat.updateByHsv();
                    updateRgb();
                    updateCmy();
                    updateLuv();
                    labelHsv.setText(String.format("HSV (%s, %s, %s)", colorFormat.getHue(), colorFormat.getSaturation(), colorFormat.getValue()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });
        sliderValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setValue(source.getValue());
                    colorFormat.updateByHsv();
                    updateRgb();
                    updateCmy();
                    updateLuv();
                    labelHsv.setText(String.format("HSV (%s, %s, %s)", colorFormat.getHue(), colorFormat.getSaturation(), colorFormat.getValue()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });

        sliderCyan.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setCyan(source.getValue());
                    colorFormat.updateByCmy();
                    updateRgb();
                    updateHsv();
                    updateLuv();
                    labelCmy.setText(String.format("CMY (%s, %s, %s)", colorFormat.getCyan(), colorFormat.getMagenta(), colorFormat.getYellow()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });
        sliderMagenta.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setMagenta(source.getValue());
                    colorFormat.updateByCmy();
                    updateRgb();
                    updateHsv();
                    updateLuv();
                    labelCmy.setText(String.format("CMY (%s, %s, %s)", colorFormat.getCyan(), colorFormat.getMagenta(), colorFormat.getYellow()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });
        sliderYellow.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setYellow(source.getValue());
                    colorFormat.updateByCmy();
                    updateRgb();
                    updateHsv();
                    updateLuv();
                    labelCmy.setText(String.format("CMY (%s, %s, %s)", colorFormat.getCyan(), colorFormat.getMagenta(), colorFormat.getYellow()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });

        sliderL.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setL(source.getValue());
                    colorFormat.updateByLuv();
                    updateRgb();
                    updateHsv();
                    updateCmy();
                    labelLuv.setText(String.format("L*u*v (%s, %s, %s)", colorFormat.getL(), colorFormat.getU(), colorFormat.getV()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });
        sliderU.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setU(source.getValue());
                    colorFormat.updateByLuv();
                    updateRgb();
                    updateHsv();
                    updateCmy();
                    labelLuv.setText(String.format("L*u*v (%s, %s, %s)", colorFormat.getL(), colorFormat.getU(), colorFormat.getV()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });
        sliderV.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isUpdating) {
                    JSlider source = (JSlider) e.getSource();
                    colorFormat.setV(source.getValue());
                    colorFormat.updateByLuv();
                    updateRgb();
                    updateHsv();
                    updateCmy();
                    labelLuv.setText(String.format("L*u*v (%s, %s, %s)", colorFormat.getL(), colorFormat.getU(), colorFormat.getV()));
                    panelColor.setBackground(new Color(colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
                }
            }
        });
    }

    private void updateRgb() {
        isUpdating = true;
        sliderRed.setValue(colorFormat.getRed());
        sliderGreen.setValue(colorFormat.getGreen());
        sliderBlue.setValue(colorFormat.getBlue());
        labelRgb.setText(String.format("RGB (%s, %s, %s)", colorFormat.getRed(), colorFormat.getGreen(), colorFormat.getBlue()));
        isUpdating = false;
    }

    private void updateHsv() {
        isUpdating = true;
        sliderHue.setValue(colorFormat.getHue());
        sliderSaturation.setValue(colorFormat.getSaturation());
        sliderValue.setValue(colorFormat.getValue());
        labelHsv.setText(String.format("HSV (%s, %s, %s)", colorFormat.getHue(), colorFormat.getSaturation(), colorFormat.getValue()));
        isUpdating = false;
    }

    private void updateCmy() {
        isUpdating = true;
        sliderCyan.setValue(colorFormat.getCyan());
        sliderMagenta.setValue(colorFormat.getMagenta());
        sliderYellow.setValue(colorFormat.getYellow());
        labelCmy.setText(String.format("CMY (%s, %s, %s)", colorFormat.getCyan(), colorFormat.getMagenta(), colorFormat.getYellow()));
        isUpdating = false;
    }

    private void updateLuv() {
        isUpdating = true;
        sliderL.setValue(colorFormat.getL());
        sliderU.setValue(colorFormat.getU());
        sliderV.setValue(colorFormat.getV());
        labelLuv.setText(String.format("L*u*v (%s, %s, %s)", colorFormat.getL(), colorFormat.getU(), colorFormat.getV()));
        isUpdating = false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ColorPicker");
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setContentPane(new ColorPicker().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
