package andromeda.hebat.finalisjtiadmin.controllers.prodi;
import andromeda.hebat.finalisjtiadmin.models.BerkasProdi;
import andromeda.hebat.finalisjtiadmin.core.Database;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class PermintaanVerifSeluruh {
    @FXML
    private Label judulHalaman;

    @FXML private TableView<BerkasProdi> tableViewBerkasProdi;

    @FXML private TableColumn<BerkasProdi, Integer> numberColumn;

    @FXML private TableColumn<BerkasProdi, String> idColumn;

    @FXML private TableColumn<BerkasProdi, String> nameColumn;

    @FXML private TableColumn<BerkasProdi, String> descriptionColumn;

    @FXML private TableColumn<BerkasProdi, String> dateColumn;

    @FXML private TableColumn<BerkasProdi, Void> actionColumn;


    private ObservableList<BerkasProdi> BerkasProdiList;

    @FXML
    public void initialize() {
        BerkasProdiList = FXCollections.observableArrayList();

        numberColumn.setCellValueFactory((TableColumn.CellDataFeatures<BerkasProdi, Integer> cellData) -> {
//            int index = tableViewBerkasProdi.getItems().indexOf(cellData.getValue()) + 1;
            int index = 1;
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

        tableViewBerkasProdi.setItems(BerkasProdiList);
    }
    private void getAllBerkasProdi() {
        String query = "SELECT * FROM USERS.BerkasProdi;";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String BerkasProdiID = rs.getString("id_BerkasProdi");
                String fullname = rs.getString("nama_lengkap");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String position = rs.getString("jabatan");
//                BerkasProdiList.add(new BerkasProdi(BerkasProdiID, fullname, password, email, position));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
