package ua.epam;

import ua.epam.shapes.Line;
import ua.epam.shapes.Point;

public class Validator {
    public static Point[] validatePolygonSize(Point[] points) {
        if (points.length < 4)
            throw new IllegalArgumentException("Provide at least 4 points");
        return points;
    }

    public static int validateColor(int color) {
        if (color < 0)
            throw new IllegalArgumentException("Color must be >= 0");
        return color;
    }

    public static boolean isTriangleValid(Point a, Point b, Point c) {

        Line line1 = new Line(a, b);
        Line line2 = new Line(b, c);
        Line line3 = new Line(a, c);

        if (line1.getLength() + line2.getLength() > line3.getLength()
                && line1.getLength() + line3.getLength() > line2.getLength()
                && line2.getLength() + line3.getLength() > line1.getLength()) {
            return true;
        }
        return false;
    }
}
