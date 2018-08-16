package Networking.MultipleThread;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MultiThreadServer extends Application {

    private TextArea textArea = new TextArea();

    private int clientNo = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new ScrollPane(textArea), 450, 200);
        primaryStage.setTitle("MultiThreadServer");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                Platform.runLater(() -> {
                    textArea.appendText("MultipleThreadServer start at " + new Date() + '\n');
                });

                ServerSocket serverSocket = new ServerSocket(8000);
                while (true) {
                    Socket socket = serverSocket.accept();
                    clientNo++;

                    Platform.runLater(()->{
                        textArea.appendText("Starting thread for client " + clientNo + " at " + new Date() + '\n');
                        InetAddress inetAddress = socket.getInetAddress();
                        textArea.appendText("Client " + clientNo + "'s host name is " + inetAddress.getHostAddress() + "\n");
                        textArea.appendText("Client " + clientNo + "'s IP address is " + inetAddress.getHostAddress() + "\n");
                    });

                    // create and start a new thread for the connection
                    new Thread(new HandleAClient(socket)).start();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    class HandleAClient implements Runnable {
        private Socket socket;

        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    double radius = inputFromClient.readDouble();
                    double area = radius * radius * Math.PI;
                    outputToClient.writeDouble(area);
                    Platform.runLater(()->{
                        textArea.appendText("radius received from client: " + radius + "\n");
                        textArea.appendText("Area found: " + area + "\n");
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
