package andromeda.hebat.finalisjtiadmin.controllers;

import andromeda.hebat.finalisjtiadmin.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private VBox loginScene;

    @FXML
    private TextField inputFieldUsername;

    @FXML
    private PasswordField inputFieldPassword;

    @FXML
    private void enterDashboard() {
        String username = inputFieldUsername.getText();
        String password = inputFieldPassword.getText();
        String fxmlFile = null;

        switch (username) {
            case "adminjurusan":
                System.out.println(password.equals("jurusan") ? "Admin jurusan valid!" : "Admin jurusan tidak valid!");
                fxmlFile = "/views/adminjurusan/dashboard.fxml";
                break;
            case "adminprodi":
                System.out.println(password.equals("prodi") ? "Admin prodi valid!" : "Admin prodi tidak valid!");
                fxmlFile = "/views/adminprodi/dashboard.fxml";
                break;
            case "adminta":
                System.out.println(password.equals("tugasakhir") ? "Admin TA valid!" : "Admin TA tidak valid!");
                fxmlFile = "/views/adminta/dashboard.fxml";
                break;
            default:
                System.out.println("Tidak ditemukan username yang cocok dengan data pengguna!");
                break;
        }

        Stage mainStage = (Stage) loginScene.getScene().getWindow();
        changeScene(mainStage, fxmlFile);
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
