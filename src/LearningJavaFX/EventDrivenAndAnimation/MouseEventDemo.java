package LearningJavaFX.EventDrivenAndAnimation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MouseEventDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Text text = new Text(20, 20, "Programming is Fun");

        pane.getChildren().addAll(text);

        text.setOnMouseDragged(e -> {
            text.setX(e.getX());
            text.setY(e.getY());
        });

        Scene scene = new Scene(pane, 300, 100);
        scene.setOnMouseMoved(event -> {
            primaryStage.setTitle("MouseEventDemo X: " + event.getX() + ", Y: " + event.getY());
        });

        primaryStage.setTitle("MouseEventDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
