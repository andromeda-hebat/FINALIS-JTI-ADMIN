package andromeda.hebat.finalisjtiadmin.controllers.jurusan;

import andromeda.hebat.finalisjtiadmin.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class KelolaDataMahasiswaController {
    @FXML
    private Button btnTambahkanMhs;

    public void openOverlayTambahMahasiswa(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Tambah Data Mahasiswa");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-tambah-data-mahasiswa.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openOverlayEditMahasiswa(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Edit Data mahasiswa");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-edit-data-mahasiswa.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openOverlayHapusMahasiswa(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Hapus Data Mahasiswa");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-hapus-data-mahasiswa.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 450);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
