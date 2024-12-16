package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Surat;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class OverlayDeleteDataSurat {
    @FXML private VBox overlayDelete;
    @FXML private Button btnConfirm;
    @FXML private Button btnCancel;

    private Surat surat;

    public void fillData(Surat surat) {
        this.surat = surat;
    }

    @FXML
    public void confirmDelete() {
        String query = "DELETE FROM TEMP.Surat WHERE id_surat = ?";

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            String suratId = Integer.toString(surat.getIdSurat());
            stmt.setString(1, suratId);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                Stage overlayStage = (Stage) overlayDelete.getScene().getWindow();
                overlayStage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil menghapus data!");
                alert.setHeaderText("Surat dengan ID " + suratId + " telah dihapus.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelDelete() {
        ((Stage) overlayDelete.getScene().getWindow()).close();
    }
}
