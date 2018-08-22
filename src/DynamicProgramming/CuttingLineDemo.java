package DynamicProgramming;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**有一个长度为m的线段(整数), 切割其为n个线段, 每个线段的长度仍为整数:
 * 怎么切割,使得其多个线段的乘机最大, 例如m=8, => 2*3*3 = 18 */
public class CuttingLineDemo {

    private static HashMap<Integer, Integer> lookUpTable = new HashMap<>();
    private static List<Integer> cuttingResults = new ArrayList<>();

    public static void main(String[] args) {
        int size = 8;

        double startingTime = System.nanoTime();
        System.out.println("The max result for 8 is: " + naiveCutting(size));
        double endingTime = System.nanoTime();
        double naiveCost = endingTime - startingTime;
        System.out.println("naiveCutting spend: " + naiveCost);

        cuttingResults.clear();
        startingTime = System.nanoTime();
        System.out.println("The max result for 8 is: " + improvedCuttting(size));
        endingTime = System.nanoTime();
        double improvedCost = endingTime - startingTime;
        System.out.println("improvedCutting spend: " + improvedCost);
        System.out.println(cuttingResults);
        System.out.println("DP improve speed: " + naiveCost / improvedCost);

    }


    /**First, write down the Naive recursive version, then
     * analyze the redundant part (the repeated partial result), storing the answer for each
     * sub-problems in a table to look up instead of recompute.*/
    private static int naiveCutting(int size) {
        if (size == 1) {
            return 1;
        } else if (size == 2) {
            return 2;
        } else if (size == 3) {
            return 3;
        } else {
            return Math.max(naiveCutting(size - 2) * 2, naiveCutting(size - 3) * 3);
        }
    }


    private static int improvedCuttting(int size) {
        if (size == 1) {
            return 1;
        } else if (size == 2) {
            return 2;
        } else if (size == 3) {
            return 3;
        } else {
            if (!lookUpTable.containsKey(size - 2)) {
                lookUpTable.put(size - 2, improvedCuttting(size - 2));
            }
            if (!lookUpTable.containsKey(size - 3)) {
                lookUpTable.put(size - 3, improvedCuttting(size - 3));
            }

            if ((2 * lookUpTable.get(size - 2)) > (3 * lookUpTable.get(size -3))) {
                if (size - 2 >= 2) {
                    // ensure I have 2 units to cut
                    cuttingResults.add(2);
                }
                return 2 * lookUpTable.get(size - 2);
            } else {
                if (size - 3 >= 3) {
                    // ensure I have 3 units to cut
                    cuttingResults.add(3);
                }
                return 3 * lookUpTable.get(size -3);
            }
        }
    }

}
