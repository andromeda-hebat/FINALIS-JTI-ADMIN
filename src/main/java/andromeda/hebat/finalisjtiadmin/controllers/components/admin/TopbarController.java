package andromeda.hebat.finalisjtiadmin.controllers.components.admin;

import andromeda.hebat.finalisjtiadmin.models.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TopbarController {
    private Admin adminObj;
    @FXML private ImageView userProfileImg;
    @FXML private Label username;
    @FXML private Label userRole;

    public void initData(Admin adminObj) {
        this.adminObj = adminObj;

        username.setText(adminObj.getName());
        userRole.setText(adminObj.getJabatan().getJenisAdminStr());
    }
}