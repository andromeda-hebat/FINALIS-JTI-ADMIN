package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Surat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BerkasRepository {
    public static ObservableList getAllTemplateSurat() {
        ObservableList<Surat> result = FXCollections.observableArrayList();
        try (Statement stmt = Database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("""
            SELECT id_surat, nama_surat, keperluan_surat, file_surat
            FROM BERKAS.Surat
            """)) {
            while (rs.next()) {
                result.add(new Surat(
                        rs.getInt("id_surat"),
                        rs.getString("nama_surat"),
                        rs.getString("keperluan_surat"),
                        rs.getString("file_surat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

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
