package andromeda.hebat.finalisjtiadmin.controllers.components.admin;

import andromeda.hebat.finalisjtiadmin.helper.SceneHelper;
import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import andromeda.hebat.finalisjtiadmin.repository.BerkasProdiRepository;
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

import java.util.ArrayList;

public class TabelPengajuanController {
    @FXML private TableView<BerkasPengajuan> tabelBerkasPengajuan;
    @FXML private TableColumn<BerkasPengajuan, Integer> noCol;
    @FXML private TableColumn<BerkasPengajuan, String> nimCol;
    @FXML private TableColumn<BerkasPengajuan, String> mahasiswaCol;
    @FXML private TableColumn<BerkasPengajuan, String> statusCol;
    @FXML private TableColumn<BerkasPengajuan, String> tanggalCol;
    @FXML private TableColumn<BerkasPengajuan, Void> actionCol;

    private String fileCategory;
    private boolean isAllData;

    private ObservableList<BerkasPengajuan> pengajuanList;

    @FXML
    private void initialize() {
    }

    public void setFileType(String fileCategory, boolean isAllData) {
        this.fileCategory = fileCategory;
        this.isAllData = isAllData;
        tableViewInit();
    }

    private void tableViewInit() {
        pengajuanList = FXCollections.observableArrayList();

        ArrayList<BerkasPengajuan> dataInit = new ArrayList<>();
        if (this.fileCategory.equalsIgnoreCase("Berkas TA") && this.isAllData) {
            pengajuanList.addAll(BerkasTARepository.getAllBerkas());
        } else if (this.fileCategory.equalsIgnoreCase("Berkas Prodi") && this.isAllData) {
            pengajuanList.addAll(BerkasProdiRepository.getAllBerkas());
        } else if (this.fileCategory.equalsIgnoreCase("Berkas TA") && !this.isAllData) {
            pengajuanList.addAll(BerkasTARepository.getAllSubmittedBerkas());
        } else if (this.fileCategory.equalsIgnoreCase("Berkas Prodi") && !this.isAllData) {
            pengajuanList.addAll(BerkasProdiRepository.getAllSubmittedBerkas());
        }

        tabelBerkasPengajuan.getColumns().forEach(column -> column.setReorderable(false));
        noCol.setCellValueFactory((TableColumn.CellDataFeatures<BerkasPengajuan, Integer> cellData) -> {
            int index = tabelBerkasPengajuan.getItems().indexOf(cellData.getValue()) + 1;
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
                    SceneHelper.changeRootNodeScene(tabelBerkasPengajuan.getScene(), "/views/pages/admin/ta/detail-permintaan-verifikasi.fxml");
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

        tabelBerkasPengajuan.setItems(pengajuanList);
    }
}
