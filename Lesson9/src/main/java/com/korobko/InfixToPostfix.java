package com.korobko;

/**
 * Converting an infix entry into a postfix
 *
 * @author Vova Korobko
 */
public class InfixToPostfix {
    private StackX theStackX;
    private String input;
    private String output = "";

    public InfixToPostfix(String in) {
        input = in;
        int stackSize = input.length();
        theStackX = new StackX(stackSize);
    }

    public String doTransform() {

        for (int j = 0; j < input.length(); j++) {
            int k = j + 1;
            
            // shifting index to read cos,sin or complex digit
            while (k < input.length()) {
                if (Character.isAlphabetic(input.charAt(j)) && Character.isAlphabetic(input.charAt(k))) k++;
                else if (Character.isDigit(input.charAt(j)) && Character.isDigit(input.charAt(k))) k++;
                else break;
            }
            String ch = input.substring(j, k);
            j = k - 1;
            theStackX.displayStack("For " + ch + " ");
            switch (ch) {
                case "+":
                case "-":
                    gotOperator(ch, 1);
                    break;
                case "*":
                case "/":
                    gotOperator(ch, 2);
                    break;
                case "(":
                case "cos":
                case "sin":
                    theStackX.push(ch + "");
                    break;
                case ")":
                    gotBracket(ch);
                    break;
                default:
                    output = output + " " + ch;
                    if (!Character.isDigit(ch.charAt(0)) || ch.charAt(0) == '.' || ch.charAt(0) == ',') {
                        output = "";
                        theStackX = new StackX(0);
                        j = input.length();
                    }
                    break;
            }
        }
        while (!theStackX.isEmpty()) {
            output = output + " " + theStackX.pop();
        }

        return output;
    }


    public void gotOperator(String operatorThis, int priority1) {                                // got operator from input
        while (!theStackX.isEmpty()) {
            String operatorTop = theStackX.pop();
            if (operatorTop.equals("("))
            {
                theStackX.push(operatorTop);
                break;
            } else
            {
                int priority2;

                if (operatorTop.equals("+") || operatorTop.equals("-"))
                    priority2 = 1;
                else
                    priority2 = 2;
                if (priority2 < priority1)
                {
                    theStackX.push(operatorTop);
                    break;
                } else
                    output = output + " " + operatorTop;
            }
        }
        theStackX.push(operatorThis + "");
    }

    public void gotBracket(String ch) {
        while (!theStackX.isEmpty()) {
            String chx = theStackX.pop();
            if (chx.equals("("))
                break;
            else
                output = output + " " + chx;
        }
    }
}
