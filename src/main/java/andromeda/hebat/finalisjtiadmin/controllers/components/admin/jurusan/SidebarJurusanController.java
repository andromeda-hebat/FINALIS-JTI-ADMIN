package andromeda.hebat.finalisjtiadmin.controllers.components.admin.jurusan;

import andromeda.hebat.finalisjtiadmin.controllers.components.admin.SidebarController;
import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarJurusanController extends SidebarController {
    @FXML private Button btnDashboard;
    @FXML private Button btnKelolaData;
    @FXML private Button btnAdmin;
    @FXML private Button btnMahasiswa;
    @FXML private Button btnTemplateSurat;
    @FXML private Button btnLogAktivitas;

    @FXML
    private void initialize() {
    }

    @FXML
    private void btnDashboardOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/admin/jurusan/dashboard.fxml");
    }

    @FXML
    private void btnAdminOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/admin/jurusan/kelola-admin.fxml");
    }

    @FXML
    private void btnTemplateSuratOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/admin/jurusan/kelola-template-surat.fxml");
    }

    @FXML
    private void btnMahasiswaOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/admin/jurusan/kelola-mahasiswa.fxml");
    }

    @FXML
    private void btnLogAktivitasOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarContainer.getScene(), "/views/pages/admin/jurusan/log-aktivitas.fxml");
    }
}
