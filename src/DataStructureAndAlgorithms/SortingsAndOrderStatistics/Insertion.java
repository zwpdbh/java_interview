package DataStructureAndAlgorithms.SortingsAndOrderStatistics;

public class Insertion {
    public static void sort(Integer[] A) {
        for (int j = 1; j < A.length; j++) {
            int key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] > key) {
                A[i+1] = A[i];
                i--;
            }
            A[i+1] = key;
        }
    }
}
