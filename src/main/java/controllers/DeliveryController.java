package controllers;

import DAO.DeliveryDAO;
import com.example.bdmaven.HelloApplication;
import com.example.bdmaven.JDBC;
import entity.Customer;
import entity.Delivery;
import formController.FormDeliveryController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeliveryController {
    private HelloApplication app;
    private Scene scene;
    @FXML
   private TableView<Delivery> table;
    private ObservableList<Delivery> list = FXCollections.observableArrayList();
    private static final DeliveryDAO DAO = new DeliveryDAO();
    PreparedStatement statement;
    ResultSet result;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void initialize() {
        TableColumn<Delivery, String> idCol = new TableColumn<>("ID_DELIVERY");
        TableColumn<Delivery, String> dateCol = new TableColumn<>("TANGGAL_PENGEMBALIAN");
        TableColumn<Delivery, String> empCol = new TableColumn<>("EMPLOYEE_ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().id_deliveryProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().tanggal_pengembalianProperty());
        empCol.setCellValueFactory(cellData -> cellData.getValue().employee_idProperty());
        idCol.setPrefWidth(60);
        dateCol.setPrefWidth(200);
        empCol.setPrefWidth(60);

        table.getColumns().clear();
        table.getColumns().add(idCol);
        table.getColumns().add(dateCol);
        table.getColumns().add(empCol);

        table.setPlaceholder(new Label("No content in table"));
        list.setAll(DAO.getAllDelivery());
        table.setItems(list);

    }


    @FXML
    protected void addData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/bdmaven/formDelivery.fxml"));
        scene.setRoot(loader.load());
        FormDeliveryController deliveryController = loader.getController();
    }
}
