package utils;

public class ArrayGenerator {
    public static int[] getOneDimensionalArray(int arraySize){
        int[] array = new int[arraySize];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*199)-100;
        }
        return array;
    }
}
