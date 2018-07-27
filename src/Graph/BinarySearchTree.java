package Graph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class BinarySearchTree<T extends Comparable> {
    /**
     * basically tree is a pointer to node which is a recursive data structure
     * think of "tree.root" is like "tree->"
     */
    private Node root;

    // Assume that if there is an instance of Node, its key must not be empty
    private class Node<T extends Comparable> {
        private T key;
        private BinarySearchTree<T> left;
        private BinarySearchTree<T> right;

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

    public BinarySearchTree() {
        this.root = null;
    }

    public void add(T key) {
        // if root is empty, create new node
        if (this.root == null) {
            this.root = new Node(key);
            // if root is not empty and it is bigger than the key
        } else if (this.root.key.compareTo(key) > 0) {
            this.root.left.add(key);
        } else {
            this.root.right.add(key);
        }
    }


    public StringBuilder treeToDot() {
        StringBuilder sb = new StringBuilder();
        // use digraph for graph with direction.
        sb.append("digraph ");
        sb.append("binary_search_tree {\n");
        sb.append("node [shape = Mrecord, penwidth = 2];\n");
        treeOutputDotAux(this, sb);
        sb.append("}\n");

        return sb;
    }

    // inorder traversal
    private void treeOutputDotAux(BinarySearchTree tree, StringBuilder sb) {

        if (tree.root.key != null) {
            sb.append(String.format("\"%s\"[label=\"{<f0>%s|{<f1>|<f2>}}\"];\n", tree.root.key, tree.root.key));
        }

        if (tree.root.left.root != null) {
            treeOutputDotAux(tree.root.left, sb);
            sb.append(String.format("\"%s\":f1 -> \"%s\":f0;\n", tree.root.key, tree.root.left.root.key));
        }

        if (tree.root.right.root != null) {
            treeOutputDotAux(tree.root.right, sb);
            sb.append(String.format("\"%s\":f1 -> \"%s\":f0;\n", tree.root.key, tree.root.right.root.key));
        }
    }


    public void show() {
        // the path is relative to the root of the project.
        Path dotFilePath = Paths.get("src/Graph/tree.dot");
        try {
            Files.write(dotFilePath, this.treeToDot().toString().getBytes());
        } catch (IOException e) {
            System.out.println(e.toString());
        }


        try {
            ArrayList<String> cmd = new ArrayList<>();
            cmd.add("dot");
            cmd.add("-Tpng");
            cmd.add(dotFilePath.toAbsolutePath().toString());
            cmd.add("-o");
            cmd.add(dotFilePath.getParent().toAbsolutePath().toString()+"/binary_search_tree.png");

            ProcessBuilder generateGraph = new ProcessBuilder(cmd);
            Process process =  generateGraph.start();

            int errCode = process.waitFor();
            System.out.println("Generate graph " + (errCode == 0 ? "Succeed" : "Failed"));

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private void getCurrentEnv(Map<String, String> env) {
        for (String key : env.keySet()) {
            System.out.println(key + " : " + env.get(key));
        }
    }

}