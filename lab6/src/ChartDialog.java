import org.apache.commons.io.FilenameUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

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

    ChartBuilder chartBuilder;
    JPanel panel = new JPanel();
    DefaultXYDataset dataset = new DefaultXYDataset();
    JFreeChart chart = ChartFactory.createXYLineChart("Image Chart", "", "", dataset,
            PlotOrientation.VERTICAL, true, true, false
    );
    ChartPanel chartPanel = new ChartPanel(chart);

    private JMenuBar initFileMenu() {
        JMenuBar menuBar = new JMenuBar();
        JButton menuEQ = new JButton("EQ");
        menuBar.add(menuEQ);

        menuEQ.addActionListener(new StubActionListener(this) {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage updatedImage = chartBuilder.equalize();
                ImageDialog dialog = new ImageDialog(getParentFrame(), false, updatedImage);
                dialog.setSize(updatedImage.getWidth() + 40, updatedImage.getHeight() + 70);
                dialog.setVisible(true);

                chartBuilder.setImage(updatedImage);
                chartBuilder.build();
                dataset.removeSeries(0);
                dataset.addSeries("", chartBuilder.channel());
            }
        });

        return menuBar;
    }

    public ChartDialog(JFrame owner, boolean modal, BufferedImage image) {
        super(owner, modal);
        chartBuilder = new ChartBuilder(image);
        panel.setSize(this.getWidth(), 0);
        panel.setLayout(new GridLayout(0, 1));
        panel.add(chartPanel);
        this.setLayout(new GridLayout(0, 1));
        this.add(panel);
        setJMenuBar(initFileMenu());
        chartBuilder.build();
        double[][] values = chartBuilder.channel();
        dataset.addSeries("", values);
    }
}
