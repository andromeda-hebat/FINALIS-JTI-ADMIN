package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.ta;

import andromeda.hebat.finalisjtiadmin.controllers.components.admin.TabelPengajuanController;
import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class DaftarPermintaanVerifikasiController {
    @FXML private TableView<BerkasPengajuan> tabelPengajuan;
    @FXML private TabelPengajuanController tabelPengajuanController;

    @FXML
    private void initialize() {
        tabelPengajuanController.setFileType("Berkas TA", true);
    }
}
