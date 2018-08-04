package DataStructureAndAlgorithms.MyGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class BinarySearchTree<T extends Comparable<T>> {
    /**
     * basically tree holds a pointer to node which is a recursive data structure
     * think of "tree.root" is like "tree->"
     */
    private TreeNode<T> root;

    private class TreeNode<T extends Comparable<T>> {
        private T key;
        private TreeNode<T> left;
        private TreeNode<T> right;

        private TreeNode(T key) {
            this.key = key;
            left = null;
            right = null;
        }
    }


    public BinarySearchTree() {
        this.root = null;
    }

    public void add(T key) {
        if (root == null) {
            root = new TreeNode<>(key);
        } else {
            TreeNode<T> current, parent;
            current = parent = root;
            while (current != null) {
                if (current.key.compareTo(key) > 0) {
                    parent = current;
                    current = current.left;
                } else if (current.key.compareTo(key) < 0) {
                    parent = current;
                    current = current.right;
                }
            }

            if (parent.key.compareTo(key) > 0) {
                parent.left = new TreeNode<>(key);
            } else {
                parent.right = new TreeNode<>(key);
            }
        }
    }



    public TreeNode search(T target) {
        return searchAux(root, target);
    }

    private TreeNode searchAux(TreeNode<T> node, T target) {
        if (node == null) {
            return null;
        } else if (node.key.compareTo(target) == 0) {
            return node;
        } else if (node.key.compareTo(target) > 0) {
            return searchAux(node.left, target);
        } else {
            return searchAux(node.right, target);
        }
    }


    public boolean delete(T target) {
        TreeNode<T> parent = null;
        TreeNode<T> current = root;

        while (current != null) {
            if (target.compareTo(current.key) < 0) {
                parent = current;
                current = current.left;
            } else if (target.compareTo(current.key) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // element is in the tree pointed by current
            }
        }
        if (current == null) {
            // element is not in the tree
            return false;
        }

        if (current.left == null && current.right == null) {
            if (target.compareTo(parent.key) < 0) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.left == null && current.right != null) {
            if (target.compareTo(parent.key) < 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null && current.left != null) {
            if (target.compareTo(parent.key) < 0) {
                parent.left = current.left;
            } else {
                parent.left = current.left;
            }
        } else {
            // find the current's leftMost in the right subtree
            TreeNode<T> parentOfLeftMost = current;
            TreeNode<T> leftMost = current.right;
            while (leftMost.left != null) {
                parentOfLeftMost = leftMost;
                leftMost = leftMost.left;
            }

            // replace current with leftMost
            current.key = leftMost.key;

            if (parentOfLeftMost.left == leftMost) {
                parentOfLeftMost.left = leftMost.right;
            } else {
                // special case where: parentOfLeftMost == current
                parentOfLeftMost.right = leftMost.right;
            }
        }

        return true;
    }



    public StringBuilder treeToDot() {
        StringBuilder sb = new StringBuilder();
        // use digraph for graph with direction.
        sb.append("digraph ");
        sb.append("binary_search_tree {\n");
        sb.append("node [shape = Mrecord, penwidth = 2];\n");
        if (this.root != null) {
            treeOutputDotAux(root, sb);
        }
        sb.append("}\n");
        return sb;
    }

    // inorder traversal
    private void treeOutputDotAux(TreeNode<T> node, StringBuilder sb) {
        if (node.key != null) {
            sb.append(String.format("\"%s\"[label=\"{<f0>%s|{<f1>|<f2>}}\"];\n", node.key, node.key));
        }
        if (node.left != null) {
            treeOutputDotAux(node.left, sb);
            sb.append(String.format("\"%s\":f1 -> \"%s\":f0;\n", node.key, node.left.key));

        }
        if (node.right != null) {
            treeOutputDotAux(node.right, sb);
            sb.append(String.format("\"%s\":f2 -> \"%s\":f0;\n", node.key, node.right.key));
        }
    }


    public void show() {
        // the path is relative to the root of the project.
        Path dotFilePath = Paths.get("src/DataStructureAndAlgorithms.GraphsAndApplications.MyGraph/tree.dot");
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
            cmd.add(dotFilePath.getParent().toAbsolutePath().toString() + "/binary_search_tree.png");

            ProcessBuilder generateGraph = new ProcessBuilder(cmd);
            Process process = generateGraph.start();

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
