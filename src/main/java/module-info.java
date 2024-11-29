module andromeda.hebat.finalisjtiadmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;


    exports andromeda.hebat.finalisjtiadmin;
    opens andromeda.hebat.finalisjtiadmin to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin.models;
    opens andromeda.hebat.finalisjtiadmin.models to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin.controllers;
    opens andromeda.hebat.finalisjtiadmin.controllers to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin.controllers.jurusan;
    opens andromeda.hebat.finalisjtiadmin.controllers.jurusan to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin.controllers.jurusan.overlay;
    opens andromeda.hebat.finalisjtiadmin.controllers.jurusan.overlay to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin.controllers.prodi;
    opens andromeda.hebat.finalisjtiadmin.controllers.prodi to javafx.fxml;
    exports andromeda.hebat.finalisjtiadmin.controllers.ta;
    opens andromeda.hebat.finalisjtiadmin.controllers.ta to javafx.fxml;
}