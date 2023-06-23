package controllers;

import DAO.DeliveryDAO;
import com.example.bdmaven.HelloApplication;
import entity.Delivery;
import entity.Delivery;
import formController.FormDeliveryController;
import formController.FormDeliveryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
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
            try {
                DAO.deleteDelivery(selectedDelivery.getId_delivery());
                list.remove(selectedDelivery);
                System.out.println("Data successfully deleted");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    @FXML
    protected void addData(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formDelivery.fxml"));
            scene.setRoot((Parent) loader.load());
            FormDeliveryController formDeliveryController = loader.getController();
            formDeliveryController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void editData(){
        if(table.getSelectionModel().getSelectedItem() != null) {
            Delivery customer = (Delivery) table.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formDelivery.fxml"));
                scene.setRoot(loader.load());
                FormDeliveryController formDeliveryController = loader.getController();
                formDeliveryController.setScene(scene);
                formDeliveryController.setEdit(true);
                formDeliveryController.setEditableDelivery(customer);
                formDeliveryController.loadEditData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }
    @FXML
    protected void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/Menu.fxml"));
            scene.setRoot((Parent) loader.load());
            MenuController menuController = loader.getController();
            menuController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
