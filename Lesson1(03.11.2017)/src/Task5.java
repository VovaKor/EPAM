package src;

/**
 * Дана квадратная матрица A порядка M (M — нечетное число). Начи-
 * ная с элемента A1,1 и перемещаясь по часовой стрелке, вывести все ее эле-
 * менты по спирали: первая строка, последний столбец, последняя строка в
 * обратном  порядке,  первый  столбец  в  обратном  порядке,  оставшиеся  эле-
 * менты второй строки и т. д.; последним выводится центральный элемент
 * матрицы.
 * Пример ввода:    {{ 0,  1,  2,  3, 4},
 *                   {15, 16, 17, 18, 5},
 *                   {14, 23, 24, 19, 6},
 *                   {13, 22, 21, 20, 7},
 *                   {12, 11, 10,  9, 8}};
 *
 * Пример вывода:   0	1	2	3	4
 *                  5	6	7	8
 *                  9	10	11	12
 *                  13	14	15
 *                  16	17	18
 *                  19	20
 *                  21	22
 *                  23
 *                  24
 *
 * @author Vova Korobko
 */
public class Task5 {
    public static void main(String[] args) {
        int[][] matrix = {{ 0,  1,  2,  3, 4},
                          {15, 16, 17, 18, 5},
                          {14, 23, 24, 19, 6},
                          {13, 22, 21, 20, 7},
                          {12, 11, 10,  9, 8}};

        printRightSpiral(matrix);
    }

    private static void printRightSpiral(int[][] matrix) {
        int lastStep = Math.round((float) matrix.length/2);
        int squareSide = matrix.length - 1;

        for (int step = 0; step < lastStep; step++,squareSide--) {

            // Top row, from first to last column
            for (int j = step; j <= squareSide; j++) {
                System.out.print(matrix[step][j] + "\t");
            }
            System.out.println();

            // Last column, from top to bottom
            for (int i = step + 1; i <= squareSide; i++) {
                System.out.print(matrix[i][squareSide] + "\t");
            }
            System.out.println();

            // Bottom row, from last to first column
            for (int j = squareSide - 1; j >= step; j--) {
                System.out.print(matrix[squareSide][j] + "\t");
            }
            System.out.println();

            // First column, from bottom to top
            for (int i = squareSide - 1; i > step; i--) {
                System.out.print(matrix[i][step] + "\t");
            }
            System.out.println();
        }
    }
}
