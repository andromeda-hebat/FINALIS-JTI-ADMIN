module farrel.ad.finalisjtiadmin {
    requires javafx.controls;
    requires javafx.fxml;


    opens farrel.ad.finalisjtiadmin to javafx.fxml;
    exports farrel.ad.finalisjtiadmin;
}