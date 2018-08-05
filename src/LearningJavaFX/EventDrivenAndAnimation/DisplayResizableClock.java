package LearningJavaFX.EventDrivenAndAnimation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DisplayResizableClock extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClockPane clockPane = new ClockPane();
        Label currentTimeLabel = new Label();

        /** Create a handler for animation*/
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clockPane.setCurrentTime();
                currentTimeLabel.setText(clockPane.getHour() + " : " + clockPane.getMinute() + " : " + clockPane.getSecond());
            }
        };

        /** Create an animation for a running clock*/
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();


        // Plance clock and label in border pane
        BorderPane pane = new BorderPane();
        pane.setCenter(clockPane);
        pane.setBottom(currentTimeLabel);
        BorderPane.setAlignment(currentTimeLabel, Pos.CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("DisplayClock");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.widthProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                clockPane.setW(pane.getWidth());
            }
        });

        pane.heightProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                clockPane.setH(pane.getHeight());
            }
        });
    }
}
