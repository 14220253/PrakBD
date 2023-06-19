package controllers;

import DAO.EmployeeDAO;
import DAO.HargaDeliveryDAO;
import com.example.bdmaven.HelloApplication;
import entity.Employees;
import entity.HargaDelivery;
import formController.FormEmployeeController;
import formController.FormHargaDeliveryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class HargaDeliveryController {
    private HelloApplication app;
    @FXML
    private TableView<HargaDelivery> table;
    private ObservableList<HargaDelivery> list = FXCollections.observableArrayList();
    private static final HargaDeliveryDAO DAO = new HargaDeliveryDAO();
    private HargaDelivery selectedHargaDelivery;
    private Scene scene;
    public void setScene(Scene scene) {this.scene = scene;}
    @FXML
    public void initialize() {
        TableColumn<HargaDelivery, String> idCol = new TableColumn<>("ID_HARGA_DELIVERY");
        TableColumn<HargaDelivery, String> radiusCol = new TableColumn<>("RADIUS");
        TableColumn<HargaDelivery, String> hargaCol = new TableColumn<>("HARGA");
        TableColumn<HargaDelivery, String> empIdCol = new TableColumn<>("EMPLOYEE_ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().id_harga_deliveryProperty());
        radiusCol.setCellValueFactory(cellData -> cellData.getValue().radiusProperty());
        hargaCol.setCellValueFactory(cellData -> cellData.getValue().hargaProperty());
        empIdCol.setCellValueFactory(cellData -> cellData.getValue().employee_idProperty());
        idCol.setPrefWidth(90);
        radiusCol.setPrefWidth(100);
        hargaCol.setPrefWidth(120);
        empIdCol.setPrefWidth(100);

        table.getColumns().clear();
        table.getColumns().add(idCol);
        table.getColumns().add(radiusCol);
        table.getColumns().add(hargaCol);
        table.getColumns().add(empIdCol);

        table.setPlaceholder(new Label("No content in table"));

        refreshTable();
    }
    public void refreshTable() {
        list.setAll(DAO.getAllHargaDelivery());
        table.setItems(list);
    }
    public void setApp(HelloApplication app) {
        this.app = app;
    }
    @FXML
    protected void getClicked() {
        selectedHargaDelivery = table.getSelectionModel().getSelectedItem();
    }
    @FXML
    protected void deleteData() {
        if (selectedHargaDelivery != null) {
            try {
                DAO.deleteHargaDelivery(selectedHargaDelivery.getEmployee_id());
                list.remove(selectedHargaDelivery);
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
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("formHargaDelivery.fxml"));
            Scene scene1 = new Scene(loader.load(), 350, 400);
            stage.setTitle("Add Harga Delivery");
            loader.<FormHargaDeliveryController>getController().setStage(stage);
            loader.<FormHargaDeliveryController>getController().setController(this);
            loader.<FormHargaDeliveryController>getController().setType("add");
            loader.<FormHargaDeliveryController>getController().setDAO(DAO);
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
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("formHargaDelivery.fxml"));
            Scene scene1 = new Scene(loader.load(), 350, 400);
            stage.setTitle("Edit Harga Delivery");
            loader.<FormHargaDeliveryController>getController().setStage(stage);
            loader.<FormHargaDeliveryController>getController().setController(this);
            loader.<FormHargaDeliveryController>getController().setType("edit");
            loader.<FormHargaDeliveryController>getController().setDAO(DAO);
            if (selectedHargaDelivery != null) {
                loader.<FormHargaDeliveryController>getController().setInitialData(
                        selectedHargaDelivery.getId_harga_delivery(),
                        selectedHargaDelivery.getRadius(),
                        selectedHargaDelivery.getHarga(),
                        selectedHargaDelivery.getEmployee_id());
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
