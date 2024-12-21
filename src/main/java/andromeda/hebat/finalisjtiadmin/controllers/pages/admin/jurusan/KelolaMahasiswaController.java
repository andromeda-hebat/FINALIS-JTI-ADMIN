package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan;

import andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay.OverlayDeleteMahasiswa;
import andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay.OverlayEditMahasiswa;
import andromeda.hebat.finalisjtiadmin.Main;
import andromeda.hebat.finalisjtiadmin.models.Mahasiswa;
import andromeda.hebat.finalisjtiadmin.repository.MahasiswaRepository;
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


public class KelolaMahasiswaController {
    @FXML private Button btnTambahkanMhs;
    @FXML private TableView<Mahasiswa> tableViewMahasiswa;
    @FXML private TableColumn<Mahasiswa, Integer> columnNo;
    @FXML private TableColumn<Mahasiswa, String> columnNim;
    @FXML private TableColumn<Mahasiswa, String> columnNama;
    @FXML private TableColumn<Mahasiswa, String> columnEmail;
    @FXML private TableColumn<Mahasiswa, String> columnJurusan;
    @FXML private TableColumn<Mahasiswa, String> columnProdi;
    @FXML private TableColumn<Mahasiswa, String> columnTahunMasuk;
    @FXML private TableColumn<Mahasiswa, Void> columnAction;
    private ObservableList<Mahasiswa> mahasiswaList;
    private ComboBox<Object> actionColumn;

    @FXML
    public void initialize() {
        btnTambahkanMhs.setOnAction(event -> openOverlayTambahMahasiswa());
        mahasiswaList = FXCollections.observableArrayList();
        mahasiswaList.setAll(MahasiswaRepository.getAllMahasiswa());

        columnNo.setCellValueFactory((TableColumn.CellDataFeatures<Mahasiswa, Integer> cellData) -> {
            int index = tableViewMahasiswa.getItems().indexOf(cellData.getValue()) + 1;
            return new ReadOnlyObjectWrapper<>(index);
        });
        columnNim.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNim()));
        columnNama.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNama()));
        columnEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        columnJurusan.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJurusan()));
        columnProdi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdi()));
        columnTahunMasuk.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTahunAngkatan()));

        columnAction.setCellFactory(tc -> new TableCell<Mahasiswa, Void>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Hapus");

            {
                editBtn.getStyleClass().add("edit");
                editBtn.setOnAction(event -> {
                    Mahasiswa mahasiswa = getTableView().getItems().get(getIndex());
                    openOverlayEditMahasiswa(mahasiswa);
                });

                deleteBtn.getStyleClass().add("delete");
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


    public void openOverlayTambahMahasiswa(){
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Tambah Data Mahasiswa");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/admin/jurusan/overlay/overlay-tambah-mahasiswa.fxml"));
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

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/admin/jurusan/overlay/overlay-edit-mahasiswa.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);

            OverlayEditMahasiswa controller = fxmlLoader.getController();

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

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/admin/jurusan/overlay/overlay-hapus-mahasiswa.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 450);

            OverlayDeleteMahasiswa overlayController = fxmlLoader.getController();
            overlayController.fillData(mahasiswa);

            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
