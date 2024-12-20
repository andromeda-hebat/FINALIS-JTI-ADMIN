package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import andromeda.hebat.finalisjtiadmin.models.Mahasiswa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class MahasiswaRepository {
    public static ObservableList getAllMahasiswa() {
        ObservableList<Mahasiswa> result = FXCollections.observableArrayList();;
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("""
             SELECT
                nim,
                nama_lengkap,
                email, 
                jurusan, 
                prodi,
                tahun_masuk
             FROM USERS.Mahasiswa
             """)) {
            while (rs.next()) {
                result.add(new Mahasiswa(
                        rs.getString("nim"),
                        rs.getString("nama_lengkap"),
                        rs.getString("email"),
                        rs.getString("jurusan"),
                        rs.getString("prodi"),
                        rs.getString("tahun_masuk")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
