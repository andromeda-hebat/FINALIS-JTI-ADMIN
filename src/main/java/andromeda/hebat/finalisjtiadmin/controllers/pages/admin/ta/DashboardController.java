package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.ta;

import andromeda.hebat.finalisjtiadmin.models.BerkasPengajuan;
import andromeda.hebat.finalisjtiadmin.repository.BerkasTARepository;
import andromeda.hebat.finalisjtiadmin.repository.StatistikRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class DashboardController {
    @FXML private HBox dashboardContainer;
    @FXML private Label totalRequest;
    @FXML private Label totalApproved;
    @FXML private Label totalRejected;
    @FXML private TableView<BerkasPengajuan> tableViewBerkasPengajuan;
    @FXML private TableColumn<BerkasPengajuan, Integer> noCol;
    @FXML private TableColumn<BerkasPengajuan, String> nimCol;
    @FXML private TableColumn<BerkasPengajuan, String> mahasiswaCol;
    @FXML private TableColumn<BerkasPengajuan, String> statusCol;
    @FXML private TableColumn<BerkasPengajuan, String> tanggalCol;
    @FXML private TableColumn<BerkasPengajuan, Void> actionCol;

    private ObservableList<BerkasPengajuan> pengajuanTAList;

    public void initialize() {
        statisticRequestInit();
        tableViewInit();
    }

    private void statisticRequestInit() {
        HashMap<String, Integer> requestStats = StatistikRepository.getStatisticRequest("TA");
        totalRequest.setText(String.valueOf(requestStats.get("total_pengajuan")));
        totalApproved.setText(String.valueOf(requestStats.get("total_disetujui")));
        totalRejected.setText(String.valueOf(requestStats.get("total_ditolak")));
    }

    private void tableViewInit() {
        pengajuanTAList = FXCollections.observableArrayList();
        pengajuanTAList.addAll(BerkasTARepository.getAllSubmittedBerkas());

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

        tableViewBerkasPengajuan.setItems(pengajuanTAList);
    }

    public void openOverlayEdit() {

    }

    public void openOverlayDelete() {

    }
}
