package andromeda.hebat.finalisjtiadmin.controllers.admin.ta;

import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import andromeda.hebat.finalisjtiadmin.models.BerkasTA;
import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.repository.BerkasTARepository;
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
    @FXML private TableView<BerkasPengajuan> tableViewBerkasTA;
    @FXML private TableColumn<BerkasPengajuan, Integer> noCol;
    @FXML private TableColumn<BerkasPengajuan, String> nimCol;
    @FXML private TableColumn<BerkasPengajuan, String> mahasiswaCol;
    @FXML private TableColumn<BerkasPengajuan, String> statusCol;
    @FXML private TableColumn<BerkasPengajuan, String> tanggalCol;
    @FXML private TableColumn<BerkasPengajuan, Void> actionCol;

    private ObservableList<BerkasPengajuan> pengajuanTAList;

    @FXML
    public void initialize() {
        tableViewInit();
    }

    private void tableViewInit() {
        pengajuanTAList = FXCollections.observableArrayList();
        pengajuanTAList.addAll(BerkasTARepository.getAllSubmittedBerkas());

        tableViewBerkasTA.getColumns().forEach(column -> column.setReorderable(false));
        noCol.setCellValueFactory((TableColumn.CellDataFeatures<BerkasPengajuan, Integer> cellData) -> {
            int index = tableViewBerkasTA.getItems().indexOf(cellData.getValue()) + 1;
            return new ReadOnlyObjectWrapper<>(index);
        });
        nimCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNim()));
        mahasiswaCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNamaLengkap()));
        statusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatusVerifikasi()));
        tanggalCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTanggalRequest()));
        actionCol.setCellFactory(tc -> new TableCell<BerkasPengajuan, Void>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Hapus");

            {
                editBtn.getStyleClass().add("edit");
                editBtn.setOnAction(event -> {
                    openOverlayEdit();
                });

                deleteBtn.getStyleClass().add("delete");
                deleteBtn.setOnAction(event -> {
                    openOverlayDelete();
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

        tableViewBerkasTA.setItems(pengajuanTAList);
    }


    private void openOverlayEdit() {

    }

    private void openOverlayDelete() {

    }

}
