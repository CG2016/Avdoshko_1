package vectorimage;

/**
 * Created by Evgeny on 15.05.2016.
 */
public class Rectangle {
    public Point lowerLeft;
    public Point upperRight;

    public Rectangle(Point lowerLeft, Point upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public static Rectangle create(String inputLine) {
        String[] values = inputLine.split(" ");
        return new Rectangle(
                new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1])),
                new Point(Integer.parseInt(values[2]), Integer.parseInt(values[3]))
        );
    }

    public double[][] toArray() {
        double[][] points = new double[2][5];

        points[0][0] = lowerLeft.x;
        points[1][0] = lowerLeft.y;

        points[0][1] = lowerLeft.x;
        points[1][1] = upperRight.y;

        points[0][2] = upperRight.x;
        points[1][2] = upperRight.y;

        points[0][3] = upperRight.x;
        points[1][3] = lowerLeft.y;

        points[0][4] = lowerLeft.x;
        points[1][4] = lowerLeft.y;

        return points;
    }

    @Override
    public String toString() {
        return String.format("Rectangle (%s, %s) - (%s, %s)", lowerLeft.x, lowerLeft.y, upperRight.x, upperRight.y);
    }
}
