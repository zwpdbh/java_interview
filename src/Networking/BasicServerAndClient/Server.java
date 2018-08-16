package Networking.BasicServerAndClient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Server extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextArea textArea = new TextArea();

        Scene scene = new Scene(new ScrollPane(textArea), 450, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        /**Need to run it on a different thread other than the JavaFX one*/
        new Thread(() -> {
            try {
                /**Everything about updating UI need to be done on this specific thread*/
                Platform.runLater(() -> textArea.appendText("Server started at " + new Date() + '\n'));

                ServerSocket serverSocket = new ServerSocket(8000);
                Socket socket = serverSocket.accept();
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    double radius = inputFromClient.readDouble();
                    double area = radius * radius * Math.PI;

                    outputToClient.writeDouble(area);

                    /**Everything about updating UI need to be done on this specific thread*/
                    Platform.runLater(() -> {
                        textArea.appendText("Radius received from client: " + radius + '\n');
                        textArea.appendText("Area is: " + area + '\n');
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}