package andromeda.hebat.finalisjtiadmin.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField inputFieldUsername;

    @FXML
    private PasswordField inputFieldPassword;

    @FXML
    private void enterDashboard() {
        String username = inputFieldUsername.getText();
        String password = inputFieldPassword.getText();

        switch (username) {
            case "adminjurusan":
                System.out.println(password.equals("jurusan") ? "Admin jurusan valid!" : "Admin jurusan tidak valid!");
                break;
            case "adminprodi":
                System.out.println(password.equals("prodi") ? "Admin prodi valid!" : "Admin prodi tidak valid!");
                break;
            case "adminta":
                System.out.println(password.equals("tugasakhir") ? "Admin TA valid!" : "Admin TA tidak valid!");
                break;
            default:
                System.out.println("Tidak ditemukan username yang cocok dengan data pengguna!");
                break;
        }
    }
}
