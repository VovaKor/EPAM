package com.korobko.factories;

import com.korobko.shapes.*;

public class ShapeFactory extends AbstractFactory {
    public static Shape createShape() {
        int key = (int) (Math.random() * 4);
        switch (key) {
            case 0:
                return new Point((int) (Math.random() * 200 - 100), (int) (Math.random() * 200 - 100));
            case 1:
                return new Line((int) (Math.random() * 200 - 100),
                        (int) (Math.random() * 200 - 100),
                        (int) (Math.random() * 200 - 100),
                        (int) (Math.random() * 200 - 100));
            case 2:
                return new Triangle(new Point((int) (Math.random() * 200 - 100), (int) (Math.random() * 200 - 100)),
                        new Point((int) (Math.random() * 200 - 100), (int) (Math.random() * 200 - 100)),
                        new Point((int) (Math.random() * 200 - 100), (int) (Math.random() * 200 - 100)));
            case 3:
                Point[] points = new Point[key + 4];
                for (int i = 0; i < points.length; i++) {
                    int x = (int) (Math.random() * 200 - 100);
                    int y = (int) (Math.random() * 200 - 100);
                    points[i] = new Point(x, y);
                }
                return new Polygon(points);
            default:
                return null;
        }
    }
}
