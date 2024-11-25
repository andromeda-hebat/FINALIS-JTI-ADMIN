module andromeda.hebat.finalisjtiadmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;


    opens andromeda.hebat.finalisjtiadmin to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin;
    exports andromeda.hebat.finalisjtiadmin.controllers;
    opens andromeda.hebat.finalisjtiadmin.controllers to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin.controllers.jurusan;
    opens andromeda.hebat.finalisjtiadmin.controllers.jurusan to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin.models;
    opens andromeda.hebat.finalisjtiadmin.models to javafx.fxml;
}