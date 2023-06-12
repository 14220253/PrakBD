package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuController {
    private Scene scene;

    @FXML
public void transaction(){
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listTransaction.fxml"));
        scene.setRoot((Parent) loader.load());


    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
}

