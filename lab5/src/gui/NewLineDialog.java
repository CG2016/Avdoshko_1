package gui;

import rasterizer.line.LineRasterizer;
import rasterizer.line.RasterizeMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.text.NumberFormat;

/**
 * Created by Evgeny on 24.04.2016.
 */
public class NewLineDialog extends JDialog {

    private Point start;
    private Point end;
    private LineRasterizer lineRasterizer;

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public LineRasterizer getLineRasterizer() {
        return lineRasterizer;
    }

    public NewLineDialog(Frame owner, boolean modal) {
        super(owner, modal);
        JPanel panel = new JPanel();
        JPanel panelPointStart = new JPanel();
        JPanel panelPointEnd = new JPanel();
        panel.setSize(this.getWidth(), this.getHeight());
        panel.setLayout(new GridLayout(0, 1));
        panelPointStart.setLayout(new GridLayout(0, 3));
        panelPointEnd.setLayout(new GridLayout(0, 3));

        JTextField startPointX = new JTextField();
        JTextField startPointY = new JTextField();
        JTextField endPointX = new JTextField();
        JTextField endPointY = new JTextField();
        JButton submit = new JButton("Submit");
        RasterizeMethod[] methods =  RasterizeMethod.values();
        JComboBox methodsComboBox = new JComboBox(methods);
        submit.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    start = new Point(Integer.parseInt(startPointX.getText()), Integer.parseInt(startPointY.getText()));
                    end = new Point(Integer.parseInt(endPointX.getText()), Integer.parseInt(endPointY.getText()));
                    RasterizeMethod method = (RasterizeMethod) methodsComboBox.getSelectedItem();
                    lineRasterizer = method.getLineRasterizer();
                    dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
                } catch(Exception ex) {

                }
            }
        });

        panelPointStart.add(new Label("Start point:"));
        panelPointStart.add(startPointX);
        panelPointStart.add(startPointY);
        panelPointEnd.add(new Label("End point:"));
        panelPointEnd.add(endPointX);
        panelPointEnd.add(endPointY);

        panel.add(panelPointStart);
        panel.add(panelPointEnd);
        panel.add(methodsComboBox);
        panel.add(submit);

        add(panel);
    }

}
