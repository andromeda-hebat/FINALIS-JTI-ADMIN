package andromeda.hebat.finalisjtiadmin.controllers.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.repository.BerkasRepository;
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
    private TextField inputFilePath;

    @FXML
    private Button btnFileChooser;

    @FXML
    private Button btnTambahkan;

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
        BerkasRepository.addNewTemplateSurat(inputNamaSurat.getText(), inputKeterangan.getText(), inputFilePath.getText());
    }
}
