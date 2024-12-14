package andromeda.hebat.finalisjtiadmin.controllers.jurusan;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

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
        SceneHelper.changeScene(mainStage, "/views/pages/adminjurusan/kelola-data-admin.fxml");
    }

    @FXML
    public void btnTemplateSuratOnClicked() {
        Stage mainStage = (Stage) sidebarJurusan.getScene().getWindow();
        SceneHelper.changeScene(mainStage, "/views/pages/adminjurusan/kelola-template-surat.fxml");
    }

    @FXML
    public void btnMahasiswaOnClicked() {
        Stage mainStage = (Stage) sidebarJurusan.getScene().getWindow();
        SceneHelper.changeScene(mainStage, "/views/pages/adminjurusan/kelola-data-mahasiswa.fxml");
    }

    @FXML
    public void btnLogoutOnClicked() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Konfirmasi keluar");
        confirmationAlert.setHeaderText("Konfirmasi untuk keluar akun");
        confirmationAlert.setContentText("Apakah anda yakin untuk keluar ?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage mainStage = (Stage) sidebarJurusan.getScene().getWindow();
            SceneHelper.changeScene(mainStage, "/views/pages/general/login.fxml", 600, 400);
        }
    }

    @FXML
    public void btnLogAktivitasOnClicked() {
        Stage mainStage = (Stage) sidebarJurusan.getScene().getWindow();
        SceneHelper.changeScene(mainStage, "/views/adminjurusan/log-aktivitas.fxml");
    }
}
