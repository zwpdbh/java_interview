package LearningJavaFX;

import javafx.application.Application;
import javafx.stage.Stage;

/**Application shows the order of initialization of JavaFX application*/
public class Test extends Application {
    /** invoked at 2nd*/
    public Test() {
        System.out.println("Test constructor is invoked");
    }

    /** invoked at 3rd */
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Start method is invoked");
    }

    /** invoked at 1st */
    public static void main(String[] args) {
        System.out.println("launch application");
        Application.launch(args);
    }
}
