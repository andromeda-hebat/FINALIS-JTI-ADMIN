package andromeda.hebat.finalisjtiadmin.controllers.pages.general;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import andromeda.hebat.finalisjtiadmin.repository.UserRepository;
import andromeda.hebat.finalisjtiadmin.session.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {
    @FXML private VBox loginScene;
    @FXML private TextField inputFieldUserID;
    @FXML private PasswordField inputFieldPassword;

    @FXML
    private void enterDashboard() {
        final String userID = inputFieldUserID.getText();
        final String password = inputFieldPassword.getText();
        String fxmlFile = null;

        Admin currentAdmin = UserRepository.getUserByIDAndPassword(userID, password);
        if (currentAdmin != null) {
            switch (currentAdmin.getJabatan()) {
                case ADMIN_JURUSAN:
                    System.out.println("Selamat datang admin jurusan!");
                    fxmlFile = "/views/pages/admin/jurusan/dashboard.fxml";
                    break;
                case ADMIN_PRODI:
                    System.out.println("Selamat datang admin prodi!");
                    fxmlFile = "/views/pages/admin/prodi/dashboard.fxml";
                    break;
                case ADMIN_TA:
                    System.out.println("Selamat datang admin TA!");
                    fxmlFile = "/views/pages/admin/ta/dashboard.fxml";
                    break;
            }

            UserSession.getInstance().setAdmin(currentAdmin);

            ((Stage) loginScene.getScene().getWindow()).close();
            Stage mainStage = new Stage();
            mainStage.setTitle("FINALIS JTI");
            mainStage.getIcons().add(new Image(getClass().getResource("/icons/finalis-jti.png").toExternalForm()));

            SceneHelper.changeScene(mainStage, fxmlFile);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You are not authenticated. Please log in to continue.",
                    ButtonType.OK);
            alert.setTitle("Authentication Required");
            alert.setHeaderText("Access Denied");

            alert.showAndWait();
        }
    }
}
