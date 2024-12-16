package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.ta;

import andromeda.hebat.finalisjtiadmin.models.DetailBerkasTAPengajuan;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DetailPermintaanTAVerifikasiController {
    @FXML private Label textNamaLengkap;
    @FXML private Label textNim;
    @FXML private Label textStatus;
    @FXML private Label textIDVerifikasi;
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
        this.textNamaLengkap.setText(this.dataBerkas.getNamaLengkap());
        this.textNim.setText("NIM " + this.dataBerkas.getNim());
        this.textStatus.setText(this.dataBerkas.getStatusVerifikasi());
        this.textIDVerifikasi.setText(String.valueOf(this.dataBerkas.getIdVerifikasi()));
        this.inputLaporanTA.setText(this.dataBerkas.getLaporanTA());
        this.inputAplikasi.setText(this.dataBerkas.getAplikasi());
        this.inputBuktiPublikasi.setText(this.dataBerkas.getBuktiPublikasi());
    }
}
