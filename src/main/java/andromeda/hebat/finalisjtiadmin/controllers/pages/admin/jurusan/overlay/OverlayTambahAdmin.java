package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.core.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

public class OverlayTambahAdmin {
    @FXML private VBox overlayTambahAdmin;
    @FXML private TextField inputIDAdmin;
    @FXML private TextField inputFullName;
    @FXML private TextField inputEmail;
    @FXML private ChoiceBox<String> inputPosition;
    @FXML private PasswordField inputPassword;
    @FXML private PasswordField inputConfirmedPassword;
    @FXML private TextField inputFotoProfil;
    @FXML private Button btnSubmitForm;
    private String base64PhotoProfile;

    private ObservableList positionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        positionList.removeAll(positionList);
        positionList.addAll("Admin TA", "Admin Prodi");
        inputPosition.getItems().addAll(positionList);

    }

    public void submitForm() {
        if (!inputPassword.getText().equals(inputConfirmedPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input password dan konfirmasi password tidak sesuai!");
            alert.showAndWait();
            return;
        }

        String query = """
            INSERT INTO USERS.Admin (id_admin, nama_lengkap, password, email, jabatan, foto_profil)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            String hashedPassword = BCrypt.hashpw(inputPassword.getText(), BCrypt.gensalt());
            stmt.setString(1, inputIDAdmin.getText());
            stmt.setString(2, inputFullName.getText());
            stmt.setString(3, hashedPassword);
            stmt.setString(4, inputEmail.getText());
            stmt.setString(5, inputPosition.getValue());
            stmt.setString(6, base64PhotoProfile);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                Stage overlayStage = (Stage) overlayTambahAdmin.getScene().getWindow();
                overlayStage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil menambahkan data admin baru!");
                alert.showAndWait();
            } else {
                Stage overlayStage = (Stage) overlayTambahAdmin.getScene().getWindow();
                overlayStage.close();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gagal menambahkan data admin baru!");
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
            try {
                byte[] fileContent = Files.readAllBytes(selectedFile.toPath());
                base64PhotoProfile = Base64.getEncoder().encodeToString(fileContent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
