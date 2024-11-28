package andromeda.hebat.finalisjtiadmin.controllers.prodi;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SidebarController {
    private Stage mainStage;
    @FXML
    private VBox sidebar;
    public void btnPermintaanVerifSeluruhOnClicked() {
        mainStage = (Stage) sidebar.getScene().getWindow();
        SceneHelper.changeScene(mainStage, "/views/adminprodi/permintaan-verif-seluruh.fxml");
    }
}
