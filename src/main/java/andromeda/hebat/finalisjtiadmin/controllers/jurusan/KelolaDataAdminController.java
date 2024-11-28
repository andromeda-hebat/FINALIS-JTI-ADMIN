package andromeda.hebat.finalisjtiadmin.controllers.jurusan;

import andromeda.hebat.finalisjtiadmin.Main;
import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class KelolaDataAdminController {

    @FXML
    private Button btnTambahkan;

    @FXML private TableView<Admin> tableViewAdmin;

    @FXML private TableColumn<Admin, Integer> numberColumn;

    @FXML private TableColumn<Admin, String> adminIDColumn;

    @FXML private TableColumn<Admin, String> fullNameColumn;

    @FXML private TableColumn<Admin, String> emailColumn;

    @FXML private TableColumn<Admin, String> positionColumn;

    @FXML private TableColumn<Admin, String> actionColumn;

    @FXML
    public void initialize() {
        numberColumn.setCellValueFactory((TableColumn.CellDataFeatures<Admin, Integer> cellData) -> {
            int index = tableViewAdmin.getItems().indexOf(cellData.getValue()) + 1;
            return new ReadOnlyObjectWrapper<>(index);
        });
        adminIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserId()));
        fullNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        positionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJabatan()));

        tableViewAdmin.setItems(getAllAdmin());
    }

    private ObservableList<Admin> getAllAdmin() {
        String query = "SELECT id_admin, nama_lengkap, email, jabatan FROM USERS.Admin;";
        ObservableList<Admin> adminList = FXCollections.observableArrayList();
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String adminID = rs.getString("id_admin");
                String fullname = rs.getString("nama_lengkap");
                String email = rs.getString("email");
                String position = rs.getString("jabatan");
                adminList.add(new Admin(adminID, fullname, email, position));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminList;
    }

    public void openOverlayTambahAdmin(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Tambah admin baru");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-tambah-admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openOverlayEditAdmin(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Edit data admin");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-edit-data-admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openOverlayDeleteAdmin(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Hapus data admin");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminjurusan/overlay-hapus-data-admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 450);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
