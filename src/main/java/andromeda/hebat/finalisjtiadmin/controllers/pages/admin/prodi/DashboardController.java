package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.prodi;

import andromeda.hebat.finalisjtiadmin.helper.CSSHelper;
import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class DashboardController {
    @FXML private HBox dashboardContainer;
    @FXML private TableView<BerkasPengajuan> tableViewBerkasPengajuan;

    @FXML
    private void initialize() {
        CSSHelper.loadCSS(dashboardContainer, "global");
    }
}
