package andromeda.hebat.finalisjtiadmin.repository;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.helper.JsonHelper;
import andromeda.hebat.finalisjtiadmin.models.Admin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {
    /**
     * Verifies the credentials of a user by querying the local database.
     * This method connects to the database directly using Java code,
     * retrieves an admin user based on the provided username and password,
     * and returns the corresponding {@code Admin} object if a match is found.
     *
     * @param username username the username of the admin to be verified.
     * @param password password the password of the admin to be verified.
     * @return an {@code Admin} object representing the verified admin user if
     *          credentials are valid; otherwise, returns {@code null}.
     */
    public static Admin checkUser(String userID, String password) {
        String query = "SELECT * FROM USERS.Admin WHERE id_admin = ? AND password = ?";
        Admin admin = null;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, userID);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setUserId(rs.getString("id_admin"));
                admin.setName(rs.getString("nama_lengkap"));
                admin.setPassword(rs.getString("password"));
                admin.setEmail(rs.getString("email"));
                admin.setJabatan(rs.getString("jabatan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return admin;
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
}
