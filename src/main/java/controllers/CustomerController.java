package controllers;

import DAO.CustomerDAO;
import com.example.bdmaven.HelloApplication;
import entity.Customer;
import formController.FormCustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.SQLException;

public class CustomerController {
    private Scene scene;
    private HelloApplication app;
    @FXML
    private TableView tableCustomer;
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private static final CustomerDAO customerDAO = new CustomerDAO();
    @FXML
    public void initialize(){
        TableColumn<Customer, String> customerId = new TableColumn<>("Customer ID");
        customerId.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty());

        TableColumn<Customer, String> customerName = new TableColumn<>("Name");
        customerName.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        
        TableColumn<Customer, String> customerAddress = new TableColumn<>("Address");
        customerAddress.setCellValueFactory(cellData -> cellData.getValue().customerAddressProperty());

        TableColumn<Customer, String> customerPhone = new TableColumn<>("Phone Number");
        customerPhone.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProperty());

        customerPhone.setMinWidth(100);
        tableCustomer.getColumns().clear();
        tableCustomer.getColumns().add(customerId);
        tableCustomer.getColumns().add(customerName);
        tableCustomer.getColumns().add(customerAddress);
        tableCustomer.getColumns().add(customerPhone);
        tableCustomer.setPlaceholder(new Label("No content in table"));
        customers.setAll(customerDAO.GetAllCustomers());
        tableCustomer.setItems(customers);

    }
    @FXML
    public void onAdd(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formCustomer.fxml"));
        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FormCustomerController formCustomerController = loader.getController();
        formCustomerController.setScene(scene);

    }
    @FXML
    public void onEdit(){
        if(tableCustomer.getSelectionModel().getSelectedItem() != null) {
            Customer customer = (Customer) tableCustomer.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formCustomer.fxml"));
                scene.setRoot(loader.load());
                FormCustomerController formCustomerController = loader.getController();
                formCustomerController.setScene(scene);
                formCustomerController.setEdit(true);
                formCustomerController.setEditableCustomer(customer);
                formCustomerController.loadEditData();
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
    public void onDelete() throws SQLException {
        if(tableCustomer.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                customerDAO.DeleteCustomer(((Customer)tableCustomer.getSelectionModel().getSelectedItem()).getCustomerId());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
        refreshTable();
    }
    @FXML
    public void back(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/Menu.fxml"));
            scene.setRoot((Parent) loader.load());
            MenuController menuController = loader.getController();
            menuController.setScene(scene);
            menuController.setApp(app);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void transactionHistory() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tabelSortedCustomer.fxml"));
        scene.setRoot((Parent) fxmlLoader.load());
        SortedCustomerController controller = fxmlLoader.getController();
        controller.setScene(scene);
        controller.setApp(app);
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void refreshTable(){
        customers.setAll(customerDAO.GetAllCustomers());
        tableCustomer.setItems(customers);
    }

    public void setApp(HelloApplication app) {
        this.app = app;
    }
}
