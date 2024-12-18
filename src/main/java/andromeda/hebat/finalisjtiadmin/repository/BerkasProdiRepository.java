package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import andromeda.hebat.finalisjtiadmin.models.DetailBerkasProdiPengajuan;
import andromeda.hebat.finalisjtiadmin.models.DetailBerkasTAPengajuan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BerkasProdiRepository {
    public static ArrayList<BerkasPengajuan> getAllBerkas() {
        ArrayList<BerkasPengajuan> result = new ArrayList<>();

        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("""
                         SELECT
                             vb.id_verifikasi,
                             pr.id_berkas_prodi AS id_berkas,
                             m.nim,
                             m.nama_lengkap,
                             pr.tanggal_request,
                             vb.status_verifikasi
                         FROM VER.VerifikasiBerkas vb
                         INNER JOIN BERKAS.Prodi pr ON vb.id_berkas = pr.id_berkas_prodi
                         INNER JOIN USERS.Mahasiswa m ON pr.nim = m.nim
                         ORDER BY pr.tanggal_request DESC
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
                             pr.id_berkas_prodi AS id_berkas,
                             m.nim,
                             m.nama_lengkap,
                             pr.tanggal_request,
                             vb.status_verifikasi
                         FROM VER.VerifikasiBerkas vb
                         INNER JOIN BERKAS.Prodi pr ON vb.id_berkas = pr.id_berkas_prodi
                         INNER JOIN USERS.Mahasiswa m ON pr.nim = m.nim
                         WHERE vb.status_verifikasi = 'Diajukan'
                         ORDER BY pr.tanggal_request DESC
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

    public static DetailBerkasProdiPengajuan getSingleBerkasProdi(int idVerifikasi) {
        DetailBerkasProdiPengajuan data = null;

        try(PreparedStatement stmt = Database.getConnection().prepareStatement("""
                SELECT
                    vb.id_verifikasi,
                    pr.id_berkas_prodi AS id_berkas,
                    m.nim,
                    m.nama_lengkap,
                    pr.tanggal_request,
                    vb.status_verifikasi,
                    vb.keterangan_verifikasi,
                    pr.distribusi_magang,
                	pr.distribusi_skripsi,
                	pr.surat_bebas_kompen,
                	pr.toeic
                FROM VER.VerifikasiBerkas vb
                INNER JOIN BERKAS.Prodi pr ON vb.id_berkas = pr.id_berkas_prodi
                INNER JOIN USERS.Mahasiswa m ON pr.nim = m.nim
                WHERE vb.id_verifikasi = ?;
                """)) {

            stmt.setInt(1, idVerifikasi);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                data = new DetailBerkasProdiPengajuan();
                data.setIdVerifikasi(rs.getInt("id_verifikasi"));
                data.setIdBerkas(rs.getString("id_berkas"));
                data.setStatusVerifikasi(rs.getString("status_verifikasi"));
                data.setNim(rs.getString("nim"));
                data.setNamaLengkap(rs.getString("nama_lengkap"));
                data.setTanggalRequest(rs.getString("tanggal_request"));
                data.setToeic(rs.getString("toeic"));
                data.setDistribusiSkripsi(rs.getString("distribusi_skripsi"));
                data.setMagang(rs.getString("distribusi_magang"));
                data.setBebasKompen(rs.getString("surat_bebas_kompen"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }


}
