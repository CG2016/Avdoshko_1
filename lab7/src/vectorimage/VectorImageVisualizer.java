package vectorimage;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;

import java.awt.*;

/**
 * Created by Evgeny on 15.05.2016.
 */
public class VectorImageVisualizer {

    public void visualize(VectorImage vectorImage, DefaultXYDataset dataset, XYPlot plot) {
        clearDataset(dataset);

        for (Line line : vectorImage.lines) {
            dataset.addSeries(line.toString(), line.toArray());
        }
        dataset.addSeries(vectorImage.clippingWindow.toString(), vectorImage.clippingWindow.toArray());

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        for (int i=0; i<dataset.getSeriesCount(); i++) {
            renderer.setSeriesPaint(i, new java.awt.Color(303));
        }
    }

    public void visualize(ShapeVectorImage shapeVectorImage, DefaultXYDataset dataset, XYPlot plot) {
        clearDataset(dataset);

        for (Line line : shapeVectorImage.shape.lines) {
            dataset.addSeries(line.toString(), line.toArray());
        }
        dataset.addSeries(shapeVectorImage.clippingWindow.toString(), shapeVectorImage.clippingWindow.toArray());

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        for (int i=0; i<dataset.getSeriesCount(); i++) {
            renderer.setSeriesPaint(i, new java.awt.Color(303));
        }
    }

    private void clearDataset(DefaultXYDataset dataset) {
        while (dataset.getSeriesCount() != 0 ) {
            dataset.removeSeries(dataset.getSeriesKey(0));
        }
    }
}
