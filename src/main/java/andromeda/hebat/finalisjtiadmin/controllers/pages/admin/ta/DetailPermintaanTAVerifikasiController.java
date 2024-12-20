package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.ta;

import andromeda.hebat.finalisjtiadmin.models.DetailBerkasTAPengajuan;
import andromeda.hebat.finalisjtiadmin.repository.BerkasTARepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DetailPermintaanTAVerifikasiController {
    @FXML private Label textNamaLengkap;
    @FXML private Label textNim;
    @FXML private Label textStatus;
    @FXML private Label textIDVerifikasi;
    @FXML private TextField inputLaporanTA;
    @FXML private Button btnFileLaporanTA;
    @FXML private Button btnFileAplikasi;
    @FXML private Button btnFilePublikasi;
    @FXML private TextField inputAplikasi;
    @FXML private TextField inputBuktiPublikasi;
    @FXML private RadioButton acceptRadioBtn;
    @FXML private RadioButton rejectRadioBtn;
    @FXML private TextArea keteranganVerifikasi;
    private String idBerkas;

    private ToggleGroup toggleGroup;
    private DetailBerkasTAPengajuan dataBerkas;

    @FXML
    private void initialize() {
        acceptRadioBtn.setUserData("Disetujui");
        rejectRadioBtn.setUserData("Ditolak");

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
        this.idBerkas = dataBerkas.getIdBerkas();
    }

    @FXML
    private void btnFileLaporanTAOnClicked() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop()
                    .browse(new URI("http://finalis-jti-web.test/uploads?file="+this.inputLaporanTA.getText()+"&category=tugas_akhir&sub_category=laporan_ta"));
        }
    }

    @FXML
    private void btnFileAplikasiOnClicked() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop()
                    .browse(new URI("http://finalis-jti-web.test/uploads?file="+this.inputAplikasi.getText()+"&category=tugas_akhir&sub_category=program_aplikasi"));
        }
    }

    @FXML
    private void btnFilePublikasiOnClicked() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop()
                    .browse(new URI("http://finalis-jti-web.test/uploads?file="+this.inputBuktiPublikasi.getText()+"&category=tugas_akhir&sub_category=publikasi_jurnal"));
        }
    }

    @FXML
    private void updateVerification() {
        String result = BerkasTARepository.updateVerifyStatus(this.idBerkas, (String) this.toggleGroup.getSelectedToggle().getUserData(), this.keteranganVerifikasi.getText(), "A12345");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (result.equalsIgnoreCase("success")) {
            alert.setTitle("Berhasil memperbarui data!");
            alert.setHeaderText("Berkas pengajuan TA berhasil diperbarui!");
        } else {
            alert.setTitle("Berhasil memperbarui data!");
            alert.setHeaderText("Berkas pengajuan TA berhasil diperbarui!");
        }

        alert.showAndWait();

    }
}
