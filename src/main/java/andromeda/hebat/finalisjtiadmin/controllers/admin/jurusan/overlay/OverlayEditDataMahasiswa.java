package andromeda.hebat.finalisjtiadmin.controllers.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Mahasiswa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OverlayEditDataMahasiswa {

    public Button btnFileChooser;
    @FXML private VBox overlayEditDataMahasiswa;

    @FXML private TextField editNama;

    @FXML private TextField editNim;

    @FXML private TextField editJurusan;

    @FXML private TextField editProdi;

    @FXML private TextField editEmail;

    @FXML private PasswordField editPassword;

    @FXML private Button btnEdit;

    @FXML private TextField editTahunMasuk;

    @FXML private TextField editFotoProfil;

    private Mahasiswa mahasiswa;

   
    @FXML
    public void initialize() {
        btnEdit.setOnAction(event -> updateData());
    }


    public void fillData(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
        editNim.setText(mahasiswa.getNim());
        editNama.setText(mahasiswa.getNama());
        editEmail.setText(mahasiswa.getEmail());
        editJurusan.setText(mahasiswa.getJurusan());
        editProdi.setText(mahasiswa.getProdi());
        editPassword.setText(mahasiswa.getPassword());
        editTahunMasuk.setText(mahasiswa.getTahunAngkatan());
        editFotoProfil.setText(mahasiswa.getFotoProfil());
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
            stmt.setString(5, editProdi.getText());
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
