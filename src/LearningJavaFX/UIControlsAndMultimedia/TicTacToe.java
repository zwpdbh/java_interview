package LearningJavaFX.UIControlsAndMultimedia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    private char whoseTurn = 'X';

    private Cell[][] cells = new Cell[3][3];

    private Label statusLabel = new Label("X's turn to play");

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
                pane.add(cells[i][j], j, i);
            }
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusLabel);

        Scene scene = new Scene(borderPane, 450, 170);
        primaryStage.setTitle("TickTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Determine if the cells are all occupied */
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getToken() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /** Determine if the player with the specified token wins */
    public boolean isWon(char token) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0].getToken() == token && cells[i][1].getToken() == token && cells[i][2].getToken() == token) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (cells[0][j].getToken() == token && cells[1][j].getToken() == token && cells[2][j].getToken() == token) {
                return true;
            }
        }

        if (cells[0][0].getToken() == token && cells[1][1].getToken() == token && cells[2][2].getToken() == token) {
            return true;
        }

        if (cells[0][2].getToken() == token && cells[1][1].getToken() == token && cells[2][0].getToken() == token) {
            return true;
        }

        return false;
    }

    /** An inner class for a cell*/
    public class Cell extends Pane {
        // Token used for this cell
        private  char token = ' ';

        public Cell() {
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> handleMouseClick());
        }


        public char getToken() {
            return token;
        }


        private void setToken(char c) {
            this.token = c;
            if (token == 'X') {
                Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));

                Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
                line2.startYProperty().bind(this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));

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


        /** Handle a mouse click event*/
        private void handleMouseClick() {
            // if the cell is empty and game is not over
            if (token == ' ' && whoseTurn != ' ') {
                setToken(whoseTurn);
            }

            // Check game status
            if (isWon(whoseTurn)) {
                statusLabel.setText(whoseTurn + " won! The game is over");
                whoseTurn = ' ';
            } else if (isFull()) {
                statusLabel.setText("Draw! The game is over");
            } else {
                whoseTurn = (whoseTurn == 'X' ? '0' : 'X');
                statusLabel.setText(whoseTurn + "'s turn");
            }
        }
    }
}
