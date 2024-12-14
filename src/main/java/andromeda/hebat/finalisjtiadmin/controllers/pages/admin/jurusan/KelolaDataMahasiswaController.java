package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan;

import andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay.OverlayEditDataMahasiswa;
import andromeda.hebat.finalisjtiadmin.Main;
import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Mahasiswa;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;


public class KelolaDataMahasiswaController {
    @FXML
    private Button btnTambahkanMhs;

    @FXML
    private TableView<Mahasiswa> tableViewMahasiswa;

    @FXML
    private TableColumn<Mahasiswa, Integer> columnNo;

    @FXML
    private TableColumn<Mahasiswa, String> columnNim;

    @FXML
    private TableColumn<Mahasiswa, String> columnNama;

    @FXML
    private TableColumn<Mahasiswa, String> columnPassword;

    @FXML
    private TableColumn<Mahasiswa, String> columnEmail;

    @FXML
    private TableColumn<Mahasiswa, String> columnJurusan;

    @FXML
    private TableColumn<Mahasiswa, String> columnProdi;

    @FXML
    private TableColumn<Mahasiswa, Void> columnAction;

    private ObservableList<Mahasiswa> mahasiswaList = FXCollections.observableArrayList();
    private ComboBox<Object> actionColumn;

    @FXML
    public void initialize() {
        btnTambahkanMhs.setOnAction(event -> openOverlayTambahMahasiswa());
        mahasiswaList = FXCollections.observableArrayList();
        getAllMahasiswa();

        columnNo.setCellValueFactory((TableColumn.CellDataFeatures<Mahasiswa, Integer> cellData) -> {
            int index = tableViewMahasiswa.getItems().indexOf(cellData.getValue()) + 1;
            return new ReadOnlyObjectWrapper<>(index);
        });
        columnNim.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNim()));
        columnNama.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNama()));
        columnPassword.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        columnEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        columnJurusan.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJurusan()));
        columnProdi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdi()));

        columnAction.setCellFactory(tc -> new TableCell<Mahasiswa, Void>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Hapus");

            {

                editBtn.setOnAction(event -> {
                    Mahasiswa mahasiswa = getTableView().getItems().get(getIndex());
                    openOverlayEditMahasiswa(mahasiswa);
                });


                deleteBtn.setOnAction(event -> {
                    Mahasiswa mahasiswa = getTableView().getItems().get(getIndex());
                    openOverlayHapusMahasiswa(mahasiswa);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(5);
                    hbox.getChildren().addAll(editBtn, deleteBtn);
                    setGraphic(hbox);
                }
            }
        });

        tableViewMahasiswa.setItems(mahasiswaList);
    }

    private void getAllMahasiswa() {
        String query = "SELECT * FROM USERS.Mahasiswa";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama_lengkap");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String jurusan = rs.getString("jurusan");
                String prodi = rs.getString("prodi");
                mahasiswaList.add(new Mahasiswa(nim, nama, password, email, jurusan, prodi));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void openOverlayTambahMahasiswa(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Tambah Data Mahasiswa");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/adminjurusan/overlay/overlay-tambah-data-mahasiswa.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openOverlayEditMahasiswa(Mahasiswa mahasiswa){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Edit Data mahasiswa");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/adminjurusan/overlay/overlay-edit-data-mahasiswa.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);

            OverlayEditDataMahasiswa controller = fxmlLoader.getController();

            controller.fillData(mahasiswa);

            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openOverlayHapusMahasiswa(Mahasiswa mahasiswa){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Hapus Data Mahasiswa");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/adminjurusan/overlay/overlay-hapus-data-mahasiswa.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 450);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
