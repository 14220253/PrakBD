module com.example.bdmaven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.bdmaven to javafx.fxml;
    exports com.example.bdmaven;
    exports controllers;
    opens controllers to javafx.fxml;
    exports formController;
    opens formController to javafx.fxml;
    opens DAO to javafx.fxml;
    exports queryController;
    opens queryController to javafx.fxml;

}