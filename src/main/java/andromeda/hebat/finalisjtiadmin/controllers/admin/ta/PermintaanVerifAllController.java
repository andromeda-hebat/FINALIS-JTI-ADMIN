package andromeda.hebat.finalisjtiadmin.controllers.admin.ta;

import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import andromeda.hebat.finalisjtiadmin.repository.BerkasTARepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class PermintaanVerifAllController {
    @FXML
    private TableView<BerkasPengajuan> tableViewBerkasPengajuan;
    @FXML private TableColumn<BerkasPengajuan, Integer> noCol;
    @FXML private TableColumn<BerkasPengajuan, String> nimCol;
    @FXML private TableColumn<BerkasPengajuan, String> mahasiswaCol;
    @FXML private TableColumn<BerkasPengajuan, String> statusCol;
    @FXML private TableColumn<BerkasPengajuan, String> tanggalCol;
    @FXML private TableColumn<BerkasPengajuan, Void> actionCol;

    private ObservableList<BerkasPengajuan> pengajuanList;

    @FXML
    public void initialize() {
        tableViewInit();
    }

    private void tableViewInit() {
        pengajuanList = FXCollections.observableArrayList();
        pengajuanList.addAll(BerkasTARepository.getAllSubmittedBerkas());

        tableViewBerkasPengajuan.getColumns().forEach(column -> column.setReorderable(false));
        noCol.setCellValueFactory((TableColumn.CellDataFeatures<BerkasPengajuan, Integer> cellData) -> {
            int index = tableViewBerkasPengajuan.getItems().indexOf(cellData.getValue()) + 1;
            return new ReadOnlyObjectWrapper<>(index);
        });
        nimCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNim()));
        mahasiswaCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNamaLengkap()));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatusVerifikasi()));
        tanggalCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTanggalRequest()));
        actionCol.setCellFactory(tc -> new TableCell<BerkasPengajuan, Void>() {
            private final Button detailBtn = new Button("Detail");

            {
                detailBtn.getStyleClass().add("detail");
                detailBtn.setOnAction(event -> {
                    System.out.println("Detail button clicked!");
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

        tableViewBerkasPengajuan.setItems(pengajuanList);
    }

}
