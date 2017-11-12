import java.util.Stack;

/**
 * Создать программу, отыскивающую проход по лабиринту.
 * Лабиринт представляется в виде матрицы, состоящей из квадратов. Каждый квадрат либо открыт,
 * либо закрыт. Вход в закрытый квадрат запрещен. Если квадрат открыт, то вход в него возможен со
 * стороны, но не с угла. Каждый квадрат определяется его координатами в матрице.
 * Программа находит проход через лабиринт, двигаясь от заданного входа. После отыскания прохода
 * программа выводит найденный путь в виде координат квадратов. Для хранения пути использовать стек.
 *
 * Пример лабиринта:  1 1 1 1 1 1 1 1 1 1 1
 *                    # # # # # #         1
 *                    1 1 1 1 1 # 1 1 1 1 1
 *                    1     # # # 1       1
 *                    1 1 1 # 1 1 1 1 1   1
 *                    1     # # # # # # # 1
 *                    1   1 1 1 1 1 1 1 # 1
 *                    1   1   1         # 1
 *                    1   1   1 1 1 1 1 # 1
 *                    1           1     # 1
 *                    1 1 1 1 1 1 1 1 1 # 1
 *
 * Пример вывода: [1,0] [1,1] [1,2] [1,3] [1,4] [1,5] [2,5] [3,5] [3,4] [3,3] [4,3] [5,3] [5,4] [5,5] [5,6] [5,7] [5,8] [5,9] [6,9] [7,9] [8,9] [9,9] [10,9]
 *
 * @author Vova Korobko
 */
public class Task6 {

    public static void main(String[] args) {

        char[][] labyrinth = {
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1'},
                {'1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1'},
                {'1', '1', '1', '0', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1'},
                {'1', '0', '1', '0', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
        };

        // Getting the way
        Stack<Point> pointStack =findWay(labyrinth,new Point(1,0),new Point(10, 9));

        // Printing coordinates to console
        while (!pointStack.isEmpty()) {
            System.out.print(pointStack.pop() + " ");
        }

    }
    private static Stack<Point> findWay(char[][] labyrinth, Point start, Point end) {
        /*
        * First step: marking all possible ways with numbers in ascending order.
        * New maze looks like this:
        *  0  0   0   0   0   0   0   0   0   0   0
        *  1  2   3   4   5   6   7   8   9   10  0
        *  0  0   0   0   0   7   0   0   0   0   0
        *  0  12  11  10  9   8   0   22  21  20  0
        *  0  0   0   11  0   0   0   0   0   19  0
        *  0  14  13  12  13  14  15  16  17  18  0
        *  0  15  0   0   0   0   0   0   0   19  0
        *  0  16  0   22  0   24  23  22  21  20  0
        *  0  17  0   21  0   0   0   0   0   21  0
        *  0  18  19  20  21  22  0   24  23  22  0
        *  0  0   0   0   0   0   0   0   0   23  0
        *
        */
        int height = labyrinth.length-1;
        int width  = labyrinth[0].length-1;

        Point to   = end;

        int index = 1;
        int[][] markedMaze = new int[height+1][width+1];

        Stack<Point> oldStack = new Stack<>();
        Stack<Point> newStack;

        int row = start.getRowIndex();
        int column = start.getColumnIndex();

        markedMaze[row][column] = index;

        oldStack.push(start);

        while (!start.equals(to)) {
            int count = 0;
            index++;
            newStack = new Stack<>();
            while (!oldStack.isEmpty()) {

                Point oldPoint = oldStack.pop();
                row = oldPoint.getRowIndex();
                column = oldPoint.getColumnIndex();
                //Searching to top
                if (row > 0 && labyrinth[row - 1][column] == '0' && markedMaze[row - 1][column] == 0) {
                    markedMaze[row - 1][column] = index;
                    to = new Point(row - 1, column);
                    newStack.push(to);
                    count++;
                    if (start.equals(to))
                        break;
                }
                // Searching to right
                if (column < width && labyrinth[row][column + 1] == '0' && markedMaze[row][column + 1] == 0) {
                    markedMaze[row][column + 1] = index;
                    to = new Point(row, column + 1);
                    newStack.push(to);
                    count++;
                    if (start.equals(to))
                        break;
                }
                // Searching to bottom
                if (row < height && labyrinth[row + 1][column] == '0' && markedMaze[row + 1][column] == 0) {
                    markedMaze[row + 1][column] = index;
                    to = new Point(row + 1, column);
                    newStack.push(to);
                    count++;
                    if (start.equals(to))
                        break;
                }
                // Searching to left
                if (column > 0 && labyrinth[row][column - 1] == '0' && markedMaze[row][column - 1] == 0) {
                    markedMaze[row][column - 1] = index;
                    to = new Point(row, column - 1);
                    newStack.push(to);
                    count++;
                    if (start.equals(to))
                        break;
                }
            }
            oldStack = newStack;
            if (count == 0)
                break;
        }

        /*
        * Second step: tracing marked way from end point to start point
        */
        row = end.getRowIndex();
        column = end.getColumnIndex();

        Stack<Point> stack = new Stack<>();

        if (markedMaze[row][column] > 1) {
            stack.push(new Point(end.getRowIndex(), end.getColumnIndex()));
            index = markedMaze[row][column];
            while (index > 1) {
                // Going up
                if (row > 0 && markedMaze[row - 1][column] == index - 1) {
                    row--;
                    // Going right
                } else if (column < width && markedMaze[row][column + 1] == index - 1) {
                    column++;
                    //Going down
                } else if (row < height && markedMaze[row + 1][column] == index - 1) {
                    row++;
                    // Going left
                } else if (column > 0 && markedMaze[row][column - 1] == index - 1) {
                    column--;
                }
                index--;
                stack.push(new Point(row, column));
            }
        }

        return stack;
    }
}

class Point {
    private final int rowIndex;
    private final int columnIndex;

    Point(int rowIndex, int columnIndex){
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    int getRowIndex() {
        return rowIndex;
    }

    int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (rowIndex != point.rowIndex) return false;
        return columnIndex == point.columnIndex;
    }

    @Override
    public int hashCode() {
        int result = rowIndex;
        result = 31 * result + columnIndex;
        return result;
    }

    @Override
    public String toString() {
        return "[" +
                rowIndex +
                "," + columnIndex +
                ']';
    }
}