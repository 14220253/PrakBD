package com.example.bdmaven;

import controllers.CustomerController;
import controllers.DeliveryController;
import controllers.MenuController;
import controllers.TransactionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        fxmlLoader.<MenuController>getController().setScene(scene);
        fxmlLoader.<MenuController>getController().setApp(this);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}