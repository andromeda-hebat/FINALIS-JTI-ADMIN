package andromeda.hebat.finalisjtiadmin.controllers;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.helper.JsonHelper;
import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import andromeda.hebat.finalisjtiadmin.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private VBox loginScene;

    @FXML
    private TextField inputFieldUserID;

    @FXML
    private PasswordField inputFieldPassword;

    @FXML
    private void enterDashboard() {
        final String userID = inputFieldUserID.getText();
        final String password = inputFieldPassword.getText();
        String fxmlFile = null;

        Admin currentAdmin = UserRepository.checkUser(userID, password);
        if (currentAdmin != null) {
            switch (currentAdmin.getJabatan()) {
                case "Admin Jurusan":
                    System.out.println("Selamat datang admin jurusan!");
                    fxmlFile = "/views/adminjurusan/kelola-data-admin.fxml";
                    break;
                case "Admin Prodi":
                    System.out.println("Selamat datang admin prodi!");
                    fxmlFile = "/views/adminprodi/dashboard.fxml";
                    break;
                case "Admin TA":
                    System.out.println("Selamat datang admin TA!");
                    fxmlFile = "/views/adminta/dashboard.fxml";
                    break;
            }

            Stage mainStage = (Stage) loginScene.getScene().getWindow();
            SceneHelper.changeScene(mainStage, fxmlFile);
        } else {
            System.out.println("Tidak ditemukan username yang cocok dengan data pengguna!");
        }
    }
}
