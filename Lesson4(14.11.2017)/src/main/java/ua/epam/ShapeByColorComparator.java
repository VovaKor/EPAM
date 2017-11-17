package ua.epam;

import ua.epam.shapes.Shape;

import java.util.Comparator;

import static ua.epam.Constants.COLOR;

public class ShapeByColorComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        if (o1.containsProperty(COLOR) && !o2.containsProperty(COLOR))
            return 1;
        else if (!o1.containsProperty(COLOR) && o2.containsProperty(COLOR))
            return -1;
        else
            return 0;
    }
}
