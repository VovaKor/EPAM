package ua.epam.factories;

import ua.epam.shapes.*;

public class ColoredShapeFactory extends AbstractFactory {
    public static Shape createShape() {
        int key = (int)(Math.random() * 4);
        switch(key){
            case 0:
                Point point = new Point((int)(Math.random() * 200 - 100),
                        (int)(Math.random() * 200 - 100),
                        (int)(Math.random() * 255));
                return point;
            case 1:
                Line line = new Line((int)(Math.random() * 200 - 100),
                        (int)(Math.random() * 200 - 100),
                        (int)(Math.random() * 200 - 100),
                        (int)(Math.random() * 200 - 100),
                        (int)(Math.random() * 255));
                return line;
            case 2:
                return new Triangle(new Point((int)(Math.random() * 200 - 100), (int)(Math.random() * 200 - 100)),
                        new Point((int)(Math.random() * 200 - 100), (int)(Math.random() * 200 - 100)),
                        new Point((int)(Math.random() * 200 - 100), (int)(Math.random() * 200 - 100)),
                        (int)(Math.random() * 255));
            case 3:
                Point[] points = new Point[key + 4];
                for (int i = 0; i < points.length; i++) {
                    int x = (int)(Math.random() * 200 - 100);
                    int y = (int)(Math.random() * 200 - 100);
                    points[i] = new Point(x, y);
                }
                return new Polygon(points, (int)(Math.random() * 255));
            default:
                return null;
        }
    }
}
