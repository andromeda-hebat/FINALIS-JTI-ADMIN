package andromeda.hebat.finalisjtiadmin.controllers.components;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SidebarProdiController extends SidebarController {
    @FXML private VBox sidebarContainer;
    @FXML private  Button btnDashboard;
    @FXML private Button btnRequestVerification;

    @FXML
    public void initialize() {
    }

    @FXML
    private void btnDashboardOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/adminprodi/dashboard");
    }

    @FXML
    private void btnRequestVerificationOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/adminprodi/daftar-permintaan-verifikasi");
    }
}
