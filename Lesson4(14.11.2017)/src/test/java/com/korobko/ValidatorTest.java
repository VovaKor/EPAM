package com.korobko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.korobko.shapes.Point;

import static org.junit.jupiter.api.Assertions.*;
import static com.korobko.Constants.MINUS_ONE;
import static com.korobko.Constants.ONE;
import static com.korobko.Constants.ZERO;

class ValidatorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validatePolygonSize() {
        // Negative test
        Point[] invalidPoints = new Point[3];
        assertThrows(IllegalArgumentException.class, () -> {
            Validator.validatePolygonSize(invalidPoints);
        });

        // Positive test
        Point[] validPoints = new Point[4];
        for (int i = 0; i < validPoints.length; i++) {
            int x = (int) (Math.random() * 200 - 100);
            int y = (int) (Math.random() * 200 - 100);
            validPoints[i] = new Point(x, y);
        }
        assertArrayEquals(validPoints, Validator.validatePolygonSize(validPoints));
    }

    @Test
    void validateColor() {
        // Negative test for -1

        assertThrows(IllegalArgumentException.class, () -> {
            Validator.validateColor(MINUS_ONE);
        });

        // Positive test for 0

        assertEquals(ZERO, Validator.validateColor(ZERO));

        // Positive test

        assertEquals(ONE, Validator.validateColor(ONE));
    }

    @Test
    void isTriangleValid() {

        // Negative test
        Point a = new Point(ONE,ONE);
        Point b = new Point(ONE,ONE);
        Point c = new Point(ONE,ONE);

        assertFalse(Validator.isTriangleValid(a, b, c));

        // Positive test
        Point a1 = new Point(ONE,ONE);
        Point b1 = new Point(ONE,MINUS_ONE);
        Point c1 = new Point(ZERO,ZERO);

        assertTrue(Validator.isTriangleValid(a1, b1, c1));
    }

}