package com.example.bdmaven;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class HelloController {
    @FXML
    private Label label;

    @FXML
    protected void onHelloButtonClick() {
        connectSQL();
    }

    @FXML
    protected void connectSQL() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mahasiswa", "root", "");
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM mahasiswa");

            while (result.next()) {
                label.setText(result.getString("NRP"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}