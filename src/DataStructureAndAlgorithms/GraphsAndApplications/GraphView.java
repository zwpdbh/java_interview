package DataStructureAndAlgorithms.GraphsAndApplications;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;

public class GraphView extends Pane {
    private Graph<? extends Displayable> graph;

    public GraphView(Graph<? extends Displayable> graph) {
        // Draw vertices
        List<? extends Displayable> vertices = graph.getVertices();
        for (int i = 0; i < graph.getSize(); i++) {
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();

            String name = vertices.get(i).getName();

            getChildren().add(new Circle(x, y, 16));
            getChildren().add(new Text(x-8, y-18, name));
        }

        // Draw edges for paris of vertices
        for (int i = 0; i < graph.getSize(); i++) {
            List<Integer> neighbors = graph.getNeighbors(i);
            // get x, y position for vertex i
            int x1 = graph.getVertex(i).getX();
            int y1 = graph.getVertex(i).getY();

            // get each x,y for vertex i's neighbours
            for (int v: neighbors) {
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();

                getChildren().add(new Line(x1, y1, x2, y2));
            }
        }
    }
}
