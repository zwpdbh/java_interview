package Graph;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Thread.sleep;

public class DemoBinarySearchTree {
    public static void main(String[] args) {


        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(2);
        bst.add(1);
        bst.add(8);
        bst.add(4);
        bst.add(9);
        bst.add(3);
        bst.add(6);
        bst.add(5);
        bst.add(7);

        bst.show();
        try {
            sleep(10000);
            if (bst.delete(4)) {
                bst.show();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
