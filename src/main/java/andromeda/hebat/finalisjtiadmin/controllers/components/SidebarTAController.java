package andromeda.hebat.finalisjtiadmin.controllers.components;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarTAController extends SidebarController {
    @FXML private Button btnDashboard;
    @FXML private Button btnRequestVerification;

    @FXML
    private void initialize() {
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
