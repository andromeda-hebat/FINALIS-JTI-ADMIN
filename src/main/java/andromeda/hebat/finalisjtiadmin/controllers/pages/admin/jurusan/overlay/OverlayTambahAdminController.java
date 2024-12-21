package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.models.Admin;
import andromeda.hebat.finalisjtiadmin.repository.AdminRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Base64;

public class OverlayTambahAdminController {
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

        try {
            String response = AdminRepository.insertNewAdmin(new Admin(
                inputIDAdmin.getText(),
                inputFullName.getText(),
                inputPassword.getText(),
                inputEmail.getText(),
                inputPosition.getValue(),
                base64PhotoProfile
            ));
            if (response.equalsIgnoreCase("success")) {
                ((Stage) overlayTambahAdmin.getScene().getWindow()).close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil menambahkan data admin baru!");
                alert.showAndWait();
            } else {
                ((Stage) overlayTambahAdmin.getScene().getWindow()).close();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gagal menambahkan data admin baru!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            ((Stage) overlayTambahAdmin.getScene().getWindow()).close();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal menambahkan admin baru! Terjadi kesalahan pada database!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void onBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cari file foto");
        fileChooser.getExtensionFilters()
                    .addAll(
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
