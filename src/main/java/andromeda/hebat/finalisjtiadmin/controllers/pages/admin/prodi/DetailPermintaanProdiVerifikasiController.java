package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.prodi;

import andromeda.hebat.finalisjtiadmin.models.DetailBerkasProdiPengajuan;
import andromeda.hebat.finalisjtiadmin.repository.BerkasProdiRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    @FXML private TextField inputLaporanSkripsi;
    @FXML private TextField inputLaporanMagang;
    @FXML private TextField inputSuratKompen;
    @FXML private TextArea keteranganVerifikasi;
    @FXML private RadioButton acceptRadioBtn;
    @FXML private RadioButton rejectRadioBtn;
    private String idBerkas;

    private ToggleGroup toggleGroup;
    private DetailBerkasProdiPengajuan dataBerkas;

    @FXML
    private void initialize() {
        acceptRadioBtn.setUserData("Disetujui");
        rejectRadioBtn.setUserData("Ditolak");

        toggleGroup = new ToggleGroup();
        acceptRadioBtn.setToggleGroup(toggleGroup);
        rejectRadioBtn.setToggleGroup(toggleGroup);
    }

    public void setDataBerkas(DetailBerkasProdiPengajuan databerkas) {
        this.dataBerkas = databerkas;
        initData();
    }

    private void initData() {
        this.textNamaLengkap.setText(this.dataBerkas.getNamaLengkap());
        this.textNim.setText("NIM " + this.dataBerkas.getNim());
        this.textStatus.setText(this.dataBerkas.getStatusVerifikasi());
        this.textIDVerifikasi.setText(String.valueOf(this.dataBerkas.getIdVerifikasi()));
        this.inputSertifTOEIC.setText(String.valueOf(this.dataBerkas.getToeic()));
        this.inputLaporanSkripsi.setText(String.valueOf(this.dataBerkas.getDistribusiSkripsi()));
        this.inputLaporanMagang.setText(String.valueOf(this.dataBerkas.getMagang()));
        this.inputSuratKompen.setText(String.valueOf(this.dataBerkas.getBebasKompen()));
        this.idBerkas = dataBerkas.getIdBerkas();
    }

    @FXML
    private void btnFileToeicOnClicked() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop()
                    .browse(new URI("http://finalis-jti-web.test/uploads?file="+this.inputSertifTOEIC.getText()+"&category=administrasi_prodi&sub_category=toeic"));
        }
    }

    @FXML
    private void btnFileLaporanSkripsiOnClicked() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop()
                    .browse(new URI("http://finalis-jti-web.test/uploads?file="+this.inputLaporanSkripsi.getText()+"&category=administrasi_prodi&sub_category=distribusi_tugas_akhir"));
        }
    }

    @FXML
    private void btnFileLaporanMagangOnClicked() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop()
                    .browse(new URI("http://finalis-jti-web.test/uploads?file="+this.inputLaporanMagang.getText()+"&category=administrasi_prodi&sub_category=distribusi_magang"));
        }
    }

    @FXML
    private void btnFileSuratKompenOnClicked() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop()
                    .browse(new URI("http://finalis-jti-web.test/uploads?file="+this.inputSuratKompen.getText()+"&category=administrasi_prodi&sub_category=bebas_kompen"));
        }
    }

    @FXML
    private void updateVerification() {
        String result = BerkasProdiRepository.updateVerifyStatus(this.idBerkas, (String) this.toggleGroup.getSelectedToggle().getUserData(), this.keteranganVerifikasi.getText(), "A12346");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (result.equalsIgnoreCase("success")) {
            alert.setTitle("Berhasil memperbarui data!");
            alert.setHeaderText("Berkas pengajuan Prodi berhasil diperbarui!");
        } else {
            alert.setTitle("Berhasil memperbarui data!");
            alert.setHeaderText("Berkas pengajuan Prodi berhasil diperbarui!");
        }

        alert.showAndWait();

    }
}
