package andromeda.hebat.finalisjtiadmin.controllers.admin.jurusan.overlay;

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

    @FXML
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
            } else {
                // Handle case where no rows were deleted
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Penghapusan Gagal");
                alert.setHeaderText("Data tidak ditemukan");
                alert.setContentText("Mahasiswa dengan NIM tersebut tidak ada.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void cancelDelete() {
        if (overlayDelete == null) {
            System.out.println("overlayDelete is null!");
            return;
        }

        ((Stage) overlayDelete.getScene().getWindow()).close();
    }
}
