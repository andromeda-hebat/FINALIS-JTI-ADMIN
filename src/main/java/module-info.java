module farrel.ad.finalisjtiadmin {
    requires javafx.controls;
    requires javafx.fxml;


    opens andromeda.hebat.finalisjtiadmin to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin;
}