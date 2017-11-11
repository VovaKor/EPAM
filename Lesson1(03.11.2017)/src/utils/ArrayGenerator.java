package utils;

public class ArrayGenerator {
    public static int[] getOneDimensionalArray(int arraySize){
        int[] array = new int[arraySize];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*199)-100;
        }
        return array;
    }
    public static int[][] get2DArray(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int)(Math.random()*199)-100;
            }
        }
        return matrix;
    }
}
