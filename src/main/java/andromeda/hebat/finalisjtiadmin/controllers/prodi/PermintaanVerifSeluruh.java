package andromeda.hebat.finalisjtiadmin.controllers.prodi;
import andromeda.hebat.finalisjtiadmin.Main;
import andromeda.hebat.finalisjtiadmin.models.BerkasProdi;
import andromeda.hebat.finalisjtiadmin.core.Database;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class PermintaanVerifSeluruh {
    @FXML private HBox halamanPermintaanVerif;
    @FXML
    private Label judulHalaman;

    @FXML private TableView<BerkasProdi> tableViewBerkasProdi;

    @FXML private TableColumn<BerkasProdi, String> numberColumn;

    @FXML private TableColumn<BerkasProdi, String> idColumn;

    @FXML private TableColumn<BerkasProdi, String> nameColumn;

    @FXML private TableColumn<BerkasProdi, String> descriptionColumn;

    @FXML private TableColumn<BerkasProdi, String> dateColumn;

    @FXML private TableColumn<BerkasProdi, Void> actionColumn;


    private ObservableList<BerkasProdi> berkasProdiList;

    @FXML
    public void initialize() {
        berkasProdiList = FXCollections.observableArrayList();
        getAllBerkasProdi();

//        numberColumn.setCellValueFactory((TableColumn.CellDataFeatures<BerkasProdi, Integer> cellData) -> {
//            int index = tableViewBerkasProdi.getItems().indexOf(cellData.getValue()) + 1;
//            return new ReadOnlyObjectWrapper<>(index);
//        });
        numberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdBerkasProdi()));
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNim()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNamaMahasiswa()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKeterangan()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTanggalRequest()));
        actionColumn.setCellFactory(tc -> new TableCell<BerkasProdi, Void>() {
            private final Button detailBtn = new Button("Detail");

            {
                detailBtn.setOnAction(event -> {
                    BerkasProdi BerkasProdi = getTableView().getItems().get(getIndex());
                    openHalamanDetail();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(5);
                    hbox.getChildren().addAll(detailBtn);
                    setGraphic(hbox);
                }
            }
        });

        tableViewBerkasProdi.setItems(berkasProdiList);
    }
    private void getAllBerkasProdi() {
        String query = """
                SELECT m.nim, nama_lengkap mahasiswa, 'kosong' keterangan, tanggal_request
                from USERS.Mahasiswa m
                INNER JOIN BERKAS.Prodi p ON p.nim = m.nim
           """;
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nim = rs.getString("nim");
                String mahasiswa = rs.getString("mahasiswa");
                String keterangan = rs.getString("keterangan");
                String tanggalRequest = rs.getString("tanggal_request");

                berkasProdiList.add(new BerkasProdi(nim, mahasiswa, keterangan, tanggalRequest));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openHalamanDetail() {
        try {
            Stage overlay = (Stage) halamanPermintaanVerif.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/adminprodi/detail-permintaan-verif.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
