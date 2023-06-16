package formController;

import DAO.CustomerDAO;
import controllers.CustomerController;
import entity.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;

public class FormCustomerController {
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;
    private Scene scene;
    private boolean isEdit = false;
    private Customer editableCustomer;
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final CustomerController customerController= new CustomerController();

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
    public void onSave() throws SQLException {
        if (isValid()){
            if(!isEdit) {
                customerDAO.AddCustomer(txtNama.getText(),txtAddress.getText(),txtPhone.getText());

            } else {
                customerDAO.UpdateCustomer(editableCustomer.getCustomerId(), txtNama.getText(),txtAddress.getText(),txtPhone.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelCustomer.fxml"));
                scene.setRoot(loader.load());
                CustomerController customerController = loader.getController();
                customerController.setScene(scene);
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
        customerController.refreshTable();
    }
    @FXML
    public void onCancel(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelCustomer.fxml"));
            scene.setRoot(loader.load());
            CustomerController customerController = loader.getController();
            customerController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEditableCustomer(Customer editableCustomer) {
        this.editableCustomer = editableCustomer;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

}
