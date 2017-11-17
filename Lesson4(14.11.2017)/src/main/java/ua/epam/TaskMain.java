package ua.epam;

import ua.epam.factories.ColoredShapeFactory;
import ua.epam.factories.ShapeFactory;
import ua.epam.shapes.Shape;
import java.util.ArrayList;
import java.util.List;

import static ua.epam.Constants.COLOR;

/**
 * 1. создать последовательность из фигур  используя фабрики цветных и нецветных фигур
 * 2. получить масив цветных и не цветных и сколько каждых фигур в нем присутсвует
 * 3. групируем в масивах фигуры
 * 4. тестируем методы фигур
 *
 * @author Vova Korobko
 */

public class TaskMain {
    public static void main(String[] args) {

    }

    /*
    * Generating colored and simple shapes
    */
    public static List<Shape> generateShapes() {
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int key = (int) (Math.random() * 2);
            switch (key) {
                case 0:
                    shapes.add(ColoredShapeFactory.createShape());
                    break;
                case 1:
                    shapes.add(ShapeFactory.createShape());
                    break;
                default:
                    break;
            }
        }
        return shapes;
    }

    /*
    * Counts colored shapes
    */
    public static int countColoredShapes(List<Shape> shapes) {
        return (int) shapes.stream().filter(shape -> shape.containsProperty(COLOR)).count();
    }

    /*
    * Counts simple shapes
    */
    public static int countSimpleShapes(List<Shape> shapes) {
        return (int) shapes.stream().filter(shape -> !shape.containsProperty(COLOR)).count();
    }

    /*
    * Sorts shapes: first simple -> colored, then lexicographically
    */
    public static List<Shape> sortShapes(List<Shape> shapes) {
        shapes.sort(new ShapeByColorComparator()
                .thenComparing(new ShapeLexicographicComparator()));
        return shapes;
    }
}
