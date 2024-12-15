package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan;

import andromeda.hebat.finalisjtiadmin.helper.CSSHelper;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class DashboardController {
    @FXML private HBox dashboardContainer;

    @FXML
    private void initialize() {
        CSSHelper.loadCSS(dashboardContainer, "global");
    }
}
