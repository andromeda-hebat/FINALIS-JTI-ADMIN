package andromeda.hebat.finalisjtiadmin.controllers.jurusan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OverlayTambahTemplateSuratController {

    @FXML
    private TextField inputNamaSurat;

    @FXML
    private TextField inputKeterangan;

    @FXML
    private Button inputFile;

    @FXML
    private Button btnTambahkan;

    @FXML
    private TextField filePathField;

    @FXML
    public void onBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cari file surat");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }
}
