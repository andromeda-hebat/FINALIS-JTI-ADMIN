package andromeda.hebat.finalisjtiadmin.controllers;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.helper.JsonHelper;
import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private VBox loginScene;

    @FXML
    private TextField inputFieldUsername;

    @FXML
    private PasswordField inputFieldPassword;

    @FXML
    private void enterDashboard() {
        final String username = inputFieldUsername.getText();
        final String password = inputFieldPassword.getText();
        String fxmlFile = null;

        Admin currentAdmin = checkUser(username, password);
        if (currentAdmin != null) {
            switch (currentAdmin.getJabatan()) {
                case "Admin Jurusan":
                    System.out.println("Selamat datang admin jurusan!");
                    fxmlFile = "/views/adminjurusan/kelola-data-admin.fxml";
                    break;
                case "Admin Prodi":
                    System.out.println("Selamat datang admin prodi!");
                    fxmlFile = "/views/adminprodi/dashboard.fxml";
                    break;
                case "Admin TA":
                    System.out.println("Selamat datang admin TA!");
                    fxmlFile = "/views/adminta/dashboard.fxml";
                    break;
            }

            Stage mainStage = (Stage) loginScene.getScene().getWindow();
            SceneHelper.changeScene(mainStage, fxmlFile);
        } else {
            System.out.println("Tidak ditemukan username yang cocok dengan data pengguna!");
        }
    }

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
    private Admin checkUser(String username, String password) {
        String query = "SELECT * FROM USERS.Admin WHERE nama_lengkap = ? AND password = ?";
        Admin admin = null;

        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
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
     * @param username username the username of the admin to be verified.
     * @param password password the password of the admin to be verified.
     * @return an {@code Admin} object representing the verified admin user if
     *           credentials are valid; otherwise, returns {@code null}.
     */
//    private Admin checkUser(String username, String password) {
//        Admin admin = null;
//        HttpClient client = HttpClient.newHttpClient();
//
//        String requestBody = "username="+username+"&password="+password;
//
//        HttpRequest req = HttpRequest.newBuilder()
//                .uri(URI.create("http://finalis-jti-web.test/api/auth"))
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .header("Accept", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        try {
//            HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
//
//            if (response.statusCode() == 200) {
//                admin = new Admin();
//                admin.setUserId(JsonHelper.getDataFromJson(response.body(), "id_admin"));
//                admin.setName(JsonHelper.getDataFromJson(response.body(), "nama_lengkap"));
//                admin.setPassword(JsonHelper.getDataFromJson(response.body(), "password"));
//                admin.setEmail(JsonHelper.getDataFromJson(response.body(), "email"));
//                admin.setJabatan(JsonHelper.getDataFromJson(response.body(), "jabatan"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return admin;
//    }
}
