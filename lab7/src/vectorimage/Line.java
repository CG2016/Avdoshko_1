package vectorimage;


/**
 * Created by Evgeny on 15.05.2016.
 */
public class Line {
    public Point src;
    public Point dest;

    public Line(Point src, Point dest) {
        this.src = src;
        this.dest = dest;
    }

    public static Line create(String inputLine) {
        String[] values = inputLine.split(" ");
        return new Line(
                new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1])),
                new Point(Integer.parseInt(values[2]), Integer.parseInt(values[3]))
        );
    }

    public double length() {
        return Math.sqrt((dest.x - src.x)*(dest.x - src.x) + (dest.y - src.y)*(dest.y - src.y));
    }

    public double[][] toArray() {
        double[][] points = new double[2][2];
        points[0][0] = src.x;
        points[1][0] = src.y;
        points[0][1] = dest.x;
        points[1][1] = dest.y;
        return points;
    }

    @Override
    public String toString() {
        return String.format("Line (%s, %s) - (%s, %s)", src.x, src.y, dest.x, dest.y);
    }
}
