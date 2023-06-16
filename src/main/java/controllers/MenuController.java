package controllers;

import com.example.bdmaven.ListItemDetailsController;
import entity.ItemDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuController {
    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    formItemDetailsController FormItemDetailsController = new formItemDetailsController();
    @FXML
    public void listitemdetails(){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listItemdetails.fxml"));
            scene.setRoot((Parent) loader.load());
            ListItemDetailsController listItemDetailsController = loader.getController();
            listItemDetailsController.setScene(scene);
            listItemDetailsController.setList(FormItemDetailsController.getListitemdetails());
            listItemDetailsController.refreshTable();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Scene getScene() {
        return scene;
    }


}

