module andromeda.hebat.finalisjtiadmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens andromeda.hebat.finalisjtiadmin to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin;
    exports andromeda.hebat.finalisjtiadmin.controllers;
    opens andromeda.hebat.finalisjtiadmin.controllers to javafx.fxml;
}