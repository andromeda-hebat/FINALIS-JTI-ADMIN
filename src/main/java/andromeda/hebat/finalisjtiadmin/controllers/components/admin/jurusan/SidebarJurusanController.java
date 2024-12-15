package andromeda.hebat.finalisjtiadmin.controllers.components.admin.jurusan;

import andromeda.hebat.finalisjtiadmin.controllers.components.admin.SidebarController;
import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class SidebarJurusanController extends SidebarController {
    @FXML private VBox sidebarJurusan;
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
        SceneHelper.changeRootNodeScene(sidebarJurusan.getScene(), "/views/pages/admin/jurusan/dashboard.fxml");
    }

    @FXML
    private void btnAdminOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarJurusan.getScene(), "/views/pages/admin/jurusan/kelola-data-admin.fxml");
    }

    @FXML
    private void btnTemplateSuratOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarJurusan.getScene(), "/views/pages/admin/jurusan/kelola-template-surat.fxml");
    }

    @FXML
    private void btnMahasiswaOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarJurusan.getScene(), "/views/pages/admin/jurusan/kelola-data-mahasiswa.fxml");
    }

    @FXML
    private void btnLogAktivitasOnClicked() {
        SceneHelper.changeRootNodeScene(sidebarJurusan.getScene(), "/views/pages/admin/jurusan/log-aktivitas.fxml");
    }
}
