package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import andromeda.hebat.finalisjtiadmin.models.DetailBerkasTAPengajuan;

import java.sql.PreparedStatement;
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

    public static DetailBerkasTAPengajuan getSingleBerkasTA(int idVerifikasi) {
        DetailBerkasTAPengajuan data = null;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
                    SELECT
                        vb.id_verifikasi,
                        ta.id_ta AS id_berkas,
                        m.nim,
                        m.nama_lengkap,
                        ta.tanggal_request,
                        vb.status_verifikasi,
                        ta.laporan_TA AS laporan_ta,
                        ta.aplikasi,
                        ta.bukti_publikasi
                    FROM VER.VerifikasiBerkas vb
                    INNER JOIN BERKAS.TA ta ON vb.id_berkas = ta.id_ta
                    INNER JOIN USERS.Mahasiswa m ON ta.nim = m.nim
                    WHERE vb.id_verifikasi = ?
                """)) {
            stmt.setInt(1, idVerifikasi);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                data = new DetailBerkasTAPengajuan();
                data.setIdVerifikasi(rs.getInt("id_verifikasi"));
                data.setIdBerkas(rs.getString("id_berkas"));
                data.setStatusVerifikasi(rs.getString("status_verifikasi"));
                data.setNim(rs.getString("nim"));
                data.setNamaLengkap(rs.getString("nama_lengkap"));
                data.setTanggalRequest(rs.getString("tanggal_request"));
                data.setLaporanTA(rs.getString("laporan_ta"));
                data.setAplikasi(rs.getString("aplikasi"));
                data.setBuktiPublikasi(rs.getString("bukti_publikasi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String updateVerifyStatus(String idBerkas, String statusVerifikasi, String keteranganVerifikasi, String idAdmin) {
        try (PreparedStatement stmt = Database.getConnection().prepareCall("""
                EXEC sp_UpdateVerifikasiBerkasTA
                    @id_berkas = ?,
                    @status_verifikasi = ?,
                    @keterangan_verifikasi = ?,
                    @id_admin = ?
                """)) {
            stmt.setString(1, idBerkas);
            stmt.setString(2, statusVerifikasi);
            stmt.setString(3, keteranganVerifikasi);
            stmt.setString(4, idAdmin);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "fail";
    }
}
