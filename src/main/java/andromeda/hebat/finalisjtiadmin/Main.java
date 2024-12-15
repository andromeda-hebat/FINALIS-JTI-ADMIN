package andromeda.hebat.finalisjtiadmin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        loadFonts();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/general/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        scene.getStylesheets().add(getClass().getResource("/css/global.css").toExternalForm());
        stage.setTitle("FINALIS JTI");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResource("/icons/finalis-jti.png").toExternalForm()));
        stage.show();
    }

    private void loadFonts() {
        Font.loadFont(getClass().getResource("/fonts/Reddit_Sans/RedditSans-Regular.ttf").toExternalForm(), 16);
        Font.loadFonts(getClass().getResource("/fonts/Reddit_Sans/RedditSans-Black.ttf").toExternalForm(), 16);
        Font.loadFonts(getClass().getResource("/fonts/Reddit_Sans/RedditSans-Bold.ttf").toExternalForm(), 16);
        Font.loadFonts(getClass().getResource("/fonts/Reddit_Sans/RedditSans-Italic.ttf").toExternalForm(), 16);
    }

    public static void main(String[] args) {
        launch();
    }
}