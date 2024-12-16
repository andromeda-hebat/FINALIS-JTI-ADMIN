package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan;

import andromeda.hebat.finalisjtiadmin.Main;
import andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay.OverlayDeleteDataSurat;
import andromeda.hebat.finalisjtiadmin.controllers.pages.admin.jurusan.overlay.OverlayEditDataSurat;
import andromeda.hebat.finalisjtiadmin.models.Surat;
import andromeda.hebat.finalisjtiadmin.repository.BerkasRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KelolaTemplateSuratController {
    @FXML private Button btnTambahSuratBaru;
    @FXML private TableView<Surat> tableViewSurat;
    @FXML private TableColumn<Surat, Integer> idSuratColumn;
    @FXML private  TableColumn<Surat, String> namaSuratColumn;
    @FXML private TableColumn<Surat, String> keperluanColumn;
    @FXML private TableColumn<Surat, String> filePathColumn;
    @FXML private TableColumn<Surat, Void> actionColumn;
    private ObservableList<Surat> suratList;

    public void initialize() {
        suratList = BerkasRepository.getAllTemplateSurat();

        idSuratColumn.setCellValueFactory(cellData -> cellData.getValue().idSuratProperty().asObject());
        namaSuratColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNamaSurat()));
        keperluanColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKeperluan()));
        filePathColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFileSurat()));
        actionColumn.setCellFactory(tc -> new TableCell<Surat, Void>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Hapus");

            {
                editBtn.setOnAction(event -> {
                    Surat surat = getTableView().getItems().get(getIndex());
                    openOverlayEditSurat(surat);
                });

                deleteBtn.setOnAction(event -> {
                    Surat surat = getTableView().getItems().get(getIndex());
                    openOverlayDeleteSurat(surat);
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

        tableViewSurat.setItems(suratList);
    }

    public void openOverlayEditSurat(Surat surat) {
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Edit data surat");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/admin/jurusan/overlay/overlay-edit-data-template-surat.fxml"));
            Parent root = fxmlLoader.load();

            OverlayEditDataSurat controller = fxmlLoader.getController();
            controller.fillData(surat);

            Scene scene = new Scene(root, 500, 600);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openOverlayDeleteSurat(Surat surat) {
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Hapus data surat");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/admin/jurusan/overlay/overlay-hapus-data-surat.fxml"));
            Parent root = fxmlLoader.load();

            OverlayDeleteDataSurat controller = fxmlLoader.getController();
            controller.fillData(surat);

            Scene scene = new Scene(root, 400, 450);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnTambahSuratBaruOnClicked() {
        try {
            Stage overlay = new Stage();
            overlay.setTitle("Tambah Template Surat Baru");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pages/admin/jurusan/overlay/overlay-tambah-template-surat.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 700);
            overlay.setScene(scene);
            overlay.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
