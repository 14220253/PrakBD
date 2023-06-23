package controllers;

import DAO.EmployeeDAO;
import DAO.HargaDeliveryDAO;
import com.example.bdmaven.HelloApplication;
import entity.HargaDelivery;
import entity.Employees;
import entity.HargaDelivery;
import formController.FormHargaDeliveryController;
import formController.FormEmployeeController;
import formController.FormHargaDeliveryController;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formHargaDelivery.fxml"));
        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FormHargaDeliveryController formHargaDeliveryController = loader.getController();
        formHargaDeliveryController.setScene(scene);
    }
    @FXML
    protected void editData(){
        if(table.getSelectionModel().getSelectedItem() != null) {
            HargaDelivery hargaDelivery = (HargaDelivery) table.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formHargaDelivery.fxml"));
                scene.setRoot(loader.load());
                FormHargaDeliveryController formHargaDeliveryController = loader.getController();
                formHargaDeliveryController.setScene(scene);
                formHargaDeliveryController.setEdit(true);
                formHargaDeliveryController.setEditableHargaDelivery(hargaDelivery);
                formHargaDeliveryController.loadEditData();
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
    protected void back(ActionEvent event) {
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
