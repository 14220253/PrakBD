package com.example.bdmaven;

import controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tabelSortedCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        fxmlLoader.<SortedCustomerController>getController().setScene(scene);
        fxmlLoader.<SortedCustomerController>getController().setApp(this);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}