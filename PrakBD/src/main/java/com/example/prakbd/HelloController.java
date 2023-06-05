package com.example.prakbd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class HelloController {
    @FXML
    private Button button;
    @FXML
    private Label label;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        connectSQL(event);
    }

    @FXML
    protected void connectSQL(ActionEvent event) {
        try {
            Class.forName("java.sql.Connection");
            Class.forName("java.sql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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