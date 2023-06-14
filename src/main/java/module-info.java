module com.example.bdmaven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.bdmaven to javafx.fxml;
    exports com.example.bdmaven;
    exports controllers;
    opens controllers to javafx.fxml;
    exports formController;
    opens formController to javafx.fxml;

}