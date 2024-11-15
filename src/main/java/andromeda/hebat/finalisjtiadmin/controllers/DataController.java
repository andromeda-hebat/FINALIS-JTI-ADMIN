package andromeda.hebat.finalisjtiadmin.controllers;

import andromeda.hebat.finalisjtiadmin.core.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class DataController {

    @FXML
    private Button loadDataBtn;

    @FXML
    private TextArea dataTextArea;
    @FXML
    public void initialize() {
        loadDataBtn.setOnAction(e -> {
            String data = Database.getUserData();
            dataTextArea.setText(data.isEmpty() ? "No data found." : data);
        });

    }
}
