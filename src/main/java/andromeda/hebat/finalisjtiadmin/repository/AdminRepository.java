package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.helper.JsonHelper;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminRepository {
    /**
     * Verifies the credentials of a user by querying the local database.
     * This method connects to the database directly using Java code,
     * retrieves an admin user based on the provided username and password,
     * and returns the corresponding {@code Admin} object if a match is found.
     *
     * @param userID username the username of the admin to be verified.
     * @param password password the password of the admin to be verified.
     * @return an {@code Admin} object representing the verified admin user if
     *          credentials are valid; otherwise, returns {@code null}.
     */
    public static Admin getAdminByIDAndPassword(String userID, String password) {
        Admin admin = null;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
            SELECT 
                id_admin,
                nama_lengkap,
                password,
                email,
                jabatan
            FROM USERS.Admin 
            WHERE id_admin = ?
            """)) {
            stmt.setString(1, userID);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                boolean isPasswordMatch = BCrypt.checkpw(password, hashedPassword);

                if (isPasswordMatch) {
                    admin = new Admin();
                    admin.setUserId(rs.getString("id_admin"));
                    admin.setName(rs.getString("nama_lengkap"));
                    admin.setPassword(rs.getString("password"));
                    admin.setEmail(rs.getString("email"));
                    admin.setJabatan(rs.getString("jabatan"));
                    admin.setFotoProfil(getAdminPhotoProfile(admin.getUserId()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    public static String getAdminPhotoProfile(String userID) {
        String result = null;
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
            SELECT foto_profil
            FROM USERS.Admin
            WHERE id_admin = ?
            """)) {
            stmt.setString(1, userID);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result = rs.getString("foto_profil");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Verifies the credentials of a user by connecting to external API.
     * Returns the corresponding {@code Admin} object if a match is found.
     *
     * @param userID username the username of the admin to be verified.
     * @param password password the password of the admin to be verified.
     * @return an {@code Admin} object representing the verified admin user if
     *           credentials are valid; otherwise, returns {@code null}.
     */
    public static Admin checkUserViaExternalAPI(String userID, String password) {
        Admin admin = null;
        HttpClient client = HttpClient.newHttpClient();

        String requestBody = "username="+userID+"&password="+password;

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://finalis-jti-web.test/api/auth"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                admin = new Admin();
                admin.setUserId(JsonHelper.getDataFromJson(response.body(), "id_admin"));
                admin.setName(JsonHelper.getDataFromJson(response.body(), "nama_lengkap"));
                admin.setPassword(JsonHelper.getDataFromJson(response.body(), "password"));
                admin.setEmail(JsonHelper.getDataFromJson(response.body(), "email"));
                admin.setJabatan(JsonHelper.getDataFromJson(response.body(), "jabatan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return admin;
    }

    public static ObservableList getAllAdmin() {
        ObservableList<Admin> result = FXCollections.observableArrayList();;
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("""
             SELECT
                id_admin,
                nama_lengkap,
                password, 
                email, 
                jabatan
             FROM USERS.Admin
             """)) {
            while (rs.next()) {
                result.add(new Admin(
                        rs.getString("id_admin"),
                        rs.getString("nama_lengkap"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("jabatan")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String insertNewAdmin(Admin admin) throws SQLException {
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
            INSERT INTO USERS.Admin
            VALUES
            (?, ?, ?, ?, ?, ?)
            """)) {
            String hashedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
            stmt.setString(1, admin.getUserId());
            stmt.setString(2, admin.getName());
            stmt.setString(3, hashedPassword);
            stmt.setString(4, admin.getEmail());
            stmt.setString(5, admin.getJabatan().getJenisAdminStr());
            stmt.setString(6, admin.getFotoProfil());

            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0) ? "success" : "failed";
        }
    }

    public static void updateAdminFullName(String idAdmin, String fullName) {
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
            UPDATE USERS.Admin
            SET 
                nama_lengkap = ?
            WHERE id_admin = ?
            """)) {
            stmt.setString(1, fullName);
            stmt.setString(2, idAdmin);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAdminEmail(String idAdmin, String email) {
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
            UPDATE USERS.Admin
            SET
                email = ?
            WHERE id_admin = ?
            """)) {
            stmt.setString(1, email);
            stmt.setString(2, idAdmin);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAdminJabatan(String idAdmin, String jabatan) {
        try (PreparedStatement stmt = Database.getConnection().prepareStatement("""
            UPDATE USERS.Admin
            SET
                jabatan = ?
            WHERE id_admin = ?
            """)) {
            stmt.setString(1, jabatan);
            stmt.setString(2, idAdmin);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
