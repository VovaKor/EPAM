import main.java.ua.epam.utils.ConsolePrinter;

/**
 * Дана целочисленная прямоугольная матрица. Упорядочить столбцы по убыванию среднего значения.
 * Пример вывода:       30      1       2
 *                      0       5       8
 *                      4       8       7
 *                      0       11      0
 *
 * @author Vova Korobko
 */
public class Task3 {
    public static void main(String[] args) {
        //Generating rectangular matrix
        int[][] matrix = ArrayGenerator.get2DArray(4,3);

        //Getting averages from given matrix
        float[][] averages = getAverages(matrix);

        //Sorting averages in descending order
        sortAverages(averages);

        //Taking indexes from averages and sorting matrix
        int[][] sortedArray = sortMatrix(matrix,averages);

        //Printing result into console
        ConsolePrinter.print2DArray(sortedArray);
    }
    private static float[][] getAverages(int[][] matrix) {
        float[][] averages = new float[matrix[0].length][2];

        for (int i = 0; i < matrix[0].length; i++) {
            float sum = 0f;

            for (int j = 0; j < matrix.length; j++) {
                sum += (float) matrix[j][i];
            }
            //saving column average
            averages[i][0] = sum / (float) matrix.length;
            //remembering column position
            averages[i][1] = (float) i;
        }
        return averages;
    }


    private static int[][] sortMatrix(int[][] matrix, float[][] indexes) {
        int[][] sortedMatrix = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < indexes.length; i++) {
            int index = Math.round(indexes[i][1]);
            for (int j = 0; j < matrix.length; j++) {
                sortedMatrix[j][i] = matrix[j][index];
            }
        }
        return sortedMatrix;
    }


    private static void sortAverages(float[][] averages) {

        for (int i = 0; i <averages.length ; i++) {
            for (int j = 0; j < averages.length - 1 - i; j++) {
                if(averages[j][0]<averages[j+1][0]){
                    float[] tmp = averages[j];
                    averages[j] = averages[j+1];
                    averages[j+1] = tmp;
                }
            }
        }
    }
}

