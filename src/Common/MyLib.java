package Common;

import java.util.ArrayList;
import java.util.Random;

public class MyLib {

    /**helper method which print out the content of array*/
    public static void printElements(Integer[] A) {
        System.out.println("After sorting: ");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    /**helper method which print out the content of arraylist*/
    public static void printElements(ArrayList<Integer> A) {
        System.out.println("Initial elements are: ");
        for (int i = 0; i < A.size(); i++) {
            System.out.print(A.get(i) + " ");
        }
        System.out.println();
    }

    /**Helper method which exchange two element in the array*/
    public static void exch(Integer[] A, int i, int j) {
        int tmp = A[j];
        A[j] = A[i];
        A[i] = tmp;
    }

    /**helper method get a random integer between min(inclusive) and max(inclusive)*/
    public static int randomIntBetween(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
