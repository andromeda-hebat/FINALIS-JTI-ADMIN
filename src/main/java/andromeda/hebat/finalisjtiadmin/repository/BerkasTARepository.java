package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BerkasTARepository {
    public static ArrayList<BerkasPengajuan> getAllBerkas() {
        ArrayList<BerkasPengajuan> result = new ArrayList<>();

        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("""
                         SELECT
                             ROW_NUMBER() OVER (ORDER BY tanggal_request ASC) AS nomor,
                             vb.id_verifikasi,
                             ta.id_ta AS id_berkas,
                             m.nim,
                             m.nama_lengkap,
                             ta.tanggal_request,
                             vb.status_verifikasi,
                             vb.keterangan_verifikasi
                         FROM VER.VerifikasiBerkas vb
                         INNER JOIN BERKAS.TA ta ON vb.id_berkas = ta.id_ta
                         INNER JOIN USERS.Mahasiswa m ON ta.nim = m.nim
                         ORDER BY ta.tanggal_request DESC
                     """)) {

            while (rs.next()) {
                result.add(
                        new BerkasPengajuan(
                                rs.getInt("id_verifikasi"),
                                rs.getString("id_berkas"),
                                rs.getString("nim"),
                                rs.getString("nama_lengkap"),
                                rs.getString("tanggal_request"),
                                rs.getString("status_verifikasi")
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<BerkasPengajuan> getAllSubmittedBerkas() {
        ArrayList<BerkasPengajuan> result = new ArrayList<>();

        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("""
                         SELECT
                             vb.id_verifikasi,
                             ta.id_ta AS id_berkas,
                             m.nim,
                             m.nama_lengkap,
                             ta.tanggal_request,
                             vb.status_verifikasi
                         FROM VER.VerifikasiBerkas vb
                         INNER JOIN BERKAS.TA ta ON vb.id_berkas = ta.id_ta
                         INNER JOIN USERS.Mahasiswa m ON ta.nim = m.nim
                         WHERE vb.status_verifikasi = 'Diajukan'
                         ORDER BY ta.tanggal_request DESC
                     """)) {

            while (rs.next()) {
                result.add(
                        new BerkasPengajuan(
                                rs.getInt("id_verifikasi"),
                                rs.getString("id_berkas"),
                                rs.getString("status_verifikasi"),
                                rs.getString("nim"),
                                rs.getString("nama_lengkap"),
                                rs.getString("tanggal_request")
                        ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
