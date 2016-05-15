import clipping.*;
import org.apache.commons.io.FilenameUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import vectorimage.ShapeVectorImage;
import vectorimage.StubMenuListener;
import vectorimage.VectorImage;
import vectorimage.VectorImageVisualizer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Evgeny on 15.05.2016.
 */
public class MainForm extends JFrame {
    private DefaultXYDataset dataset = new DefaultXYDataset();
    private JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset,
            PlotOrientation.VERTICAL, true, true, false
    );
    private XYPlot plot = chart.getXYPlot();
    private ChartPanel chartPanel = new ChartPanel(chart);

    private JPanel panel = new JPanel();
    private VectorImageVisualizer vectorImageVisualizer = new VectorImageVisualizer();

    private VectorImage vectorImage;
    private ShapeVectorImage shapeVectorImage;

    private JMenuBar initFileMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(0, 3));
        JButton menuInitialScale = new JButton("Back To Initial Scale");
        JButton menuOpenFile = new JButton("Open File");
        JButton menuClipMidpoint = new JButton("Clip with Midpoint Algorithm");
        JButton menuClipLiangBarsky = new JButton("Clip with Liang-Barsky Algorithm");
        JButton menuClipSutherlandHodgman = new JButton("Clip with Sutherland-Hodgman Algorithm");
        menuClipMidpoint.setEnabled(false);
        menuClipLiangBarsky.setEnabled(false);
        menuClipSutherlandHodgman.setEnabled(false);
        menuBar.add(menuInitialScale);
        menuBar.add(menuOpenFile);
        menuBar.add(menuClipMidpoint);
        menuBar.add(menuClipLiangBarsky);
        menuBar.add(menuClipSutherlandHodgman);

        menuClipMidpoint.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClippingAlgorithm clippingAlgorithm = new MidpointAlgorithm();
                VectorImage clippedVectorImage = clippingAlgorithm.clip(vectorImage);
                ChartDialog chartDialog = new ChartDialog(this.getParentFrame(), true, clippedVectorImage, clippingAlgorithm.getExecutingTime(), "Midpoint Algorithm");
                chartDialog.setVisible(true);
            }
        });

        menuClipLiangBarsky.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClippingAlgorithm clippingAlgorithm = new LiangBarskyAlgorithm();
                VectorImage clippedVectorImage = clippingAlgorithm.clip(vectorImage);
                ChartDialog chartDialog = new ChartDialog(this.getParentFrame(), true, clippedVectorImage, clippingAlgorithm.getExecutingTime(), "Lian-Barsky Algorithm");
                chartDialog.setVisible(true);
            }
        });

        menuClipSutherlandHodgman.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShapeClippingAlgorithm clippingAlgorithm = new SutherlandHodgmanAlgorithm();
                ShapeVectorImage clippedShapeVectorImage = clippingAlgorithm.clip(shapeVectorImage);
                ChartDialog chartDialog = new ChartDialog(this.getParentFrame(), true, clippedShapeVectorImage, clippingAlgorithm.getExecutingTime(), "Lian-Barsky Algorithm");
                chartDialog.setVisible(true);
            }
        });

        menuInitialScale.addActionListener(new StubMenuListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                XYPlot plot = chart.getXYPlot();
                NumberAxis domain = (NumberAxis) plot.getDomainAxis();
                domain.setRange(-0.5, 100);
                NumberAxis range = (NumberAxis) plot.getRangeAxis();
                range.setRange(-0.5, 100);
            }
        });

        menuOpenFile.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Primitives", "input", "shape");
                fileChooser.setFileFilter(filter);
                fileChooser.showOpenDialog(panel);
                File chosenFile = fileChooser.getSelectedFile();
                try {
                    String ext = FilenameUtils.getExtension(chosenFile.getAbsolutePath());
                    if (ext.equals("input")) {
                        vectorImage = VectorImage.create(new FileInputStream(chosenFile));
                        vectorImageVisualizer.visualize(vectorImage, dataset, plot);
                        menuClipMidpoint.setEnabled(true);
                        menuClipLiangBarsky.setEnabled(true);
                        menuClipSutherlandHodgman.setEnabled(false);
                    } else if (ext.equals("shape")) {
                        shapeVectorImage = ShapeVectorImage.create(new FileInputStream(chosenFile));
                        vectorImageVisualizer.visualize(shapeVectorImage, dataset, plot);
                        menuClipMidpoint.setEnabled(false);
                        menuClipLiangBarsky.setEnabled(false);
                        menuClipSutherlandHodgman.setEnabled(true);
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        return menuBar;
    }


    public MainForm() {
        super("");
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(-0.5, 100);
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(-0.5, 100);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(this.getWidth(), 0);
        panel.setLayout(new GridLayout(0, 1));
        panel.add(chartPanel);
        this.setLayout(new GridLayout(0, 1));
        this.add(panel);
        this.setJMenuBar(initFileMenu());
    }

    public static void main(String[] args) {
        MainForm app = new MainForm();
        app.setVisible(true);
    }
}
