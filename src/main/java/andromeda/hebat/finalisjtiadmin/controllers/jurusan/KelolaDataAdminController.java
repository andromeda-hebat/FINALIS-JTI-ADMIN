package andromeda.hebat.finalisjtiadmin.controllers.jurusan;

import andromeda.hebat.finalisjtiadmin.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class KelolaDataAdminController {
    @FXML
    private Button btnTambahkan;

    public void openOverlayTambahAdmin(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Tambah Template Surat Baru");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-tambah-admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openOverlayEditAdmin(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Edit Data Admin");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-edit-data-admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openOverlayDeleteAdmin(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Apakah Anda ingin Menghapus data ini");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-hapus-data-admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 400);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
