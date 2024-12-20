package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan;

import andromeda.hebat.finalisjtiadmin.Main;
import andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay.OverlayDeleteAdminController;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import andromeda.hebat.finalisjtiadmin.repository.AdminRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KelolaAdminController {
    @FXML private Button btnTambahkan;
    @FXML private TableView<Admin> tableViewAdmin;
    @FXML private TableColumn<Admin, Integer> numberColumn;
    @FXML private TableColumn<Admin, String> adminIDColumn;
    @FXML private TableColumn<Admin, String> fullNameColumn;
    @FXML private TableColumn<Admin, String> emailColumn;
    @FXML private TableColumn<Admin, String> positionColumn;
    @FXML private TableColumn<Admin, Void> actionColumn;
    private ObservableList<Admin> adminList;

    @FXML
    public void initialize() {
        adminList = FXCollections.observableArrayList();
        adminList.setAll(AdminRepository.getAllAdmin());

        numberColumn.setCellValueFactory((TableColumn.CellDataFeatures<Admin, Integer> cellData) -> {
            int index = tableViewAdmin.getItems().indexOf(cellData.getValue()) + 1;
            return new ReadOnlyObjectWrapper<>(index);
        });
        adminIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserId()));

        fullNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        fullNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fullNameColumn.setOnEditCommit(event -> {
            Admin admin = event.getRowValue();
            admin.setName(event.getNewValue());
            AdminRepository.updateAdminFullName(admin.getUserId(), admin.getName());
        });

        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> {
            Admin admin = event.getRowValue();
            admin.setEmail(event.getNewValue());
            AdminRepository.updateAdminEmail(admin.getUserId(), admin.getEmail());
        });
        positionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJabatan().getJenisAdminStr()));
        ObservableList<String> roles = FXCollections.observableArrayList("Admin Jurusan", "Admin TA", "Admin Prodi");
        positionColumn.setCellFactory(ComboBoxTableCell.forTableColumn(roles));
        positionColumn.setOnEditCommit(event -> {
            Admin admin = event.getRowValue();
            admin.setJabatan(event.getNewValue());
            AdminRepository.updateAdminJabatan(admin.getUserId(), admin.getJabatan().getJenisAdminStr());
        });

        actionColumn.setCellFactory(tc -> new TableCell<Admin, Void>() {
            private final Button deleteBtn = new Button("Hapus");

            {
                deleteBtn.getStyleClass().add("delete");
                deleteBtn.setOnAction(event -> {
                    Admin admin = getTableView().getItems().get(getIndex());
                    openOverlayDeleteAdmin(admin);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(5);
                    hbox.setAlignment(Pos.CENTER);
                    hbox.getChildren().addAll(deleteBtn);
                    setGraphic(hbox);
                }
            }
        });

        tableViewAdmin.setItems(adminList);
    }

    public void openOverlayTambahAdmin() {
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Tambah admin baru");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/admin/jurusan/overlay/overlay-tambah-admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openOverlayDeleteAdmin(Admin admin) {
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Hapus data admin");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/admin/jurusan/overlay/overlay-hapus-admin.fxml"));
            Parent root = fxmlLoader.load();

            OverlayDeleteAdminController controller = fxmlLoader.getController();
            controller.fillData(admin, adminList);

            Scene scene = new Scene(root, 400, 450);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
