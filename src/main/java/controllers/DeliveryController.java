package controllers;

import DAO.DeliveryDAO;
import com.example.bdmaven.HelloApplication;
import entity.Delivery;
import formController.FormDeliveryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class DeliveryController {
    private HelloApplication app;
    @FXML
   private TableView<Delivery> table;
    private ObservableList<Delivery> list = FXCollections.observableArrayList();
    private static final DeliveryDAO DAO = new DeliveryDAO();
    private Delivery selectedDelivery;
    private Scene scene;
    public void setScene(Scene scene) {this.scene = scene;}

    @FXML
    public void initialize() {
        TableColumn<Delivery, String> idCol = new TableColumn<>("ID_DELIVERY");
        TableColumn<Delivery, String> dateCol = new TableColumn<>("TANGGAL_PENGEMBALIAN");
        TableColumn<Delivery, String> empCol = new TableColumn<>("EMPLOYEE_ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().id_deliveryProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().tanggal_pengembalianProperty());
        empCol.setCellValueFactory(cellData -> cellData.getValue().employee_idProperty());
        idCol.setPrefWidth(60);
        dateCol.setPrefWidth(180);
        empCol.setPrefWidth(80);

        table.getColumns().clear();
        table.getColumns().add(idCol);
        table.getColumns().add(dateCol);
        table.getColumns().add(empCol);

        table.setPlaceholder(new Label("No content in table"));

        refreshTable();
    }
    public void refreshTable() {
        list.setAll(DAO.getAllDelivery());
        table.setItems(list);
    }

    public void setApp(HelloApplication app) {
        this.app = app;
    }

    @FXML
    protected void getClicked() {
        selectedDelivery = table.getSelectionModel().getSelectedItem();
    }

    @FXML
    protected void deleteData() {
        if (selectedDelivery != null) {
            list.remove(selectedDelivery);
            try {
                DAO.deleteCustomer(selectedDelivery.getId_delivery());
                System.out.println("Data successfully deleted");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    @FXML
    protected void addData(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("formDelivery.fxml"));
            Scene scene1 = new Scene(loader.load(), 350, 400);
            stage.setTitle("Add Delivery");
            loader.<FormDeliveryController>getController().setStage(stage);
            loader.<FormDeliveryController>getController().setController(this);
            loader.<FormDeliveryController>getController().setType("add");
            loader.<FormDeliveryController>getController().setDAO(DAO);
            stage.setScene(scene1);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void editData(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("formDelivery.fxml"));
            Scene scene1 = new Scene(loader.load(), 350, 400);
            stage.setTitle("Edit Delivery");
            loader.<FormDeliveryController>getController().setStage(stage);
            loader.<FormDeliveryController>getController().setController(this);
            loader.<FormDeliveryController>getController().setType("edit");
            loader.<FormDeliveryController>getController().setDAO(DAO);
            if (selectedDelivery != null) {
                loader.<FormDeliveryController>getController().setInitialData(
                        selectedDelivery.getId_delivery(),
                        selectedDelivery.getTanggal_pengembalian(),
                        selectedDelivery.getEmployee_id());
            }
            stage.setScene(scene1);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void back(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
