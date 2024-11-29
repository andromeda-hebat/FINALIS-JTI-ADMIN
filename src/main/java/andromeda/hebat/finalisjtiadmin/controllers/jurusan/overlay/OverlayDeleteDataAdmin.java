package andromeda.hebat.finalisjtiadmin.controllers.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OverlayDeleteDataAdmin {

    @FXML private VBox overlayDelete;

    @FXML private Button btnConfirm;

    @FXML private Button btnCancel;

    private Admin admin;

    public void fillData(Admin admin) {
        this.admin = admin;
    }

    public void confirmDelete() {
        String query = "DELETE FROM USERS.Admin WHERE id_admin = ?";

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, admin.getUserId());

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                Stage overlayStage = (Stage) overlayDelete.getScene().getWindow();
                overlayStage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil menghapus data!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelDelete() {
        ((Stage) overlayDelete.getScene().getWindow()).close();
    }
}
