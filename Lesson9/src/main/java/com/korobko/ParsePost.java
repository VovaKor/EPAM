package com.korobko;

/**
 * @author Vova Korobko
 */
public class ParsePost {
    private StackX theStackX;
    private String input;

    public ParsePost(String s) {
        input = s;
    }

    public double doParse() {
        theStackX = new StackX(20);
        String ch;
        int j;
        double num1, num2, intermediateResult;

        for (j = 0; j < input.length(); j++) {
            int k = j + 1;
            // shifting index to read cos or sin
            while (k < input.length()) {
                if (input.charAt(k) != ' ') k++;
                else break;
            }
            ch = input.substring(j, k);
            j = k;
            theStackX.displayStack("" + ch + " ");

            // it's a digit
            if (ch.charAt(0) >= '0' && ch.charAt(0) <= '9') {
                theStackX.push((ch));

            // it's an operand
            } else {
                num2 = Double.parseDouble(theStackX.pop());
                switch (ch) {
                    case "+":
                        String pop = theStackX.pop();
                        if (pop == null) pop = "0";
                        num1 = Double.parseDouble(pop);
                        intermediateResult = num1 + num2;
                        break;
                    case "-":
                        pop = theStackX.pop();
                        if (pop == null) pop = "0";
                        num1 = Double.parseDouble(pop);
                        intermediateResult = num1 - num2;
                        break;
                    case "*":
                        pop = theStackX.pop();
                        if (pop == null) pop = "1";
                        num1 = Double.parseDouble(pop);
                        intermediateResult = num1 * num2;
                        break;
                    case "/":
                        pop = theStackX.pop();
                        if (pop == null) pop = "1";
                        num1 = Double.parseDouble(pop);
                        intermediateResult = num1 / num2;
                        break;
                    case "cos":
                        intermediateResult = Math.cos(num2);
                        break;
                    case "sin":
                        intermediateResult = Math.sin(num2);
                        break;
                    default:
                        intermediateResult = 0;
                }
                theStackX.push(intermediateResult + "");
            }
        }
        if (!theStackX.isEmpty()) {
            intermediateResult = Double.parseDouble(theStackX.pop());
        } else {
            intermediateResult = 0;
        }

        return intermediateResult;
    }
}
