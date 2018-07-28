package SortingsAndOrderStatistics;

import static Common.MyLib.exch;
import static Common.MyLib.randomIntBetween;

public class Quick {
    public static void randomizedSort(Integer[] A) {
        randomizedQuickSort(A, 0, A.length - 1);
    }
    public static void sort(Integer[] A) {
        sort(A, 0, A.length-1);
    }

    private static void sort(Integer[] A, int l, int r) {
        if (r <= l) return;
        int j = partition(A, l, r);
        sort(A, l, j-1);
        sort(A, j+1, r);
    }

    /**the partition process rearange the array to make the following 3 conditions hold
     * The entry A[j] is in its final place in the array, for some j.
     * No entry in A[lo] through a[j-1] is greater than a[j].
     * No entry in A[j+1] through a[hi] is less than a[j].
     * */
    private static int partition(Integer[] A, int l, int r) {
        int i = l;
        int j = r + 1;

        int p = A[l];
        while (true) {
            // choose an entry at the leftSubTree that is >= than p
            while (A[++i] < p) {
                if (i == r) break;
            }
            // choose an entry from rightSubTree that is <= than p
            while (p < A[--j]) {
                if (j == l) break;
            }
            if (i >= j)break;
            exch(A, i, j);
        }
        exch(A, l, j);
        return j;
    }

    public static int randomizedPartiion(Integer[] A, int p, int r) {
        int i = randomIntBetween(p, r);
        exch(A, r, i);
        return partition(A, p, r);
    }

    public static void randomizedQuickSort(Integer[] A, int p, int r) {
        if (p < r) {
            int q = randomizedPartiion(A, p, r);
            randomizedQuickSort(A, p, q-1);
            randomizedQuickSort(A, q + 1, r);
        }
    }
}
