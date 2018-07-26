package Graph;

import java.util.Iterator;

public interface BinaryTree<T> extends Iterable<T> {
    // returns the element stored in the root of the tree.
    public T getRootElement();

    // returns the left subtree of the root
    public BinaryTree<T> getLeft();

    // returns the right subtree of the root
    public BinaryTree<T> getRight();

    // returns true if the binary tree contains an element that matches the specified element and false otherwise
    public boolean contains(T target);

    // return a reference to the element in the tree matching
    public T find (T target);

    // returns true if the binary tree contains no elements, and false otherwise
    public boolean isEmpty();

    // return the number of elements in the binary tree
    public int size();

    // returns the string representation of the binary tree
    public String toString();

    // returns a pre-order traversal on the binary tree
    public Iterable<T> preorder();

    // returns an inorder traversal on the binary tree
    public Iterator<T> inorder();

    // returns a postorder traversal on the binary tree
    public Iterator<T> postorder();

    // performs a level-order traversal on the binary tree
    public Iterator<T> levelorder();
}
