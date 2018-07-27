package Graph;

import java.util.ArrayList;
import java.util.Collections;
public class DemoBinarySearchTree {
    public static void main(String[] args) {
        ArrayList<Integer> dataSet = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataSet.add(i);
        }
        Collections.shuffle(dataSet);

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer i : dataSet) {
            bst.add(i);
        }

        bst.show();

        Integer target = 11;
        Node<Integer> result = bst.search(target);
        if (result != null) {
            System.out.println("Search " + target + " result is: " + result.key);
        } else {
            System.out.println("Search " + target + ", not found");
        }

    }
}
