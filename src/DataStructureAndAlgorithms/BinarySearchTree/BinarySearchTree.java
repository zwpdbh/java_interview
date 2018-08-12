//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Map;
//
//public class BinarySearchTree<T extends Comparable<T>> {
//    /**
//     * basically tree holds a pointer to node which is a recursive data structure
//     * think of "tree.root" is like "tree->"
//     */
//    private Node<T> root;
//
//    private class Node<T extends Comparable<T>> {
//        private T key;
//        private
//        private Node<T> left;
//        private Node<T> right;
//
//        private Node(T key) {
//            this.key = key;
//
//            left = null;
//            right = null;
//        }
//
//        @Override
//        public String toString() {
//            return "Node{" +
//                    "key=" + key +
//                    '}';
//        }
//
//        public T getKey() {
//            return key;
//        }
//
//        public Node<T> getLeft() {
//            return left;
//        }
//
//        public Node<T> getRight() {
//            return right;
//        }
//    }
//
//
//    public BinarySearchTree() {
//        this.root = null;
//    }
//
//    public void add(T key) {
//        if (root == null) {
//            root = new Node<>(key);
//        } else {
//            Node<T> current, parent;
//            current = parent = root;
//            while (current != null) {
//                if (current.key.compareTo(key) > 0) {
//                    parent = current;
//                    current = current.left;
//                } else if (current.key.compareTo(key) < 0) {
//                    parent = current;
//                    current = current.right;
//                }
//            }
//
//            if (parent.key.compareTo(key) > 0) {
//                parent.left = new Node<>(key);
//            } else {
//                parent.right = new Node<>(key);
//            }
//        }
//    }
//
//    public int size() {
//        return size(root);
//    }
//
//    private int size(Node x) {
//        if (x == null) {
//            return 0;
//        } else {
//            return 1 + size(x) + size(x.getLeft()) + size(x.getRight());
//        }
//    }
//
//
//    public Node search(T target) {
//        return searchAux(root, target);
//    }
//
//    private Node<T> searchAux(Node<T> node, T target) {
//        if (node == null) {
//            return null;
//        } else if (node.key.compareTo(target) == 0) {
//            return node;
//        } else if (node.key.compareTo(target) > 0) {
//            return searchAux(node.left, target);
//        } else {
//            return searchAux(node.right, target);
//        }
//    }
//
//    /**First, like search, find the node contains the target
//     * Then, there are 3 cases to consider:
//     * 1) the node is a leaf
//     * 2) the node has one child(maybe left or right), "snip" the node out of this sequence
//     *    by connecting its parent directly to its child.
//     * 3) the node has two children
//     * Generally, we need to remember the parent of the node to be delete so we could modify its child field*/
//    public boolean delete(T target) {
//        Node<T> parent = root;
//        Node<T> current = root;
//        boolean isLeftChild = true;
//
//        while (current.key != target) {
//            parent = current;
//            if (target.compareTo(current.key) < 0) {
//                isLeftChild = true;
//                current = current.left;
//            } else {
//                isLeftChild = false;
//                current = current.right;
//            }
//            if (current == null) {
//                return false;
//            }
//        }
//
//        // found node to delete
//        // case 1: if no children, simply delete it
//        if (current.left == null && current.right == null) {
//            if (current == root) {
//                root = null;
//            } else if (isLeftChild) {
//                parent.left = null;     // disconnect from parent
//            } else {
//                parent.right = null;
//            }
//        } else if (current.right == null) {
//            // case 2.1: only has left child
//            if (current == root) {
//                root = current.left;
//            } else if (isLeftChild) {
//                parent.left = current.left;
//            } else {
//                parent.right = current.left;
//            }
//        } else if (current.left == null) {
//            // case 2.2: only has right child
//            if (current == root) {
//                root = current.right;
//            } else if (isLeftChild) {
//                parent.left = current.right;
//            } else {
//                parent.right = current.right;
//            }
//        } else {
//            // case 3: The node to be deleted has two children
//
//        }
//
//        return true;
//    }
//
//    private Node<T> getSuccessor(Node<T> delNode) {
//        Node<T> successorParent = delNode;
//        Node<T> successor = delNode;
//        Node<T> current = delNode.right;
//
//        while (current != null) {
//            successorParent = successor;
//            successor = current;
//            current = current.left;
//        }
//
//        // if successor not right child, make connections
//        if (successor != delNode.right) {
//            successorParent.left = successor.right;
//            successor.right = delNode.right;
//        }
//    }
//
//
//
//    public StringBuilder treeToDot() {
//        StringBuilder sb = new StringBuilder();
//        // use digraph for graph with direction.
//        sb.append("digraph ");
//        sb.append("binary_search_tree {\n");
//        sb.append("node [shape = Mrecord, penwidth = 2];\n");
//        if (this.root != null) {
//            treeOutputDotAux(root, sb);
//        }
//        sb.append("}\n");
//        return sb;
//    }
//
//    // inorder traversal
//    private void treeOutputDotAux(Node<T> node, StringBuilder sb) {
//        if (node.key != null) {
//            sb.append(String.format("\"%s\"[label=\"{<f0>%s|{<f1>|<f2>}}\"];\n", node.key, node.key));
//        }
//        if (node.left != null) {
//            treeOutputDotAux(node.left, sb);
//            sb.append(String.format("\"%s\":f1 -> \"%s\":f0;\n", node.key, node.left.key));
//
//        }
//        if (node.right != null) {
//            treeOutputDotAux(node.right, sb);
//            sb.append(String.format("\"%s\":f2 -> \"%s\":f0;\n", node.key, node.right.key));
//        }
//    }
//
//
//    public void show() {
//        // the path is relative to the root of the project.
//        Path dotFilePath = Paths.get("/Users/zw/code/Java_Projects/Java_Interview/src/DataStructureAndAlgorithms/BinarySearchTree/tree.dot");
//        try {
//            Files.write(dotFilePath, this.treeToDot().toString().getBytes());
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
//
//
//        try {
//            ArrayList<String> cmd = new ArrayList<>();
//            cmd.add("dot");
//            cmd.add("-Tpng");
//            cmd.add(dotFilePath.toAbsolutePath().toString());
//            cmd.add("-o");
//            cmd.add(dotFilePath.getParent().toAbsolutePath().toString() + "/binary_search_tree.png");
//
//            ProcessBuilder generateGraph = new ProcessBuilder(cmd);
//            Process process = generateGraph.start();
//
//            int errCode = process.waitFor();
//            System.out.println("Generate graph " + (errCode == 0 ? "Succeed" : "Failed"));
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//
//    }
//
//    private void getCurrentEnv(Map<String, String> env) {
//        for (String key : env.keySet()) {
//            System.out.println(key + " : " + env.get(key));
//        }
//    }
//
//}

package DataStructureAndAlgorithms.BinarySearchTree;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;            // key
        private Value value;        // associated value
        private Node left, right;   // links to subtree
        private int N;              // Number of nodes in subtree rooted here


        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    private Node root;

    public int size() {
        return size(root);
    }

    public int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.N;
        }
    }


    public Value get(Key key) {
        return get(root, key);
    }
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }


    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }


    private Node floor(Node x, Key key) {
        if (x == null)  {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }

        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k-t-1);
        } else {
            return x;
        }
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }


    private void deleteMin() {
        root = deleteMin(root);
    }

    /**we go left until finding a Node that has a null left link and then
     * replace "the link to that node" by its right link*/
    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }

        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void delete(Key key) {
        root = delete(root, key);
    }

    /**Write a recursive method that takes a link to a Node as argument
     * and returns a link to a Node, so we could reflect changes to the tree by
     * assigning the result to the link used as argument*/
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            // Save a link to the node to be deleted in t.
            Node t = x;

            // Set x to point to its successor
            x = min(t.right);

            // set the right link of x to deleteMin(t.right)
            x.right = deleteMin(t.right);

            // set the right link of x to t.right
            x.left = t.left;
        }

        x.N = size(x.left) + 1 + size(x.right);

        return x;
    }
}