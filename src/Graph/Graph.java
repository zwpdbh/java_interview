package Graph;

import java.util.List;

public interface Graph<V> {
    /**Return the number of vertices in the graph*/
    public int getSize();

    /**Return the vertices in the graph*/
    public List<V> getVertices();

    /**Return the index for the specified vertex object*/
    public int getIndex(V v);

    /**Return the neighbors of vertex with the specified index*/
    public List<Integer> getNeighbors(int index);

    /**Return the degree for a specified vertex*/
    public int getDegree(int v);

    /**Print the edges*/
    public void printEdges();

    /**Clear the graph*/
    public void clear();

    /**Add a vertex to the graph*/
    public void addVertex(V vertex);

    /**Add an edge to the graph*/
    public void addEdge(int u, int v);

    /**Obtain a depth-first search tree starting from v */
    public AbstractGraph<V>.Tree dfs(int v);

    /**Obtain a breadth-first search tree starting from v*/
    public AbstractGraph<V>.Tree bsf(int v);
}
