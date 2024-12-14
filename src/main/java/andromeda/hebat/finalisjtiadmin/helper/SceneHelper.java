package andromeda.hebat.finalisjtiadmin.helper;

import andromeda.hebat.finalisjtiadmin.Main;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneHelper {
    public static void changeScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double screenWidth = screenBounds.getWidth();
            double screenHeight = screenBounds.getHeight();

            Scene scene = new Scene(fxmlLoader.load(), screenWidth, screenHeight);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setMaximized(true);

            if (!stage.isShowing()) {
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void changeScene(Stage stage, String fxmlFile, double width, double height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));

            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            if (!stage.isShowing()) {
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeRootNodeScene(Scene scene, String fxmlFile) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile + ".fxml"));
        try {
            scene.setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
