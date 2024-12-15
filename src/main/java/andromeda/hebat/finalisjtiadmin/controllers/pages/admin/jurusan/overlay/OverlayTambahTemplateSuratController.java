package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.repository.BerkasRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OverlayTambahTemplateSuratController {

    @FXML private VBox mainContainer;

    @FXML private TextField inputNamaSurat;

    @FXML private TextField inputKeterangan;

    @FXML private TextField inputFilePath;

    @FXML private Button btnFileChooser;

    @FXML private Button btnTambahkan;

    @FXML
    public void onBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cari file surat");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            inputFilePath.setText(selectedFile.getName());
        }
    }

    @FXML
    public void btnTambahkanOnClicked() {
        String result = BerkasRepository.addNewTemplateSurat(inputNamaSurat.getText(), inputKeterangan.getText(), inputFilePath.getText());
        if (result.equalsIgnoreCase("success")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Berhasil menambahkan data template surat baru!");
            alert.setContentText("Sukses");
            alert.showAndWait();
        } else if (result.equalsIgnoreCase("failed")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gagal menambahkan data template surat baru!");
            alert.setContentText("Gagal!");
            alert.showAndWait();
        }

        Stage overlayStage = (Stage) mainContainer.getScene().getWindow();
        overlayStage.close();
    }
}
