package andromeda.hebat.finalisjtiadmin.controllers.ta;

import andromeda.hebat.finalisjtiadmin.core.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class  StatistikTAController {
    @FXML private Label PengajuanTA;
    @FXML private Label TAVerif;
    @FXML private Label TADitolak;

    @FXML
    public void initialize(){
        JumlahPengajuanTA();
        JumlahTAVerif();
        JumlahTADitolak();
    }

    private void JumlahPengajuanTA(){
        String query = "SELECT COUNT(*) AS total_pengajuan_TA FROM VER.VerifikasiBerkas WHERE jenis_berkas = 'TA'";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int total = rs.getInt("total_pengajuan_TA");
                System.out.println("Jumlah " + total);
                PengajuanTA.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void JumlahTAVerif(){
        String query = "SELECT COUNT(*) AS total_pengajuan_TA FROM VER.VerifikasiBerkas WHERE jenis_berkas = 'TA' AND status_verifikasi = 'Disetujui'";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int total = rs.getInt("total_pengajuan_TA");
                TAVerif.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void JumlahTADitolak(){
        String query = "SELECT COUNT(*) AS total_pengajuan_TA FROM VER.VerifikasiBerkas WHERE jenis_berkas = 'TA' AND status_verifikasi = 'Ditolak'";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int total = rs.getInt("total_pengajuan_TA");
                TADitolak.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
