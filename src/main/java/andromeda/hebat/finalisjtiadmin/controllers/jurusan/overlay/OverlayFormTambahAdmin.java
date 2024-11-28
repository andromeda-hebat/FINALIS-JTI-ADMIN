package andromeda.hebat.finalisjtiadmin.controllers.jurusan.overlay;

import andromeda.hebat.finalisjtiadmin.core.Database;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OverlayFormTambahAdmin {

    @FXML
    TextField inputIDAdmin;

    @FXML
    TextField inputFullName;

    @FXML
    TextField inputEmail;

    @FXML
    ChoiceBox<String> inputPosition;

    @FXML
    PasswordField inputPassword;

    @FXML
    PasswordField inputConfirmedPassword;

    @FXML
    Button btnSubmitForm;

    ObservableList positionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        positionList.removeAll(positionList);
        positionList.addAll("Admin TA", "Admin Prodi", "Admin Jurusan");
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
            INSERT INTO USERS.Admin (id_admin, nama_lengkap, password, email, jabatan)
            VALUES (?, ?, ?, ?, ?);
        """;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, inputIDAdmin.getText());
            stmt.setString(2, inputFullName.getText());
            stmt.setString(3, inputPassword.getText());
            stmt.setString(4, inputEmail.getText());
            stmt.setString(5, inputPosition.getValue());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Berhasil menambahkan data admin baru!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
