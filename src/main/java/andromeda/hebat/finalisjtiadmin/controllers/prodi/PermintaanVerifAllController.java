package andromeda.hebat.finalisjtiadmin.controllers.prodi;
import andromeda.hebat.finalisjtiadmin.models.BerkasProdi;
import andromeda.hebat.finalisjtiadmin.core.Database;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.Statement;

public class PermintaanVerifAllController {
    @FXML
    private Label judulHalaman;

    @FXML private TableView<BerkasProdi> tableViewBerkasProdi;

    @FXML private TableColumn<BerkasProdi, Integer> numberColumn;

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

        numberColumn.setCellValueFactory((TableColumn.CellDataFeatures<BerkasProdi, Integer> cellData) -> {
            int index = tableViewBerkasProdi.getItems().indexOf(cellData.getValue()) + 1;
            return new ReadOnlyObjectWrapper<>(index);
        });
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNim()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNamaMahasiswa()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKeterangan()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTanggalRequest()));
        actionColumn.setCellFactory(tc -> new TableCell<BerkasProdi, Void>() {
            private final Button detailBtn = new Button("Detail");

            {
                detailBtn.setOnAction(event -> {
                    BerkasProdi BerkasProdi = getTableView().getItems().get(getIndex());
                    System.out.println("detail button telah diklik");
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
                SELECT
                     	M.nim AS NIM,
                     	M.nama_lengkap AS 'Mahasiswa',
                     	V.keterangan_verifikasi AS 'Aktivitas',
                     	P.tanggal_request AS 'Tanggal Request'
                     FROM USERS.Mahasiswa M
                     INNER JOIN BERKAS.Prodi P ON M.nim = P.nim
                     INNER JOIN VER.VerifikasiBerkas V ON V.id_berkas = P.id_berkas_prodi
                     WHERE status_verifikasi = 'Diajukan'
                     ORDER BY tanggal_request DESC;
           """;
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nim = rs.getString("NIM");
                String mahasiswa = rs.getString("Mahasiswa");
                String keterangan = rs.getString("Aktivitas");
                String tanggalRequest = rs.getString("Tanggal Request");

                berkasProdiList.add(new BerkasProdi(nim, mahasiswa, keterangan, tanggalRequest));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
