package andromeda.hebat.finalisjtiadmin.helper;

import andromeda.hebat.finalisjtiadmin.Main;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;

public class CSSHelper {
    public static void loadCSS(Node rootNode, String cssFile) {
        Platform.runLater(() -> {
            Scene scene = rootNode.getScene();
            if (scene != null) {
                scene.getStylesheets().add(Main.class.getResource("/css/" + cssFile + ".css").toExternalForm());
            }
        });
    }
}
