package com.korobko;

/**
 * @author Vova Korobko
 */
public class StackX {
    private int maxSize;
    private String[] stackArray;
    private int top;

    public StackX(int size) {
        maxSize = size;
        stackArray = new String[maxSize];
        top = -1;
    }

    // putting element on top of the stack
    public void push(String j) {
        if (top < maxSize)
            stackArray[++top] = j;
    }

    // removing element from top of the stack
    public String pop() {
        if (top >= 0)
            return stackArray[top--];
        else
            return null;
    }

    // reading element from top of the stack
    public String peek() {
        return stackArray[top];
    }

    // true if stack is empty
    public boolean isEmpty() {
        return (top == -1);
    }

    // true if stack is full
    public boolean isFull() {
        return (top == maxSize - 1);
    }

    // actual stack size
    public int size() {
        return top + 1;
    }

    // reading element at index n
    public String peekN(int n) {
        return stackArray[n];
    }

    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("StackX (bottom-->top): ");
        for (int j = 0; j < size(); j++) {
            System.out.print(peekN(j));
            System.out.print(' ');
        }
        System.out.println("");
    }
}
