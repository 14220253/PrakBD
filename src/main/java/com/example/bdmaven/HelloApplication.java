package com.example.bdmaven;

import controllers.CustomerController;
import controllers.DeliveryController;
import controllers.TransactionController;
import formController.FormDeliveryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tabelDelivery.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        fxmlLoader.<DeliveryController>getController().setScene(scene);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}