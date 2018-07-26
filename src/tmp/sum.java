package tmp;
class Summer {
    private static final int SIZE = 1000;

//    public static int sum2D(int[][] grid) {
//        int sum = 0;
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                sum += grid[j][i];
//            }
//        }
//        return sum;
//    }

    public static int sum2D(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum += grid[i][j];
            }
        }
        return sum;
    }
}
