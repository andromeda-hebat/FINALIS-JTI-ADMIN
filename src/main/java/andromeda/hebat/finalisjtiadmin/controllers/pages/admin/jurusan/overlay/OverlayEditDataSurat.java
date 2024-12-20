package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.models.Surat;
import andromeda.hebat.finalisjtiadmin.core.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OverlayEditDataSurat {
    @FXML private TextField editNamaSurat;
    @FXML private TextField editKeperluan;
    @FXML private TextField editFilePath;
    @FXML private Button btnEdit;

    public Button btnFileChooser;
    public VBox overlayEditDataSurat;
    private Surat surat;

    @FXML
    public void initialize() {
        btnEdit.setOnAction(event -> updateData());
    }

    public void fillData(Surat surat) {
        this.surat = surat;
        editNamaSurat.setText(surat.getNamaSurat());
        editKeperluan.setText(surat.getKeperluan());
        editFilePath.setText(surat.getFileSurat());

    }

    public void updateData() {
        String query = """
                UPDATE TEMP.Surat 
                SET
                    nama_surat = ?,
                    keperluan_surat = ?,
                    file_surat = ?
                """;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, editNamaSurat.getText());
            stmt.setString(2, editKeperluan.getText());
            stmt.setString(3, editFilePath.getText());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                Stage overlayStage = (Stage) overlayEditDataSurat.getScene().getWindow();
                overlayStage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil memperbarui data template surat!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cari file Surat");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            editFilePath.setText(selectedFile.getName());
        }
    }
}
