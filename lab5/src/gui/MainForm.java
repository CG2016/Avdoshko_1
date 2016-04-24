package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.xy.DefaultXYDataset;
import rasterizer.circle.BrezenhamCircleRasterizer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Created by Evgeny on 24.04.2016.
 */
public class MainForm extends JFrame {
    DefaultXYDataset dataset = new DefaultXYDataset();
    JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset,
            PlotOrientation.VERTICAL, true, true, false
    );
    ChartPanel chartPanel = new ChartPanel(chart);

    JPanel panel = new JPanel();

    private JMenuBar initFileMenu() {
        JMenuBar menuBar = new JMenuBar();
        JButton menuInitialScale = new JButton("Back To Initial Scale");
        JButton menuNewLine = new JButton("New Line");
        JButton menuNewCircle = new JButton("New Circle");
        JButton menuClear = new JButton("Clear All");
        menuBar.add(menuInitialScale);
        menuBar.add(menuNewLine);
        menuBar.add(menuNewCircle);
        menuBar.add(menuClear);

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

        menuNewLine.addActionListener(new StubMenuListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewLineDialog dialog = new NewLineDialog(getParentFrame(), true);
                dialog.setSize(400, 200);
                dialog.setVisible(true);
                double[][] points = dialog.getLineRasterizer().rasterize(dialog.getStart(), dialog.getEnd());
                dataset.addSeries(dialog.getLineRasterizer().toString(), points);
            }
        });

        menuNewCircle.addActionListener(new StubMenuListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewCircleDialog dialog = new NewCircleDialog(getParentFrame(), true);
                dialog.setSize(400, 200);
                dialog.setVisible(true);
                double[][] points = new BrezenhamCircleRasterizer().rasterize(dialog.getCenter(), dialog.getRadius());
                dataset.addSeries("Brezenham Circle", points);
            }
        });

        menuClear.addActionListener(new StubMenuListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (dataset.getSeriesCount() != 0 ) {
                    dataset.removeSeries(dataset.getSeriesKey(0));
                }
            }
        });
        return menuBar;
    }

    public MainForm() {
        super("Rasterizer");
        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(new PixelRender(1));
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
