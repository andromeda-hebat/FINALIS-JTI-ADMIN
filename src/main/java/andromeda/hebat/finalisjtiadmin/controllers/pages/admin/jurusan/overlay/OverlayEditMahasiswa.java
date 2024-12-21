package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Mahasiswa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OverlayEditMahasiswa {
    @FXML private VBox overlayEditDataMahasiswa;
    @FXML private TextField editNama;
    @FXML private TextField editNim;
    @FXML private TextField editJurusan;
    @FXML private ChoiceBox<String> inputProdi;
    @FXML private TextField editEmail;
    @FXML private PasswordField editPassword;
    @FXML private Button btnEdit;
    @FXML private TextField editTahunMasuk;
    @FXML private TextField editFotoProfil;

    private Mahasiswa mahasiswa;
    private String hashedPassword;
    private String base64PhotoProfile;

   
    @FXML
    public void initialize() {

    }


    public void fillData(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
        editNim.setText(mahasiswa.getNim());
        editNama.setText(mahasiswa.getNama());
        editEmail.setText(mahasiswa.getEmail());
        editJurusan.setText(mahasiswa.getJurusan());
        inputProdi.setValue(mahasiswa.getProdi());
        hashedPassword = mahasiswa.getPassword();
        editTahunMasuk.setText(mahasiswa.getTahunAngkatan());
        getPhotoProfile();
    }

    private void getPhotoProfile() {
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
                SELECT foto_profil
                FROM USERS.Mahasiswa
                WHERE nim = ?
                """)) {
            stmt.setString(1, editNim.getText());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                base64PhotoProfile = rs.getString("foto_profil");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        String query = """
            UPDATE USERS.mahasiswa
            SET 
                nama_lengkap = ?,
                password = ?,
                email = ?,
                jurusan = ?,
                prodi = ?,
                tahun_angkatan = ?,
                foto_profil = ?
            WHERE nim = ?;
        """;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, editNama.getText());
            stmt.setString(2, editPassword.getText());
            stmt.setString(3, editEmail.getText());
            stmt.setString(4, editJurusan.getText());
            stmt.setString(5, inputProdi.getValue());
            stmt.setString(6, editNim.getText());
            stmt.setString(7, editTahunMasuk.getText());
            stmt.setString(8, editFotoProfil.getText());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                Stage overlayStage = (Stage) overlayEditDataMahasiswa.getScene().getWindow();
                overlayStage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil memperbarui data mahasiswa!");
                alert.showAndWait();
            } else {
                Stage overlayStage = (Stage) overlayEditDataMahasiswa.getScene().getWindow();
                overlayStage.close();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Berhasil memperbarui data mahasiswa!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cari foto mahasiswa");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            editFotoProfil.setText(selectedFile.getName());
        }
    }
}
