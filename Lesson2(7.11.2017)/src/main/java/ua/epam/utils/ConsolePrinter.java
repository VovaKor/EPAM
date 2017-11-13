package ua.epam.utils;

import java.util.Arrays;

public class ConsolePrinter {
    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i] + " ");
        }
        System.out.println();
    }
    public static void printArray(float[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i] + " ");
        }
        System.out.println();
    }

    public static void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf(" "+array[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void print2DArray(float[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf(" "+array[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void printArray(Object[] array) {
        System.out.println(Arrays.toString(array));
    }
}
