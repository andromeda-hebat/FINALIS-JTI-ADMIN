package andromeda.hebat.finalisjtiadmin.controllers.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Mahasiswa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OverlayTambahDataMahasiswa {

    @FXML private VBox overlayTambahMahasiswa;

    @FXML private TextField inputNama;

    @FXML private TextField inputNim;

    @FXML private TextField inputJurusan;

    @FXML private TextField inputProdi;

    @FXML private TextField inputEmail;

    @FXML private PasswordField inputPassword;

    @FXML private Button btnTambahkan;

    private Mahasiswa mahasiswa;

    @FXML
    public void initialize() {
        btnTambahkan.setOnAction(event -> handleBtnTambahkan());
    }

    public void fillData(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
        inputNim.setText(mahasiswa.getNim());
        inputNama.setText(mahasiswa.getNama());
        inputEmail.setText(mahasiswa.getEmail());
        inputJurusan.setText(mahasiswa.getJurusan());
        inputProdi.setText(mahasiswa.getProdi());
        inputPassword.setText(mahasiswa.getPassword());
    }

    @FXML
    public void handleBtnTambahkan() {
        if (inputNim.getText().isEmpty() || inputNama.getText().isEmpty() || inputEmail.getText().isEmpty() ||
            inputJurusan.getText().isEmpty() || inputProdi.getText().isEmpty() || inputPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Perhatian");
            alert.setHeaderText("Semua field harus diisi!");
            alert.showAndWait();
            return;
        }

        String query = """
            INSERT INTO mahasiswa (nim, nama, password, email, jurusan, prodi)
            VALUES (?, ?, ?, ?, ?, ?);
        """;
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
          
            stmt.setString(1, inputNim.getText());
            stmt.setString(2, inputNama.getText());
            stmt.setString(3, inputPassword.getText());
            stmt.setString(4, inputEmail.getText());
            stmt.setString(5, inputJurusan.getText());
            stmt.setString(6, inputProdi.getText());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
             
                Stage overlayStage = (Stage) overlayTambahMahasiswa.getScene().getWindow();
                overlayStage.close();
                
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil");
                alert.setHeaderText("Data mahasiswa berhasil ditambahkan!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Terjadi kesalahan saat menambahkan data mahasiswa.");
            alert.showAndWait();
        }
      
    }
}