package andromeda.hebat.finalisjtiadmin.controllers.prodi;

import andromeda.hebat.finalisjtiadmin.core.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatistikProdiController {
    @FXML private Label PengajuanProdi;
    @FXML private Label ProdiVerif;
    @FXML private Label ProdiDitolak;

    @FXML
    public void initialize(){
        JumlahPengajuanProdi();
        JumlahProdiVerif();
        JumlahProdiDitolak();
    }

    private void JumlahPengajuanProdi(){
        String query = "SELECT COUNT(*) AS total_pengajuan_TA FROM VER.VerifikasiBerkas WHERE jenis_berkas = 'Prodi'";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int total = rs.getInt("total_pengajuan_TA");
                System.out.println("Jumlah " + total);
                PengajuanProdi.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void JumlahProdiVerif(){
        String query = "SELECT COUNT(*) AS total_pengajuan_TA FROM VER.VerifikasiBerkas WHERE jenis_berkas = 'Prodi' AND status_verifikasi = 'Disetujui'";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int total = rs.getInt("total_pengajuan_TA");
                ProdiVerif.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void JumlahProdiDitolak(){
        String query = "SELECT COUNT(*) AS total_pengajuan_TA FROM VER.VerifikasiBerkas WHERE jenis_berkas = 'Prodi' AND status_verifikasi = 'Ditolak'";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int total = rs.getInt("total_pengajuan_TA");
                ProdiDitolak.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
