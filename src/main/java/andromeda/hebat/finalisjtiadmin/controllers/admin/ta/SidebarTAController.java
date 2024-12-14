package andromeda.hebat.finalisjtiadmin.controllers.admin.ta;

import andromeda.hebat.finalisjtiadmin.controllers.admin.general.SidebarController;
import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class SidebarTAController extends SidebarController {
    @FXML private Button btnDashboard;
    @FXML private Button btnRequestVerification;

    @FXML
    private void initialize() {
        addMouseEvents(btnDashboard);
        addMouseEvents(btnRequestVerification);
    }

    private void addMouseEvents(Button button) {
        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
        button.setOnMousePressed(event -> button.setCursor(Cursor.DEFAULT));
        button.setOnMouseReleased(event -> button.setCursor(Cursor.HAND));
    }

    @FXML
    private void btnDashboardOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/adminta/dashboard");
    }

    @FXML
    private void btnRequestVerificationOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/adminta/permintaan-verifikasi");
    }
}
