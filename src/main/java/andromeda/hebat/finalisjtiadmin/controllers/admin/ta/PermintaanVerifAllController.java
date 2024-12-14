package andromeda.hebat.finalisjtiadmin.controllers.admin.ta;
import andromeda.hebat.finalisjtiadmin.models.BerkasTA;
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

    @FXML private TableView<BerkasTA> tableViewBerkasTA;

    @FXML private TableColumn<BerkasTA, Integer> numberColumn;

    @FXML private TableColumn<BerkasTA, String> idColumn;

    @FXML private TableColumn<BerkasTA, String> nameColumn;

    @FXML private TableColumn<BerkasTA, String> descriptionColumn;

    @FXML private TableColumn<BerkasTA, String> dateColumn;

    @FXML private TableColumn<BerkasTA, Void> actionColumn;


    private ObservableList<BerkasTA> berkasTAList;

    @FXML
    public void initialize() {
        berkasTAList = FXCollections.observableArrayList();
        getAllBerkasTA();

        numberColumn.setCellValueFactory((TableColumn.CellDataFeatures<BerkasTA, Integer> cellData) -> {
            int index = tableViewBerkasTA.getItems().indexOf(cellData.getValue()) + 1;
            return new ReadOnlyObjectWrapper<>(index);
        });
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNim()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNamaMahasiswa()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKeterangan()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTanggalRequest()));
        actionColumn.setCellFactory(tc -> new TableCell<BerkasTA, Void>() {
            private final Button detailBtn = new Button("Detail");

            {
                detailBtn.setOnAction(event -> {
                    BerkasTA BerkasTA = getTableView().getItems().get(getIndex());
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

        tableViewBerkasTA.setItems(berkasTAList);
    }
    private void getAllBerkasTA() {
        String query = """
                SELECT m.nim, nama_lengkap mahasiswa, 'kosong' keterangan, tanggal_request
                from USERS.Mahasiswa m
                INNER JOIN BERKAS.TA p ON p.nim = m.nim
           """;
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nim = rs.getString("nim");
                String mahasiswa = rs.getString("mahasiswa");
                String keterangan = rs.getString("keterangan");
                String tanggalRequest = rs.getString("tanggal_request");

                berkasTAList.add(new BerkasTA(nim, mahasiswa, keterangan, tanggalRequest));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
