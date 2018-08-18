package Networking.MultiplayerTicTacToe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TicTacToeClient extends Application implements TicTacToeConstants {
    private boolean myTurn = false;

    private char myToken = ' ';
    private char otherToken = ' ';

    // Create and initialize cells
    private Cell[][] cells = new Cell[3][3];

    // create and initialize a title label
    private Label titleLabel = new Label();

    // Create and initialize a status label
    private Label statusLabel = new Label();

    // Indicate selected row and column by the current move
    private int rowSelected;
    private int columnSelect;

    // Input and output streams from/to server
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    // Continue to play?
    private boolean continueToPlay = true;

    // Wait for the player to mark a cell
    private boolean waiting = true;

    private String host = "localhost";

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private void connectToServer() {
        try {
            Socket socket = new Socket(host, 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        // Control game on a separate thread
        new Thread(() -> {
            try {
                int player = fromServer.readInt();

                // player 1 or player 2?
                if (player == PLAYER1) {
                    myToken = 'X';
                    otherToken = '0';
                    Platform.runLater(() -> {
                        titleLabel.setText("Player 1 with token 'X");
                        titleLabel.setText("Waiting for player 2 to join");
                    });

                    // Receive startup notification from the server
                    fromServer.readInt();

                    // The other player has joined
                    Platform.runLater(() -> {
                        statusLabel.setText("Player 2 has joined. I start first");
                    });

                    // it is my turn
                    myTurn = true;
                } else if (player == PLAYER2){
                    myToken = 'O';
                    otherToken = 'X';
                    Platform.runLater(() -> {
                        titleLabel.setText("Player 2 with token '0");
                        statusLabel.setText("Waiting for player 1 to move");
                    });
                }

                // Continue to play
                while (continueToPlay) {
                    if (player == PLAYER1) {
                        waitForPlayerAction();
                        sendMove();
                        receiveInfoFromServer();
                    } else if (player == PLAYER2) {
                        receiveInfoFromServer();
                        waitForPlayerAction();
                        sendMove();
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }


    /**Wait for the player to make a cell*/
    private void waitForPlayerAction() throws InterruptedException {
        while (waiting) {
            Thread.sleep(100);
        }
        waiting = true;
    }

    /**Send this player's move to the server*/
    private void sendMove() throws IOException {
        toServer.writeInt(rowSelected);
        toServer.writeInt(columnSelect);
    }

    /**Receive info from server*/
    private void receiveInfoFromServer() throws IOException {
        int status = fromServer.readInt();

        if (status == PLAYER1_WON) {
            continueToPlay = false;
            if (myToken == 'X') {
                Platform.runLater(() -> {
                    statusLabel.setText("I won! (X)");
                });
            } else if (myToken == '0') {
                Platform.runLater(()->{
                    statusLabel.setText("Player 1 (X) has won");
                });
                receiveMove();
            }
        } else if (status == PLAYER2_WON) {
            // Player 2 won, stop playing
            continueToPlay = false;
            if (myToken == '0') {
                Platform.runLater(()->{
                    statusLabel.setText("I won! (0)");
                });
            } else if (myToken == 'X') {
                Platform.runLater(() -> {
                    statusLabel.setText("Player 2 (0) has won");
                });
                receiveMove();
            }
        } else if (status == DRAW) {
            continueToPlay = false;
            Platform.runLater(() -> {
                statusLabel.setText("Game is over, DRAW");
            });

            if (myToken == '0') {
                receiveMove();
            }
        } else {
            receiveMove();
            Platform.runLater(() -> {
                statusLabel.setText("My turn");
            });
            myTurn = true;
        }
    }

    private void receiveMove() throws IOException {
        int row = fromServer.readInt();
        int column = fromServer.readInt();
        Platform.runLater(() -> {
            cells[row][column].setToken(otherToken);
        });
    }

    public class Cell extends Pane {
        private int row;
        private int column;

        private char token = ' ';

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
            this.setPrefSize(2000, 2000);
            setStyle("-fx-border-color: black");
            this.setOnMouseClicked(e -> handleMouseClick());
        }

        public char getToken() {
            return this.token;
        }

        public void setToken(char c) {
            token = c;
            repaint();
        }

        protected void repaint() {
            if (token == 'X') {
                Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
                line2.startYProperty().bind(this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));

                // Add the lines to the pane
                this.getChildren().addAll(line1, line2);
            } else if (token == '0') {
                Ellipse ellipse = new Ellipse(
                        this.getWidth() / 2,
                        this.getHeight() / 2,
                        this.getWidth() / 2 - 10,
                        this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.BLACK);
                ellipse.setFill(Color.WHITE);

                getChildren().add(ellipse);
            }
        }

        private void handleMouseClick() {
            if (token == ' ' && myTurn) {
                setToken(myToken);
                myTurn = false;
                rowSelected = row;
                columnSelect = column;
                statusLabel.setText("Waiting for the other player to move");
                waiting = false;
            }
        }
    }
}
