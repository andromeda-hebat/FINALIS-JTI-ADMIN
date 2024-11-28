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
            overlay.setTitle("Tambah admin baru");

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
            overlay.setTitle("Edit data admin");

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
            overlay.setTitle("Hapus data admin");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-hapus-data-admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 450);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
