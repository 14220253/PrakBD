package controllers;

import com.example.bdmaven.HelloApplication;
import com.example.bdmaven.ListItemDetailsController;
import entity.Delivery;
import formController.formItemDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    private Scene scene;
    private HelloApplication app;

    public void setApp(HelloApplication app) {this.app = app;}

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
    @FXML
    public void delivery(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("tabelDelivery.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 400));
            loader.<DeliveryController>getController().setApp(app);
            loader.<DeliveryController>getController().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void employee(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("tabelEmployees.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 400));
            loader.<EmployeeController>getController().setApp(app);
            loader.<EmployeeController>getController().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void jobs(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("tabelJobs.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 400));
            loader.<JobsController>getController().setApp(app);
            loader.<JobsController>getController().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void hargaDelivery(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("tabelHargaDelivery.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 400));
            loader.<HargaDeliveryController>getController().setApp(app);
            loader.<HargaDeliveryController>getController().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Scene getScene() {
        return scene;
    }


}

