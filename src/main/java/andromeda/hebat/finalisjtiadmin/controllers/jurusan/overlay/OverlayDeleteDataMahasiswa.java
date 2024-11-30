package andromeda.hebat.finalisjtiadmin.controllers.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Mahasiswa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OverlayDeleteDataMahasiswa {
    @FXML private VBox overlayDelete;

    @FXML private Button btnConfirm;

    @FXML private Button btnCancel;

    private Mahasiswa mahasiswa;

    public void fillData(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public void confirmDelete() {
        String query = "DELETE FROM USERS.Mahasiswa WHERE nim = ?";

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, mahasiswa.getNim());

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
