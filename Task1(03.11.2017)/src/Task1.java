import utils.ConsolePrinter;

/**
 * Упорядочить одномерный масив вначале отрицательные по возрастанию затем положительные по убыванию.
 * Пример вывода: -84  -84  -72  -64  -64  -42  -29  -21  -17  84  82  82  55  52  49  41  40  13  12  7
 * @author vova
 */
public class Task1 {

    public static void main(String[] args) {
        //Generating random test array
        int arraySize = 20;
        int[] array = new int[arraySize];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*199)-100;
        }
        //Sorting
        sortArray(array);
        //Printing to console
        ConsolePrinter.printArray(array);
    }
    private static void sortArray(int[] array) {
        for (int i=0; i<array.length;i++)
            for (int j = 0; j < array.length - 1 - i; j++) {
            //checking negative integers
                if((array[j]>array[j+1] && array[j+1]<0)
                        //checking positive integers
                        || (array[j]<array[j+1] && array[j]>0)){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
    }
}