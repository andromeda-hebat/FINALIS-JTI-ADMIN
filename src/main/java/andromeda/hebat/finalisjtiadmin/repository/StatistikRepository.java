package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class StatistikRepository {
    public static HashMap<String, Integer> getStatisticRequest(String jenisBerkas) {
        HashMap<String, Integer> result = new HashMap<>();
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
                SELECT
                    COUNT(*) AS total_pengajuan,
                    COUNT(CASE WHEN status_verifikasi = 'Disetujui' THEN 1 END) AS total_disetujui,
                    COUNT(CASE WHEN status_verifikasi = 'Ditolak' THEN 1 END) AS total_ditolak
                FROM VER.VerifikasiBerkas
                WHERE jenis_berkas = ?
                """)) {
                stmt.setString(1, jenisBerkas);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result.put("total_pengajuan", rs.getInt("total_pengajuan"));
                result.put("total_disetujui", rs.getInt("total_disetujui"));
                result.put("total_ditolak", rs.getInt("total_ditolak"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
