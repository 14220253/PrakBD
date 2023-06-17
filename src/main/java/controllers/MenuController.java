package controllers;

import formController.formItemDetailsController;
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

            listItemDetailsController.refreshTable();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void customer(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelCustomer.fxml"));
            scene.setRoot((Parent) loader.load());

            CustomerController customerController = loader.getController();
            customerController.setScene(scene);

            customerController.refreshTable();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void kategori(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listKategori.fxml"));
            scene.setRoot((Parent) loader.load());

            KategoriController kategoriController = loader.getController();
            kategoriController.setScene(scene);
            kategoriController.refreshTable();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Delivery(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelDelivery.fxml"));
            scene.setRoot((Parent) loader.load());

            DeliveryController deliveryControllerController = loader.getController();
            deliveryControllerController.setScene(scene);
            deliveryControllerController.refreshTable();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void jobs(){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelJobs.fxml"));
        scene.setRoot((Parent) loader.load());

        JobsController jobsController = loader.getController();
        jobsController.setScene(scene);
        jobsController.refreshTable();

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }

    public Scene getScene() {
        return scene;
    }


}

