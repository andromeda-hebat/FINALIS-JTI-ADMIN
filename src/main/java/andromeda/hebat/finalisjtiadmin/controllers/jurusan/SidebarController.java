package andromeda.hebat.finalisjtiadmin.controllers.jurusan;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class SidebarController {

    @FXML private VBox sidebarJurusan;

    @FXML private Button btnKelolaData;

    @FXML private Button btnAdmin;

    @FXML private Button btnMahasiswa;

    @FXML private Button btnTemplateSurat;

    @FXML private Button btnLogAktivitas;

    @FXML private Button btnLogout;

    @FXML
    public void initialize() {
        List<Button> buttons = List.of(
                btnKelolaData,
                btnAdmin,
                btnMahasiswa,
                btnTemplateSurat,
                btnLogAktivitas,
                btnLogout
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
    public void btnAdminOnClicked() {
        Stage mainStage = (Stage) sidebarJurusan.getScene().getWindow();
        SceneHelper.changeScene(mainStage, "/views/adminjurusan/kelola-data-admin.fxml");
    }

    @FXML
    public void btnTemplateSuratOnClicked() {
        Stage mainStage = (Stage) sidebarJurusan.getScene().getWindow();
        SceneHelper.changeScene(mainStage, "/views/adminjurusan/kelola-template-surat.fxml");
    }

    @FXML
    public void btnMahasiswaOnClkicked() {
        Stage mainStage = (Stage) sidebarJurusan.getScene().getWindow();
        SceneHelper.changeScene(mainStage, "/views/adminjurusan/kelola-data-mahasiswa.fxml");
    }
}
