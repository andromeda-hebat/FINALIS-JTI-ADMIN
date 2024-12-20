package andromeda.hebat.finalisjtiadmin.controllers.components.admin;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

abstract public class SidebarController {
    @FXML protected VBox sidebarContainer;

    @FXML
    public void btnLogoutOnClicked() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Konfirmasi keluar");
        confirmationAlert.setHeaderText("Konfirmasi untuk keluar akun");
        confirmationAlert.setContentText("Apakah anda yakin untuk keluar ?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            ((Stage) sidebarContainer.getScene().getWindow()).close();
            Stage mainStage = new Stage();
            SceneHelper.changeScene(mainStage, "/views/pages/general/login.fxml", 600, 400);
        }
    }
}
