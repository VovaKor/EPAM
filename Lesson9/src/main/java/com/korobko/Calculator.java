package com.korobko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Стековый калькулятор
 * Реализация в 4 главе Роберт Лафоре - Структуры данных и алгоритмы в Java. 2-е издание
 * Добавить sin cos
 *
 * @author Vova Korobko
 */
public class Calculator {

    public static void main(String[] args) throws IOException {
        String input;
        double result;

        while (true) {
            System.out.print("Enter expression: ");
            input = getString();

            if (input.equals("")) break;

            result = computeExpression(input);
            System.out.println(input + " evaluates into " + result);
        }
    }

    public static double computeExpression(String expression) {
        InfixToPostfix converter = new InfixToPostfix(expression);
        String output = converter.doTransform().trim();
        ParsePost parser = new ParsePost(output);
        return parser.doParse();
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
}
