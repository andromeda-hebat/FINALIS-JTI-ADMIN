package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.models.Admin;
import andromeda.hebat.finalisjtiadmin.repository.AdminRepository;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OverlayDeleteAdminController {
    @FXML private VBox overlayDelete;
    @FXML private Button btnConfirm;
    @FXML private Button btnCancel;
    private ObservableList<Admin> adminList;

    private Admin admin;

    public void fillData(Admin admin, ObservableList<Admin> adminList) {
        this.admin = admin;
        this.adminList = adminList;
    }

    public void confirmDelete() {
        String response = AdminRepository.deleteAdmin(this.admin.getUserId());

        if (response.equalsIgnoreCase("success")) {
            adminList.remove(this.admin);
            ((Stage) overlayDelete.getScene().getWindow()).close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Berhasil menghapus data!");
            alert.showAndWait();
        } else {
            ((Stage) overlayDelete.getScene().getWindow()).close();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gagal menghapus data!");
            alert.showAndWait();
        }
    }

    public void cancelDelete() {
        ((Stage) overlayDelete.getScene().getWindow()).close();
    }
}
