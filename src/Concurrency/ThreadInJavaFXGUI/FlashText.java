package Concurrency.ThreadInJavaFXGUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FlashText extends Application {
    private String text = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        Label textLabel = new Label("Programming is fun");
        pane.getChildren().add(textLabel);

        /** Use lambda */
        new Thread(() -> {
            try {
                while (true) {
                    if (textLabel.getText().trim().length() == 0) {
                        text = "Welcome";
                    } else {
                        text = "";
                    }

                    Platform.runLater(() -> textLabel.setText(text));
                    Thread.sleep(200);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }).start();

        // use inner class
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (true) {
//                        if (textLabel.getText().trim().length() == 0) {
//                            text = "Welcome";
//                        } else {
//                            text = "";
//                        }
//
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                textLabel.setText(text);
//                            }
//                        });
//
//                        Thread.sleep(200);
//                    }
//                } catch (InterruptedException ex) {
//                    System.out.println(ex.toString());
//                }
//            }
//        }).start();

        Scene scene = new Scene(pane, 200, 50);
        primaryStage.setTitle("FlashText");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
