package andromeda.hebat.finalisjtiadmin.controllers;

import andromeda.hebat.finalisjtiadmin.Main;
import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private VBox loginScene;

    @FXML
    private TextField inputFieldUsername;

    @FXML
    private PasswordField inputFieldPassword;

    @FXML
    private void enterDashboard() {
        System.out.println("hai!");
        final String username = inputFieldUsername.getText();
        final String password = inputFieldPassword.getText();
        String fxmlFile = null;

        Admin currentAdmin = checkUser(username, password);
        if (currentAdmin != null) {
            switch (currentAdmin.getRole()) {
                case "jurusan":
                    System.out.println("Selamat datang admin jurusan!");
                    fxmlFile = "/views/adminjurusan/dashboard.fxml";
                    break;
                case "prodi":
                    System.out.println("Selamat datang admin prodi!");
                    fxmlFile = "/views/adminprodi/dashboard.fxml";
                    break;
                case "ta":
                    System.out.println("Selamat datang admin TA!");
                    fxmlFile = "/views/adminta/dashboard.fxml";
                    break;
            }
        } else {
            System.out.println("Tidak ditemukan username yang cocok dengan data pengguna!");
        }

        Stage mainStage = (Stage) loginScene.getScene().getWindow();
        changeScene(mainStage, fxmlFile);
    }

    private Admin checkUser(String username, String password) {
        String query = "SELECT * FROM Admin WHERE name = ? AND password = ?";
        Admin admin = null;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                admin = new Admin();
                admin.setUserId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setPassword(rs.getString("password"));
                admin.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return admin;
    }

    public void changeScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
