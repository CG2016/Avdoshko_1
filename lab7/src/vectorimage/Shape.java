package vectorimage;

import java.util.List;

/**
 * Created by Evgeny on 15.05.2016.
 */
public class Shape {
    public List<Line> lines;

    public double[][] toArray() {
        double[][] array = new double[2][2*lines.size()+1];
        for (int i=0; i<lines.size(); i++) {
            array[0][2*i] = lines.get(i).src.x;
            array[1][2*i] = lines.get(i).src.y;
            array[0][2*i+1] = lines.get(i).dest.x;
            array[1][2*i+1] = lines.get(i).dest.y;
        }
        array[0][2*lines.size()] = lines.get(0).src.x;
        array[1][2*lines.size()] = lines.get(0).src.y;
        return array;
    }
}
