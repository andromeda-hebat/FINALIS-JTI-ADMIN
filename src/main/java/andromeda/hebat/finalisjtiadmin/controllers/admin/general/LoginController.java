package andromeda.hebat.finalisjtiadmin.controllers.admin.general;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import andromeda.hebat.finalisjtiadmin.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
                    fxmlFile = "/views/pages/adminjurusan/kelola-data-admin.fxml";
                    break;
                case "Admin Prodi":
                    System.out.println("Selamat datang admin prodi!");
                    fxmlFile = "/views/pages/adminprodi/dashboard.fxml";
                    break;
                case "Admin TA":
                    System.out.println("Selamat datang admin TA!");
                    fxmlFile = "/views/pages/adminta/dashboard.fxml";
                    break;
            }

            Stage mainStage = (Stage) loginScene.getScene().getWindow();
            SceneHelper.changeScene(mainStage, fxmlFile);
        } else {
            System.out.println("Tidak ditemukan username yang cocok dengan data pengguna!");
        }
    }
}
