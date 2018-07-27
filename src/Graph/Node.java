package Graph;

public class Node<T extends Comparable> {
    public T key;
    public BinarySearchTree<T> left;
    public BinarySearchTree<T> right;

    public Node(T key) {
        this.key = key;
        this.left = new BinarySearchTree<>();
        this.right = new BinarySearchTree<>();
    }

    public T getKey() {
        return key;
    }

    public BinarySearchTree<T> getLeft() {
        return left;
    }

    public BinarySearchTree<T> getRight() {
        return right;
    }
}