package gui;

import rasterizer.line.LineRasterizer;
import rasterizer.line.RasterizeMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Created by Evgeny on 24.04.2016.
 */
public class NewCircleDialog extends JDialog {
    private Point center;
    private int radius;

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public NewCircleDialog(Frame owner, boolean modal) {
        super(owner, modal);
        JPanel panel = new JPanel();
        JPanel panelPointCenter = new JPanel();
        JPanel panelRadius = new JPanel();
        panel.setSize(this.getWidth(), this.getHeight());
        panel.setLayout(new GridLayout(0, 1));
        panelPointCenter.setLayout(new GridLayout(0, 3));
        panelRadius.setLayout(new GridLayout(0, 2));

        JTextField centerPointX = new JTextField();
        JTextField centerPointY = new JTextField();
        JTextField radiusField = new JTextField();
        JButton submit = new JButton("Submit");
        submit.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    center = new Point(Integer.parseInt(centerPointX.getText()), Integer.parseInt(centerPointY.getText()));
                    radius = Integer.parseInt(radiusField.getText());
                    dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
                } catch(Exception ex) {

                }
            }
        });

        panelPointCenter.add(new Label("Center point:"));
        panelPointCenter.add(centerPointX);
        panelPointCenter.add(centerPointY);
        panelRadius.add(new Label("Radius:"));
        panelRadius.add(radiusField);

        panel.add(panelPointCenter);
        panel.add(panelRadius);
        panel.add(submit);

        add(panel);
    }
}
