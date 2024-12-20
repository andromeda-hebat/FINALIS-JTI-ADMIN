package andromeda.hebat.finalisjtiadmin.controllers.components.admin;

import andromeda.hebat.finalisjtiadmin.models.Admin;
import andromeda.hebat.finalisjtiadmin.session.UserSession;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

public class TopbarController {
    private static Admin adminObj;
    @FXML private ImageView userProfileImg;
    @FXML private Label username;
    @FXML private Label userRole;

    @FXML
    private void initialize() {
        username.setText(UserSession.getInstance().getAdmin().getName());
        userRole.setText(UserSession.getInstance().getAdmin().getJabatan().getJenisAdminStr());

        byte[] imgBytes = Base64.getDecoder().decode(UserSession.getInstance().getAdmin().getFotoProfil());

        InputStream is = new ByteArrayInputStream(imgBytes);
        Image img = new Image(is);
        userProfileImg.setImage(img);
    }
}