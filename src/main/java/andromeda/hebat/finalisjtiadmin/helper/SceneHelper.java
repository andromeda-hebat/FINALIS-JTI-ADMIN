package andromeda.hebat.finalisjtiadmin.helper;

import andromeda.hebat.finalisjtiadmin.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SceneHelper {
    public static void changeScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            stage.setScene(scene);

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

            double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
            double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setScene(scene);

            stage.sizeToScene();
            double stageWidth = stage.getWidth();
            double stageHeight = stage.getHeight();
            stage.setX((screenWidth - stageWidth) / 2);
            stage.setY((screenHeight - stageHeight) / 2);

            if (!stage.isShowing()) {
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
