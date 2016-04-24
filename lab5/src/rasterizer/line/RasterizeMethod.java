package rasterizer.line;

/**
 * Created by Evgeny on 24.04.2016.
 */
public enum RasterizeMethod {
    BrezenhamLineRasterizer(new BrezenhamLineRasterizer()),
    DdaLineRasterizer(new DdaLineRasterizer()),
    StepByStepLineRasterizer(new StepByStepLineRasterizer());

    LineRasterizer lineRasterizer;

    RasterizeMethod(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    public LineRasterizer getLineRasterizer() {
        return lineRasterizer;
    }

}
