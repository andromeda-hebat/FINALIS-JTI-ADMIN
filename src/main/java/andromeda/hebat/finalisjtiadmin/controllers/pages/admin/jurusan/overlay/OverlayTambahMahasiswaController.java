package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.models.Mahasiswa;
import andromeda.hebat.finalisjtiadmin.repository.MahasiswaRepository;
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

public class OverlayTambahMahasiswaController {
    @FXML private VBox overlayTambahMahasiswa;
    @FXML private TextField inputNama;
    @FXML private TextField inputNim;
    @FXML private TextField inputJurusan;
    @FXML private ChoiceBox<String> inputProdi;
    @FXML private TextField inputEmail;
    @FXML private PasswordField inputPassword;
    @FXML private TextField inputTahunMasuk;
    @FXML private TextField inputFotoProfil;
    @FXML private Button btnTambahkan;
    private String base64PhotoProfile;

    private Mahasiswa mahasiswa;
    private ObservableList prodiList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        prodiList.removeAll(prodiList);
        prodiList.addAll("D4 Teknik Informatika", "D4 Sistem Informasi Bisnis", "D2 Pengembangan Perangkat Lunak Situs");
        inputProdi.getItems().addAll(prodiList);
    }

    public void fillData(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
        inputNim.setText(mahasiswa.getNim());
        inputNama.setText(mahasiswa.getNama());
        inputEmail.setText(mahasiswa.getEmail());
        inputJurusan.setText(mahasiswa.getJurusan());
        inputProdi.setValue(mahasiswa.getProdi());
        inputPassword.setText(mahasiswa.getPassword());
    }

    @FXML
    public void submitForm() {
        if (inputNim.getText().isEmpty() || inputNama.getText().isEmpty() || inputEmail.getText().isEmpty() ||
            inputJurusan.getText().isEmpty() || inputProdi.getValue().isEmpty() || inputPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Perhatian");
            alert.setHeaderText("Semua field harus diisi!");
            alert.showAndWait();
            return;
        }

        try {
            String response = MahasiswaRepository.insertNewMahasiswa(new Mahasiswa(
                inputNim.getText(),
                inputNama.getText(),
                inputPassword.getText(),
                inputEmail.getText(),
                inputJurusan.getText(),
                inputProdi.getValue(),
                inputTahunMasuk.getText(),
                base64PhotoProfile
            ));

            if (response.equalsIgnoreCase("success")) {
                ((Stage) overlayTambahMahasiswa.getScene().getWindow()).close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil menambahkan data mahasiswa baru!");
                alert.showAndWait();
            } else {
                ((Stage) overlayTambahMahasiswa.getScene().getWindow()).close();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gagal menambahkan data mahasiswa baru!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            ((Stage) overlayTambahMahasiswa.getScene().getWindow()).close();
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