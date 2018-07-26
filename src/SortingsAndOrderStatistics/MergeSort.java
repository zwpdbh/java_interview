package SortingsAndOrderStatistics;

public class MergeSort {
    private static Integer[] aux;


    public static void sort(Integer[] A) {
        aux = new Integer[A.length];
        sort(A, 0, A.length -1);
    }

    public static void sort(Integer[] A, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = l + (r - l) / 2;
        sort(A, l, m);
        sort(A, m + 1, r);
        merge(A, l, m, r);
    }

    public static void merge(Integer[] A, int l, int m, int r) {
        int i = l;
        int j = m + 1;

        for (int k = l; k <= r; k++) {
            aux[k] = A[k];
        }

        for (int k = l; k <= r; k++) {
            if (i > m) {
                A[k] = aux[j];
                j++;
            } else if (j > r) {
                A[k] = aux[i];
                i++;
            } else if (aux[j] < aux[i]) {
                A[k] = aux[j];
                j++;
            } else {
                A[k] = aux[i];
                i++;
            }
        }
    }
}
