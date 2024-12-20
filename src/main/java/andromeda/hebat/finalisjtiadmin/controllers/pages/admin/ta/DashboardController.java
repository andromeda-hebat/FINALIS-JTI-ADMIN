package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.ta;

import andromeda.hebat.finalisjtiadmin.controllers.components.admin.TabelPengajuanController;
import andromeda.hebat.finalisjtiadmin.controllers.components.admin.TopbarController;
import andromeda.hebat.finalisjtiadmin.controllers.pages.admin.PageParentController;
import andromeda.hebat.finalisjtiadmin.helper.CSSHelper;
import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import andromeda.hebat.finalisjtiadmin.models.JenisBerkas;
import andromeda.hebat.finalisjtiadmin.repository.StatistikRepository;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.SQLOutput;
import java.util.HashMap;

public class DashboardController {
    @FXML private HBox topbarComponent;
    @FXML private HBox dashboardContainer;
    @FXML private Label totalRequest;
    @FXML private Label totalApproved;
    @FXML private Label totalRejected;
    @FXML private TableView<BerkasPengajuan> tabelPengajuan;
    @FXML private TabelPengajuanController tabelPengajuanController;

    private ObservableList<BerkasPengajuan> pengajuanTAList;

    @FXML
    public void initialize() {
        CSSHelper.loadCSS(dashboardContainer, "global");
        statisticRequestInit();

        tabelPengajuanController.setFileType(JenisBerkas.BERKAS_TA, false);
    }

    private void statisticRequestInit() {
        HashMap<String, Integer> requestStats = StatistikRepository.getStatisticRequest("TA");
        totalRequest.setText(String.valueOf(requestStats.get("total_pengajuan")));
        totalApproved.setText(String.valueOf(requestStats.get("total_disetujui")));
        totalRejected.setText(String.valueOf(requestStats.get("total_ditolak")));
    }
}
