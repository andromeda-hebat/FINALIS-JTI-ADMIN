package andromeda.hebat.finalisjtiadmin.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button dataBtn;

    @FXML
    public void initialize() {
        dataBtn.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/data.fxml"));
            try {
                Parent newView = loader.load();

                Stage currentStage = (Stage) dataBtn.getScene().getWindow();
                currentStage.setScene(new Scene(newView));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
