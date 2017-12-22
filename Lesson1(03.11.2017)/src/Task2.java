package src;

import src.utils.ConsolePrinter;

/**
 * Упорядочить одномерный масив сначала положительные потом отрицательные за О(n).
 * Пример вывода: 64  57  39  24  93  6  78  -88  -73  -80  -22  -19  -8  -56  -29  -34  -27  -4  -61  -26
 * @author Vova Korobko
 */
public class Task2 {
    public static void main(String[] args) {
        //Generating random test array
        int arraySize = 20;
        int[] array = ArrayGenerator.getOneDimensionalArray(arraySize);
        //Sorting
        sortArray(array);
        //Printing to console
        ConsolePrinter.printArray(array);
    }
    private static void sortArray(int[] array) {
        int start = 0;
        int end = array.length - 1;

        for (;start < end; start++) {            //O(n)
            if (array[start] < 0) {
                //checking array tail if negative
                while (array[end] < 0) {         //O(n)
                    end--;
                }
                //shifting
                if (start < end) {
                    int temp = array[start];
                    array[start] = array[end];
                    array[end] = temp;
                }
            }

        }
    }
}
