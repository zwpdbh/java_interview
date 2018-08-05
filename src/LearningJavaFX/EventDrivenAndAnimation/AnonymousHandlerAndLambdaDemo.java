package LearningJavaFX.EventDrivenAndAnimation;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class AnonymousHandlerAndLambdaDemo extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btNew = new Button("New");
        Button btOpen = new Button("Open");
        Button btSave = new Button("Save");
        Button btPrint = new Button("Print");

        hBox.getChildren().addAll(btNew, btOpen, btSave, btPrint);

        // handle event by defining inner class
        btNew.setOnAction(new ActionHandlerForNew());

        // by defining anonymous inner class
        btOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Process Open");
            }
        });

        // Lambda with type
        btSave.setOnAction((ActionEvent e) -> {
            System.out.println("Process Save");
        });

        // THe parentheses can be omitted if there is only one parameter without an explicit data type.
        btPrint.setOnAction(event -> {
            System.out.println("Process Print");
        });

        Scene scene = new Scene(hBox, 400, 50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class ActionHandlerForNew implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Process New");
        }
    }
}
