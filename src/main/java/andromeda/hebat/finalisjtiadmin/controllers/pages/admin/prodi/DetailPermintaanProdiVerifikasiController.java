package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.prodi;

import andromeda.hebat.finalisjtiadmin.models.DetailBerkasProdiPengajuan;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class DetailPermintaanProdiVerifikasiController {
    @FXML private Label textNamaLengkap;
    @FXML private Label textNim;
    @FXML private Label textStatus;
    @FXML private Label textIDVerifikasi;
    @FXML private TextField inputSertifTOEIC;
    @FXML private TextField inputLaporanTA;
    @FXML private TextField inputLaporanMagang;
    @FXML private TextField inputSuratKompen;
    @FXML private Button btnFileToeic;
    @FXML private Button btnFileLaporanTA;
    @FXML private Button btnFileLaporanMagang;
    @FXML private Button btnFileSuratKompen;
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
        this.inputLaporanTA.setText(String.valueOf(this.databerkas.getToeic()));
        this.inputLaporanMagang.setText(String.valueOf(this.databerkas.getMagang()));
        this.inputSuratKompen.setText(String.valueOf(this.databerkas.getBebasKompen()));
    }

    @FXML
    private void btnFileToeicOnClicked() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop()
                    .browse(new URI("http://finalis-jti-web.test/uploads?file="+this.inputSertifTOEIC.getText()+"&category=administrasi_prodi&sub_category=toeic"));
        }
    }

    @FXML
    private void btnFileLaporanTAOnClicked() {

    }

    @FXML
    private void btnFileLaporanMagangOnClicked() {

    }

    @FXML
    private void btnFileSuratKompenOnClicked() {

    }
}
