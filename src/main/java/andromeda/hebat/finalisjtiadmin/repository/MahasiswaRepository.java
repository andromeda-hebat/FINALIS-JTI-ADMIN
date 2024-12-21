package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Mahasiswa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama_lengkap"));
                mhs.setEmail(rs.getString("email"));
                mhs.setJurusan(rs.getString("jurusan"));
                mhs.setProdi(rs.getString("prodi"));
                mhs.setTahunAngkatan(rs.getString("tahun_masuk"));
                result.add(mhs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String insertNewMahasiswa(Mahasiswa mahasiswa) throws SQLException {
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
            INSERT INTO USERS.Mahasiswa
            VALUES
            (?, ?, ?, ?, ?, ?, ?, ?)
            """)) {
            String hashedPassword = BCrypt.hashpw(mahasiswa.getPassword(), BCrypt.gensalt());
            stmt.setString(1, mahasiswa.getNim());
            stmt.setString(2, mahasiswa.getNama());
            stmt.setString(3, hashedPassword);
            stmt.setString(4, mahasiswa.getEmail());
            stmt.setString(5, mahasiswa.getJurusan());
            stmt.setString(6, mahasiswa.getProdi());
            stmt.setString(7, mahasiswa.getTahunAngkatan());
            stmt.setString(8, mahasiswa.getFotoProfil());

            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0) ? "success" : "failed";
        }
    }
}
