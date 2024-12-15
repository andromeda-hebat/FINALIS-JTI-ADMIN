package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.ta;

import andromeda.hebat.finalisjtiadmin.models.DetailBerkasTAPengajuan;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DetailPermintaanTAVerifikasiController {
    @FXML private TextField inputLaporanTA;
    @FXML private TextField inputAplikasi;
    @FXML private TextField inputBuktiPublikasi;
    @FXML private RadioButton acceptRadioBtn;
    @FXML private RadioButton rejectRadioBtn;

    private ToggleGroup toggleGroup;
    private DetailBerkasTAPengajuan dataBerkas;

    @FXML
    private void initialize() {
        toggleGroup = new ToggleGroup();

        acceptRadioBtn.setToggleGroup(toggleGroup);
        rejectRadioBtn.setToggleGroup(toggleGroup);
    }

    public void setDataBerkas(DetailBerkasTAPengajuan dataBerkas) {
        this.dataBerkas = dataBerkas;
        initData();
    }

    private void initData() {
        this.inputLaporanTA.setText(this.dataBerkas.getLaporanTA());
        this.inputAplikasi.setText(this.dataBerkas.getAplikasi());
        this.inputBuktiPublikasi.setText(this.dataBerkas.getBuktiPublikasi());
    }
}
