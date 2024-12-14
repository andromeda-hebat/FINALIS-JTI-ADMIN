package andromeda.hebat.finalisjtiadmin.controllers.jurusan;

import andromeda.hebat.finalisjtiadmin.core.Database;
import andromeda.hebat.finalisjtiadmin.models.Admin;
import andromeda.hebat.finalisjtiadmin.models.LogAktivitas;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class LogAktivitasController {
    @FXML
    private TableView<LogAktivitas> tableViewLogAktivitas;

    @FXML
    private TableColumn<LogAktivitas, Integer> columnID;

    @FXML
    private TableColumn<LogAktivitas, String> columnIDAdmin;

    @FXML
    private TableColumn<LogAktivitas, String> columnIDBerkas;

    @FXML
    private TableColumn<LogAktivitas, String> columnStatusSebelum;

    @FXML
    private TableColumn<LogAktivitas, String> columnStatusSesudah;

    @FXML
    private TableColumn<LogAktivitas, String> columnWaktu;

    @FXML
    private ObservableList<LogAktivitas> logAktivitasList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        logAktivitasList = FXCollections.observableArrayList();
        getAllLogAktivitas();

        columnID.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getId())
        );

        columnIDAdmin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdAdmin()));
        columnIDBerkas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdBerkas()));
        columnStatusSebelum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatusSebelum()));
        columnStatusSesudah.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatusSesudah()));
        columnWaktu.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTanggal()));

        tableViewLogAktivitas.setItems(logAktivitasList);
    }

    private void getAllLogAktivitas(){
        String query = "SELECT id_log, id_admin, id_berkas, status_sebelumnya, status_sesudahnya, waktu_aktivitas FROM VER.LogAktivitas";
        try (Statement stmt = Database.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData metaData = rs.getMetaData();
            // Debug nama kolom
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.println(metaData.getColumnName(i));
            }
            while (rs.next()) {
                Integer id = rs.getInt("id_log");
                String idAdmin = rs.getString("id_admin");
                String idBerkas = rs.getString("id_berkas");
                String statusSebelum = rs.getString("status_sebelumnya");
                String statusSesudah = rs.getString("status_sesudahnya");
                String tanggal = rs.getString("waktu_aktivitas");
                logAktivitasList.add(new LogAktivitas( id, idAdmin, idBerkas, statusSebelum, statusSesudah, tanggal));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
