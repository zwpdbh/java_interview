package Graph;

public class DemoBinarySearchTree {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(1);
        bst.add(10);
        bst.add(3);
        bst.add(7);


        bst.show();
    }
}
