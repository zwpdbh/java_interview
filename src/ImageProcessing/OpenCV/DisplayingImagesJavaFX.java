package ImageProcessing.OpenCV;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.image.WritableImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**first of all, read an image using the imread() method and convert it into BufferedImage.
 *
 * Then, convert the BufferedImage to WritableImage, as shown below:
 * WritableImage writableImage = SwingFXUtils.toFXImage(bufImage, null);
 *
 * At last, Pass this WritableImage object to the constructor of the ImageView class:
 * ImageView imageView = new ImageView(writableImage);*/
public class DisplayingImagesJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        WritableImage writableImage = loadImage();

        // Setting the image view
        ImageView imageView = new ImageView(writableImage);

        // setting the position of the image;
        imageView.setX(50);
        imageView.setY(25);

        // setting the fit height and width of the imageView
        imageView.setFitHeight(400);
        imageView.setFitWidth(600);

        // setting the preserve ration of the image view
        imageView.setPreserveRatio(true);

        // Creating a Group object
        Group root = new Group(imageView);

        // Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        // setting the title to the stage
        primaryStage.setTitle("Loading an image");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public WritableImage loadImage() throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String imgFile = "/Users/zw/code/Java_Projects/Java_Interview/src/resources/test_img.jpg";
        Mat image = Imgcodecs.imread(imgFile);

        // Encoding the image
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", image, matOfByte);

        // Storing the encoded mat in a byte array
        byte byteArray[] = matOfByte.toArray();

        // Displaying the image
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        BufferedImage bufferedImage = ImageIO.read(inputStream);

        System.out.println("Image Loaded");

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    public static void main(String args[]) {
        launch(args);
    }
}
