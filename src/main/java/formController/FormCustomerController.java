package formController;

import controllers.CustomerController;
import entity.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class FormCustomerController {
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;

    private ArrayList<Customer> listCustomer = new ArrayList<>();
    private Scene scene;

    private boolean isEdit = false;
    private Customer editableCustomer;
    private int indexCustomer;
    @FXML
    public void initialize(){

    }
    public void loadEditData(){
        txtNama.setText(editableCustomer.getCustomerName());
        txtAddress.setText(editableCustomer.getCustomerAddress());
        txtPhone.setText(editableCustomer.getCustomerPhone());
    }
    private boolean isValid(){
        if(txtNama.getText().isBlank() || txtNama.getText().isEmpty()
                || txtAddress.getText().isBlank() || txtAddress.getText().isEmpty()
                || txtPhone.getText().isBlank() || txtPhone.getText().isEmpty()) {
            return false;
        }
        return true;
    }
    @FXML
    public void onSave(){
        if (isValid()){
            if(!isEdit) {
                listCustomer.add(new Customer(listCustomer.size()+"", txtNama.getText(),
                        txtAddress.getText(), txtPhone.getText()));
            } else {
                editableCustomer.setCustomerName(txtNama.getText());
                editableCustomer.setCustomerAddress(txtAddress.getText());
                editableCustomer.setCustomerPhone(txtPhone.getText());
                listCustomer.set(indexCustomer, editableCustomer);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelCustomer.fxml"));
                scene.setRoot((Parent) loader.load());
                CustomerController customerController = loader.getController();
                customerController.setScene(scene);
                customerController.setListCustomer(listCustomer);
                customerController.refreshTable();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Harap Cek Data Kembali !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }
    @FXML
    public void onCancel(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelCustomer.fxml"));
            scene.setRoot((Parent) loader.load());
            CustomerController customerController = loader.getController();
            customerController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public Customer getEditableCustomer() {
        return editableCustomer;
    }

    public void setEditableCustomer(Customer editableCustomer) {
        this.editableCustomer = editableCustomer;
    }

    public int getIndexCustomer() {
        return indexCustomer;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public void setIndexCustomer(int indexCustomer) {
        this.indexCustomer = indexCustomer;
    }

}
