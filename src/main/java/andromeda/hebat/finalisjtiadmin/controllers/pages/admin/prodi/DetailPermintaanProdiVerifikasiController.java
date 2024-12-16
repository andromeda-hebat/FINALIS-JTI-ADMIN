package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.prodi;

import andromeda.hebat.finalisjtiadmin.models.DetailBerkasProdiPengajuan;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class DetailPermintaanProdiVerifikasiController {
    @FXML private Label textNamaLengkap;
    @FXML private Label textNim;
    @FXML private Label textStatus;
    @FXML private Label textIDVerifikasi;
    @FXML private TextField inputSertifTOEIC;
    @FXML private TextField inputLaporanSkripsi;
    @FXML private TextField inputLaporanMagang;
    @FXML private TextField inputSuratKompen;
    @FXML private RadioButton acceptRadioBtn;
    @FXML private RadioButton rejectRadioBtn;

    private ToggleGroup toggleGroup;
    private DetailBerkasProdiPengajuan databerkas;

    @FXML
    private void initialize() {
        toggleGroup = new ToggleGroup();
        acceptRadioBtn.setToggleGroup(toggleGroup);
        rejectRadioBtn.setToggleGroup(toggleGroup);
    }

    public void setDataBerkas(DetailBerkasProdiPengajuan databerkas) {
        this.databerkas = databerkas;
        initData();
    }

    private void initData() {
        this.textNamaLengkap.setText(this.databerkas.getNamaLengkap());
        this.textNim.setText("NIM " + this.databerkas.getNim());
        this.textStatus.setText(this.databerkas.getStatusVerifikasi());
        this.textIDVerifikasi.setText(String.valueOf(this.databerkas.getIdVerifikasi()));
        this.inputSertifTOEIC.setText(String.valueOf(this.databerkas.getToeic()));
        this.inputLaporanSkripsi.setText(String.valueOf(this.databerkas.getToeic()));
        this.inputLaporanMagang.setText(String.valueOf(this.databerkas.getMagang()));
        this.inputSuratKompen.setText(String.valueOf(this.databerkas.getBebasKompen()));
    }
}
