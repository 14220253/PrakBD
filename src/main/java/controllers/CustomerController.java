package controllers;

import DAO.CustomerDAO;
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
import java.util.ArrayList;

public class CustomerController {
    private ArrayList<Customer> listCustomer;
    private Scene scene;
    @FXML
    private TableView tableCustomer;
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private static final CustomerDAO customerDAO = new CustomerDAO();
    @FXML
    public void initialize(){
        TableColumn<Customer, String> customerId = new TableColumn<>("Customer ID");
        customerId.setCellValueFactory(cellData -> {
            return cellData.getValue().customerIdProperty();
        });
        TableColumn<Customer, String> customerName = new TableColumn<>("Name");
        customerName.setCellValueFactory(cellData -> {
            return cellData.getValue().customerNameProperty();
        });
        
        TableColumn<Customer, String> customerAddress = new TableColumn<>("Address");
        customerAddress.setCellValueFactory(cellData -> {
            return cellData.getValue().customerAddressProperty();
        });
        TableColumn<Customer, String> customerPhone = new TableColumn<>("Phone Number");
        customerPhone.setCellValueFactory(cellData -> {
            return cellData.getValue().customerPhoneProperty();
        });
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
            scene.setRoot((Parent) loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FormCustomerController formCustomerController = loader.getController();
        formCustomerController.setScene(scene);
        formCustomerController.setListCustomer(listCustomer);
    }
    @FXML
    public void onEdit(){
        if(tableCustomer.getSelectionModel().getSelectedItem() != null) {
            Customer customer = (Customer) tableCustomer.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formCustomer.fxml"));
                scene.setRoot((Parent) loader.load());
                FormCustomerController formCustomerController = loader.getController();
                formCustomerController.setScene(scene);
                formCustomerController.setListCustomer(listCustomer);
                formCustomerController.setIndexCustomer(tableCustomer.getSelectionModel().getSelectedIndex());
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
    public void onDelete(){
        if(tableCustomer.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                Customer barang = (Customer) tableCustomer.getSelectionModel().getSelectedItem();
                listCustomer.remove(barang);
                refreshTable();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public ArrayList<Customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(ArrayList<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }
    public void refreshTable(){
        ObservableList<Customer> data = FXCollections.observableArrayList(
                listCustomer
        );
        tableCustomer.setItems(data);
    }

}
