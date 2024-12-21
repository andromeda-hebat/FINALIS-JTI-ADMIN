package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OverlayEditDataAdmin {
    @FXML private VBox overlayEditDataAdmin;
    @FXML private TextField inputIDAdmin;
    @FXML private TextField inputFullName;
    @FXML private TextField inputEmail;
    @FXML private ChoiceBox<String> inputPosition;
    @FXML private PasswordField inputPassword;
    @FXML private PasswordField inputConfirmedPassword;
    @FXML private TextField inputFotoProfil;
    @FXML private Button btnSubmitForm;

    private String hashedPassword;
    private String base64PhotoProfile;

    private ObservableList positionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        positionList.removeAll(positionList);
        positionList.addAll("Admin TA", "Admin Prodi");
        inputPosition.getItems().addAll(positionList);
    }

    public void fillData(Admin admin) {
        inputIDAdmin.setText(admin.getUserId());
        inputFullName.setText(admin.getName());
        inputEmail.setText(admin.getEmail());
        inputPosition.setValue(admin.getJabatan().getJenisAdminStr());
        hashedPassword = admin.getPassword();
        getPhotoProfile();
    }

    private void getPhotoProfile() {
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
                SELECT foto_profil
                FROM USERS.Admin
                WHERE id_admin = ?
                """)) {
            stmt.setString(1, inputIDAdmin.getText());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.base64PhotoProfile = rs.getString("foto_profil");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        if (!inputPassword.getText().equals(inputConfirmedPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input password dan konfirmasi password tidak sesuai!");
            alert.showAndWait();
            return;
        }

        String query = """
            UPDATE USERS.Admin
            SET 
                nama_lengkap = ?,
                password = ?,
                email = ?,
                jabatan = ?,
                foto_profil = ?
            WHERE id_admin = ?;
        """;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, inputFullName.getText());
            stmt.setString(2, inputPassword.getText());
            stmt.setString(3, inputEmail.getText());
            stmt.setString(4, inputPosition.getValue());
            stmt.setString(5, inputFotoProfil.getText());
            stmt.setString(6, inputIDAdmin.getText());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                Stage overlayStage = (Stage) overlayEditDataAdmin.getScene().getWindow();
                overlayStage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil memperbarui data admin!");
                alert.showAndWait();
            } else {
                Stage overlayStage = (Stage) overlayEditDataAdmin.getScene().getWindow();
                overlayStage.close();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gagal memperbarui data admin!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cari file foto");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            inputFotoProfil.setText(selectedFile.getName());
        }
    }
}
