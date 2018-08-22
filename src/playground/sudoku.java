package playground;

public class sudoku {
    public static final int size = 9;
    private static final int[] PUZZLE = new int [] {
        0,0,2,5,0,0,0,0,3,
        0,4,0,0,6,7,0,0,0,
        1,5,0,0,0,3,0,0,0,
        0,0,8,0,0,0,0,0,4,
        5,6,0,0,0,0,0,1,7,
        4,0,0,0,0,0,8,0,0,
        0,0,0,6,0,0,0,8,1,
        0,0,0,1,8,0,0,2,0,
        2,0,0,0,0,5,7,0,0 };

    public static void main(String[] args) {
        // TODO
        printSuDoku(PUZZLE);

        // test valid or not
        System.out.println(validAtGrid(4, 0, 3));
    }

    private static void printSuDoku(int[] grid) {
        for (int i = 0; i < size; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---+---+---");
            }
            for (int j = 0; j < size; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                int num = grid[atIndex(i, j)];
                if (num == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(num);
                }

            }
            System.out.println();
        }
    }

    /**
     * Helper function to see if the number is valid in the row in Grid
     * @param row the row in the grid, from 0 ~ 8;
     * @param num the number to put on the Grid;
     * */
    private static boolean validInRow(int row, int num) {
        int from = row * size;
        int end = (row+1) * size -1;
        for (int i = from; i <= end; i++) {
            if (PUZZLE[i] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper function to see if the number is valid in the col in Grid
     * @param col the col in the grid, from 0 ~ 8;
     * @param num the number to put on the Grid;
     * */
    private static boolean validInCol(int col, int num) {
        int column = col % size;
        for (int k = 0; k < 9; k++) {
            if (PUZZLE[k*9 + column] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper function to see if the number is valid in the a certain block
     * @param num the number to put
     * @param row the row in the Grid
     * @param col the col in the Grid
     * */
    private static boolean validInBlock(int row, int col, int num) {
        int blockRow = row / 3;
        int blockCol = col / 3;

        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                int r = blockRow + m;
                int l = blockCol + n;
                if (PUZZLE[atIndex(r, l)] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * method for decide whether a number is valid in the Grid's row and col
     * */
    private static boolean validAtGrid(int row, int col, int num) {
        if (num >9 || num < 1) {
            System.out.println("Please input a valid number");
            System.exit(-1);
        }
        if (inGrid(row) && inGrid(col)) {
            if ( validInRow(row, num) && validInCol(col, num) && validInBlock(row, col, num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method for decide whether the number is valid when the user specify the index of the PUZZLE array
     * */
    private static boolean validAtIndex(int index, int num) {
        int[] gridPosition = fromIndexToGrid(index);
        return validAtGrid(gridPosition[0], gridPosition[1], num);
    }

    /**
     * Helper method which convert a position in Grid using row, and col into index in the PUZZLE array
     * */
    private static int atIndex(int atRow, int atCol) {
        return atRow * size + atCol;
    }

    /**
     * Helper method to make sure the range is from 0~8
     * */
    private static boolean inGrid(int k) {
        if (k>=0 && k<=8) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper method which convert an index from PUZZLE array to row, and col in the Grid
     * position[0] is the row
     * position[1] is the col
     * */
    private static int[] fromIndexToGrid(int index) {
        int[] position = new int[2];
        position[0] = index / size;
        position[1] = index % size;
        return position;
    }
}
