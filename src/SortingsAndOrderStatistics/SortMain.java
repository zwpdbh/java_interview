package SortingsAndOrderStatistics;

import SortingsAndOrderStatistics.MediansAndOrderStatistics.RandomizedSelect;

import java.util.ArrayList;
import java.util.Collections;

import static Common.MyLib.printElements;

public class SortMain {
    private final static int numOfElements = 15;

    public static void main(String[] args) {

        ArrayList<Integer> elements = initElements(numOfElements);

        Collections.shuffle(elements);
        printElements(elements);

        Integer[] A = elements.toArray(new Integer[elements.size()]);
        System.out.println("The 4th smallest element in array is: " + RandomizedSelect.select(A, 0, A.length-1, 4));
//        MergeSort.sort(A);
//        Insertion.sort(A);
//        Quick.sort(A);
        Quick.randomizedSort(A);

        printElements(A);
    }




    private static ArrayList<Integer> initElements(int size) {
        ArrayList<Integer> elements = new ArrayList<>();
        for (int i = 0; i < size; i++){
            elements.add(i);
        }
        return elements;
    }
}
