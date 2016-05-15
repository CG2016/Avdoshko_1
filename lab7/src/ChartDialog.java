import javafx.scene.chart.ChartBuilder;
import org.apache.commons.io.FilenameUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import vectorimage.ShapeVectorImage;
import vectorimage.VectorImage;
import vectorimage.VectorImageVisualizer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Evgeny on 01.05.2016.
 */
public class ChartDialog extends JDialog {

    private DefaultXYDataset dataset = new DefaultXYDataset();
    private JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset,
            PlotOrientation.VERTICAL, true, true, false
    );
    private XYPlot plot = chart.getXYPlot();
    private ChartPanel chartPanel = new ChartPanel(chart);

    private JPanel panel = new JPanel();
    private VectorImageVisualizer vectorImageVisualizer = new VectorImageVisualizer();

    public ChartDialog(JFrame owner, boolean modal, VectorImage vectorImage, double executingTime, String title) {
        this(owner,modal, executingTime, title);
        vectorImageVisualizer.visualize(vectorImage, dataset, plot);
    }

    public ChartDialog(JFrame owner, boolean modal, ShapeVectorImage shapeVectorImage, double executingTime, String title) {
        this(owner,modal, executingTime, title);
        vectorImageVisualizer.visualize(shapeVectorImage, dataset, plot);
    }

    public ChartDialog(JFrame owner, boolean modal, double executingTime, String title) {
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(-0.5, 100);
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(-0.5, 100);
        setSize(1000, 1000);
        panel.setSize(this.getWidth(), 0);
        panel.setLayout(new GridLayout(0, 1));
        panel.add(chartPanel);
        this.setLayout(new GridLayout(0, 1));
        this.add(panel);
        setTitle(title);
        JOptionPane.showMessageDialog(null, executingTime + "s.");
    }
}
