package andromeda.hebat.finalisjtiadmin.controllers.pages.admin.ta;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class DetailPermintaanTAVerifikasiController {
    @FXML private RadioButton acceptRadioBtn;
    @FXML private RadioButton rejectRadioBtn;

    private ToggleGroup toggleGroup;

    @FXML
    private void initialize() {
        toggleGroup = new ToggleGroup();

        acceptRadioBtn.setToggleGroup(toggleGroup);
        rejectRadioBtn.setToggleGroup(toggleGroup);
    }
}
