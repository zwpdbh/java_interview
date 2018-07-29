package Graph;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    /**Construct an empty graph*/
    protected AbstractGraph() {}

    /** Create adjacency lists for each vertex*/
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        for (Edge edge: edges) {
            addEdge(edge.u, edge.v);
        }
    }

    /**Construct a graph from vertices and edges stored in List*/
    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }

        createAdjacencyLists(edges, vertices.size());
    }

    /**Construct a graph for integer vertices 0, 1, 2 and edge list
     * vertices is {0, 1, ...}*/
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V)(new Integer(i)));
        }

        createAdjacencyLists(edges, numberOfVertices);
    }


    /**Return the number of vertices in the graph*/
    public int getSize() {
        return vertices.size();
    }
}
