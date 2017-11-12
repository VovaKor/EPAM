import utils.ConsolePrinter;

/**
 * Дана целочисленная прямоугольная матрица. Упорядочить строки,
 * по возрастанию по самой длинной серии одинаковых элементов.
 * Пример вывода:   1  1  0  0  1
 *                  0  1  1  1  0
 *                  1  1  1  1  1
 *
 * @author Vova Korobko
 */
public class Task4 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1, 1, 1},
                          {0, 1, 1, 1, 0},
                          {1, 1, 0, 0, 1}};
        //Getting sequences from given matrix
        int[][] sequences = getSequenceArray(matrix);

        //Sorting sequences in ascending order
        sortSequences(sequences);

        //Taking indexes from sequences and sorting matrix
        int[][] sortedArray = sortMatrix(matrix,sequences);

        //Printing result into console
        ConsolePrinter.print2DArray(sortedArray);
    }
    private static int[][] sortMatrix(int[][] matrix, int[][] indexes){

        int[][] sortedMatrix = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            int index = indexes[i][1];
            for(int j = 0; j < matrix[0].length; j++){
                sortedMatrix[i][j] = matrix[index][j];
            }
        }
        return sortedMatrix;
    }


    private static void sortSequences(int[][] sequences){
        for (int i = 0; i <sequences.length ; i++) {
            for (int j = 0; j < sequences.length - 1 - i; j++) {
                if(sequences[j][0]>sequences[j+1][0]){
                    int[] tmp = sequences[j];
                    sequences[j] = sequences[j+1];
                    sequences[j+1] = tmp;
                }
            }
        }
    }


    private static int getSequenceLength(int[] sequence){
        int maxLength = 0;
        int currentLength = 1;
        int value = sequence[0];

        for(int i = 1; i < sequence.length; i++){
            if(sequence[i]==value){
                currentLength++;
            }else{
                if(maxLength < currentLength){
                    maxLength = currentLength;
                }
                currentLength = 1;
                value = sequence[i];
            }
        }
        return maxLength > currentLength ? maxLength : currentLength;
    }

    private static int[][] getSequenceArray(int[][] matrix){
        int[][] sequenceLength = new int[matrix.length][2];

        for(int i = 0; i < matrix.length; i++){
            //saving sequence length
            sequenceLength[i][0] = getSequenceLength(matrix[i]);
            //remembering column position
            sequenceLength[i][1] = i;
        }

        return sequenceLength;
    }
}
