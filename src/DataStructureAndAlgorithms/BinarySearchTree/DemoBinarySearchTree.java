package DataStructureAndAlgorithms.BinarySearchTree;

import static java.lang.Thread.sleep;

public class DemoBinarySearchTree {
    public static void main(String[] args) {


        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.put(2, 10);
        bst.put(1, 10);
        bst.put(8, 10);
        bst.put(4, 10);
        bst.put(9, 10);
        bst.put(3, 10);
        bst.put(6, 10);
        bst.put(5, 10);
        bst.put(7, 10);

        bst.show();

        try {
            sleep(10000);
            bst.delete(2);
            bst.show();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
