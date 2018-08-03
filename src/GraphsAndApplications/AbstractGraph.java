package GraphsAndApplications;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {
    /**
     * Store vertices
     */
    protected List<V> vertices = new ArrayList<>();

    /**
     * Adjacency Lists
     */
    protected List<List<Edge>> neighbors = new ArrayList<>();

    /**
     * Construct an empty graph
     */
    protected AbstractGraph() {
    }

    /**
     * Create adjacency lists for each vertex
     */
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        for (Edge edge : edges) {
            addEdge(edge.u, edge.v);
        }
    }

    /**
     * Construct a graph from vertices and edges stored in List
     */
    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }

        createAdjacencyLists(edges, vertices.size());
    }

    /**
     * Construct a graph for integer vertices 0, 1, 2 and edge list
     * vertices is {0, 1, ...}
     */
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V) (new Integer(i)));
        }

        createAdjacencyLists(edges, numberOfVertices);
    }


    /**
     * Return the number of vertices in the graph
     */
    public int getSize() {
        return vertices.size();
    }


    /**
     * Return the vertices in the graph
     */
    public List<V> getVertices() {
        return vertices;
    }

    /**
     * Return the object for the specific vertex
     */
    public V getVertex(int index) {
        return vertices.get(index);
    }

    /**
     * Return the index for the specified vertex object
     */
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    /**
     * Return the neighbours of the specified vertex
     */
    public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge e : neighbors.get(index)) {
            result.add(e.v);
        }

        return result;
    }

    /**
     * Return the degree for a specified vertex
     */
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    /**
     * Print the edges
     */
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e : neighbors.get(u)) {
                System.out.print("(" + getVertex(e.u) + ", "
                        + getVertex(e.v) + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Add a vertex to the graph
     */
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add an edge to the graph
     */
    protected boolean addEdge(Edge e) {
        if (e.u < 0 || e.u > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.u);
        }

        if (e.v < 0 || e.v > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.u);
        }

        if (!neighbors.get(e.u).contains(e)) {
            neighbors.get(e.u).add(e);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add an edge to the graph
     */
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    /**
     * Edge inner class inside the AbstractGraph class
     */
    public static class Edge {
        public int u;
        public int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public boolean equals(Object o) {
            return u == ((Edge) o).u && v == ((Edge) o).v;
        }
    }

    /**
     * Tree inner class inside the AbstractGraph class
     */
    public class Tree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot() {
            return root;
        }

        /**
         * Return the parent of vertex v
         */
        public int getParent(int v) {
            return parent[v];
        }

        /**
         * Return an array representing search order
         */
        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        /**
         * Return the number of vertices found
         */
        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }

        /**
         * Return the path of vertices from a vertex to the root
         */
        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();
            do {
                path.add(vertices.get(index));
                index = parent[index];
            } while (index != -1);

            return path;
        }

        /**
         * Print a path from the root to vertex v
         */
        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.println("A path from " + vertices.get(root) + " to "
                    + vertices.get(index) + ": ");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
        }

        /** Print the whole tree */
        public void printTree() {
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    // Display an edge
                    System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }

    /**Obtain a DFS tree starting from vertex v */


}
