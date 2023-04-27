package Testing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.awt.event.ActionListener;

import static javafx.application.Application.launch;

public class View3DTesting extends Application {

    public static void main(String[] args) {
        launch(args);






    }
    public void start(Stage primaryStage) {

        Box box = new Box(100,100,100);

        Group root = new Group(box);

        Scene mainscene = new Scene(root, 400,400, true);

        PerspectiveCamera camera = new PerspectiveCamera();
        mainscene.setCamera(camera);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    camera.translateYProperty().set(camera.getTranslateY() + 100);
                    break;
                case S:
                    camera.translateYProperty().set(camera.getTranslateY() - 100);
                    break;
                case A:
                    camera.translateXProperty().set(camera.getTranslateX() + 100);
                case D:
                    camera.translateXProperty().set(camera.getTranslateX() - 100);
            }
        });

        primaryStage.setScene(mainscene);
        primaryStage.setTitle("Testing");
        primaryStage.show();

//        Movement




    }

}
