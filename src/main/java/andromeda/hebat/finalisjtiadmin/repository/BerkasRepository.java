package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BerkasRepository {
    public static void addNewTemplateSurat(String namaSurat, String keperluanSurat, String fileSurat) {
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
            INSERT INTO BERKAS.Surat (nama_surat, keperluan_surat, file_surat) 
            VALUES (?, ?, ?) 
            """)) {
            stmt.setString(1, namaSurat);
            stmt.setString(2, keperluanSurat);
            stmt.setString(3, fileSurat);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully insert new berkas template surat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
