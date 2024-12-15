package andromeda.hebat.finalisjtiadmin.controllers.components.admin.ta;

import andromeda.hebat.finalisjtiadmin.controllers.components.admin.SidebarController;
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
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/admin/ta/dashboard");
    }

    @FXML
    private void btnRequestVerificationOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/admin/ta/daftar-permintaan-verifikasi");
    }
}
