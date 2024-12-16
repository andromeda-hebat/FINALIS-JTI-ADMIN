package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.prodi;

import andromeda.hebat.finalisjtiadmin.controllers.components.admin.TabelPengajuanController;
import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import andromeda.hebat.finalisjtiadmin.models.JenisBerkas;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class DaftarPermintaanVerifikasiController {
    @FXML private TableView<BerkasPengajuan> tabelPengajuan;
    @FXML private TabelPengajuanController tabelPengajuanController;

    @FXML
    private void initialize() {
        tabelPengajuanController.setFileType(JenisBerkas.BERKAS_PRODI, true);
    }
}
