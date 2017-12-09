package ua.epam.shapes;

import ua.epam.Validator;

import java.util.Arrays;

import static ua.epam.Constants.COLOR;

public class Polygon extends Shape {

    private Point[] points;

    public Polygon(Point[] points) {
        this.points = Validator.validatePolygonSize(points);
    }
    public Polygon(Point[] points, int color) {
        this(points);
        setProperty(COLOR, Validator.validateColor(color));
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "points=" + Arrays.toString(points) +
                '}';
    }
}
