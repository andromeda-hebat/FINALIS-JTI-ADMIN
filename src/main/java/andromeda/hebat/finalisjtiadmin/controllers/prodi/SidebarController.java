package andromeda.hebat.finalisjtiadmin.controllers.prodi;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class SidebarController {

    private Stage mainStage;

    @FXML private VBox sidebarContainer;

    @FXML private  Button btnDashboard;

    @FXML private Button btnRequestVerification;

    @FXML
    public void initialize() {
        List<Button> buttons = List.of(
                btnDashboard,
                btnRequestVerification
        );

        for (Button button : buttons) {
            addMouseEvents(button);
        }
    }

    private void addMouseEvents(Button button) {
        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
        button.setOnMousePressed(event -> button.setCursor(Cursor.DEFAULT));
        button.setOnMouseReleased(event -> button.setCursor(Cursor.HAND));
    }

    @FXML
    public void btnDashboardOnClicked() {
        Stage mainStage = (Stage) sidebarContainer.getScene().getWindow();
        SceneHelper.changeScene(mainStage, "/views/adminprodi/dashboard.fxml");
    }

    @FXML
    public void btnRequestVerificationOnClicked() {
        mainStage = (Stage) sidebarContainer.getScene().getWindow();
        SceneHelper.changeScene(mainStage, "/views/adminprodi/permintaan-verif-seluruh.fxml");
    }
}
