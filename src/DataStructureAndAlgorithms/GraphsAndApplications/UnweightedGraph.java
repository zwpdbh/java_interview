package DataStructureAndAlgorithms.GraphsAndApplications;

import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V> {
    public UnweightedGraph() {};


    public UnweightedGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    /** Construct a graph from vertices and edges stored in arrays*/
    public UnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }
}
