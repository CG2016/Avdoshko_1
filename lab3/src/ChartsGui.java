import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Evgeny on 21.03.2016.
 */
public class ChartsGui extends JFrame {

    DefaultXYDataset datasetRed = new DefaultXYDataset();
    DefaultXYDataset datasetGreen = new DefaultXYDataset();
    DefaultXYDataset datasetBlue = new DefaultXYDataset();

    JFreeChart chartRed = ChartFactory.createXYLineChart("Channel Red", "value", "pixels", datasetRed,
            PlotOrientation.VERTICAL, true, true, false
    );
    JFreeChart chartGreen = ChartFactory.createXYLineChart("Channel Green", "value", "pixels", datasetGreen,
            PlotOrientation.VERTICAL, true, true, false
    );
    JFreeChart chartBlue = ChartFactory.createXYLineChart("Channel Blue", "value", "pixels", datasetBlue,
            PlotOrientation.VERTICAL, true, true, false
    );

    ChartPanel chartPanelRed = new ChartPanel(chartRed);
    ChartPanel chartPanelGreen = new ChartPanel(chartGreen);;
    ChartPanel chartPanelBlue = new ChartPanel(chartBlue);;

    JPanel panel = new JPanel();

    private JMenuBar initFileMenu(JPanel panel) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuCharts = new JMenu("Charts");
        JMenuItem menuItemOpenImage = new JMenuItem("Open image");
        JMenuItem menuItemClearCharts = new JMenuItem("Clear");
        menuFile.add(menuItemOpenImage);
        menuCharts.add(menuItemClearCharts);
        menuBar.add(menuFile);
        menuBar.add(menuCharts);

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "gif", "png", "bmp");
        fileChooser.setFileFilter(filter);

        menuItemOpenImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(panel);
                File choosenFile = fileChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(choosenFile);
                    ColorChartBuilder colorChartBuilder = new ColorChartBuilder(image);
                    datasetRed.addSeries(choosenFile.getName(), colorChartBuilder.channelRed());
                    datasetGreen.addSeries(choosenFile.getName(), colorChartBuilder.channelGreen());
                    datasetBlue.addSeries(choosenFile.getName(), colorChartBuilder.channelBlue());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        menuItemClearCharts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (datasetRed.getSeriesCount() != 0 ) {
                    datasetRed.removeSeries(datasetRed.getSeriesKey(0));
                    datasetGreen.removeSeries(datasetGreen.getSeriesKey(0));
                    datasetBlue.removeSeries(datasetBlue.getSeriesKey(0));
                }
            }
        });
        return menuBar;
    }

    public ChartsGui() {
        super("RGB Channels Chart");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(this.getWidth(), 0);
        panel.setLayout(new GridLayout(0, 1));
        panel.add(chartPanelRed);
        panel.add(chartPanelGreen);
        panel.add(chartPanelBlue);
        this.setLayout(new GridLayout(0, 1));
        this.add(panel);
        this.setJMenuBar(initFileMenu(panel));
    }

    public static void main(String[] args) {
        ChartsGui app = new ChartsGui();
        app.setVisible(true);
    }
}
