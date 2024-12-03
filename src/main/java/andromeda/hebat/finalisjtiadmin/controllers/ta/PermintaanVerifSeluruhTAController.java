package andromeda.hebat.finalisjtiadmin.controllers.ta;

import andromeda.hebat.finalisjtiadmin.Main;
import andromeda.hebat.finalisjtiadmin.models.VerifTA;
import javafx.fxml.FXML;
import andromeda.hebat.finalisjtiadmin.controllers.jurusan.overlay.OverlayDeleteDataAdmin;
import andromeda.hebat.finalisjtiadmin.controllers.jurusan.overlay.OverlayEditDataAdmin;
import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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

import java.sql.ResultSet;
import java.sql.Statement;

public class PermintaanVerifSeluruhTAController {

    @FXML private Button btnDetail;

    @FXML private TableView<VerifTA> TableViewPermintaan;

    @FXML private TableColumn<VerifTA, Integer> KolomNo;

    @FXML private TableColumn<VerifTA, String> KolomNIM;

    @FXML private TableColumn<VerifTA, String> KolomNamaMahasiswa;

    @FXML private TableColumn<VerifTA, String> KolomAktivitas;

    @FXML private TableColumn<VerifTA, String> KolomTanggalRequest;

    public void initialize(){

    }


}
