package SortingsAndOrderStatistics.MediansAndOrderStatistics;

import static SortingsAndOrderStatistics.Quick.randomizedPartiion;

public class RandomizedSelect {
    /**returns the ith smallest element of the array A[p..r]
     * @param A the array element
     * @param p the leftSubTree boundary of range
     * @param r the rightSubTree boundary of range
     * @param i the ith element in the range of the array
     * @return the ith smallest element in the array between range*/
    public static int select(Integer[] A, int p, int r, int i) {
        if (p == r) {
            return A[p];
        }
        int q = randomizedPartiion(A, p, r);
        int k = q - p + 1;

        if (i == k) return A[q];
        else if (i < k) {
            return select(A, p, q - 1, i);
        } else {
            return select(A, q + 1, r, i - k);
        }
    }
}
