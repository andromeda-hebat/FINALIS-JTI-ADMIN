package andromeda.hebat.finalisjtiadmin.controllers.jurusan;

import andromeda.hebat.finalisjtiadmin.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class KelolaTemplateSuratController {

    @FXML
    private Button btnTambahSuratBaru;

    public void btnTambahSuratBaruOnClicked() {
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Tambah Template Surat Baru");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-tambah-template-surat-baru.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 700);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
