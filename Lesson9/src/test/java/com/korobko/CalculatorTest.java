package com.korobko;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple Calculator.
 */
public class CalculatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void computeExpression_allOperations_correctResult() {
        String input = "sin(cos(3*(4+5)-6/(1+2)))";
        double result = Calculator.computeExpression(input);
        Assert.assertEquals(0.836, result, 0.001);
    }

    @Test
    public void computeExpression_spaceInExpression_computeZero() {
        String input = "sin(cos(3*(4+ 5)-6/(1+2)))";
        double result = Calculator.computeExpression(input);
        Assert.assertEquals(0.0, result, 0.001);
    }
}
