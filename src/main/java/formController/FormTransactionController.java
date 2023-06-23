package formController;

import DAO.TransactionDAO;
import controllers.TransactionController;
import entity.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class FormTransactionController {

    @FXML
    private DatePicker transactionDate;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtDeliveryAddress;

    @FXML
    private TextField txtDeliveryId;

    @FXML
    private TextField txtDiscountId;

    @FXML
    private TextField txtDpAmount;

    @FXML
    private TextField txtHargaDeliveryId;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtRadiusAddress;

    @FXML
    private TextField txtTotalPrice;

    private Scene scene;
    private boolean isEdit = false;
    private Transaction editableTransaction;
    private static final TransactionDAO transactionDAO = new TransactionDAO();
    private static final TransactionController transactionController= new TransactionController();

    public void loadEditData(){
        txtTotalPrice.setText(editableTransaction.getTotalHarga());
        txtDpAmount.setText(editableTransaction.getDpAmount());
        txtDeliveryAddress.setText(editableTransaction.getDeliveryAddress());
        txtRadiusAddress.setText(editableTransaction.getRadiusAddress());
        transactionDate.setValue(LocalDate.parse(editableTransaction.getTanggal()));
        txtDiscountId.setText(editableTransaction.getDiscId());
        txtPaymentId.setText(editableTransaction.getPaymentId());
        txtDeliveryId.setText(editableTransaction.getDeliveryId());
        txtHargaDeliveryId.setText(editableTransaction.getHargaDeliveryId());
        txtCustomerId.setText(editableTransaction.getCustomerId());
    }
    private boolean isValid(){
        if(txtTotalPrice.getText().isBlank() || txtTotalPrice.getText().isEmpty()
                || txtDpAmount.getText().isBlank() || txtDpAmount.getText().isEmpty()
                || txtDeliveryAddress.getText().isBlank() || txtDeliveryAddress.getText().isEmpty()
                || txtRadiusAddress.getText().isBlank() || txtRadiusAddress.getText().isEmpty()
                || transactionDate.getValue() == null
                || txtDiscountId.getText().isBlank() || txtDiscountId.getText().isEmpty()
                || txtPaymentId.getText().isBlank() || txtPaymentId.getText().isEmpty()
                || txtDeliveryId.getText().isBlank() || txtDeliveryId.getText().isEmpty()
                || txtHargaDeliveryId.getText().isBlank() || txtHargaDeliveryId.getText().isEmpty()
                || txtCustomerId.getText().isBlank() || txtCustomerId.getText().isEmpty()) {
            return false;
        }
        return true;
    }
    @FXML
    public void onSave() throws SQLException {
        if (isValid()){
            if(!isEdit) {
                transactionDAO.AddTransaction(txtTotalPrice.getText(),txtDpAmount.getText(),txtDeliveryAddress.getText(),
                        txtRadiusAddress.getText(), transactionDate.getValue().toString(),txtDiscountId.getText(),
                        txtPaymentId.getText(), txtDeliveryId.getText(), txtHargaDeliveryId.getText(),txtCustomerId.getText());

            } else {
                transactionDAO.UpdateTransaction(editableTransaction.getTransactionId(), txtTotalPrice.getText(),txtDpAmount.getText(),txtDeliveryAddress.getText(),
                        txtRadiusAddress.getText(), transactionDate.getValue().toString(),txtDiscountId.getText(),
                        txtPaymentId.getText(), txtDeliveryId.getText(), txtHargaDeliveryId.getText(),txtCustomerId.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelTransaction.fxml"));
                scene.setRoot(loader.load());
                TransactionController transactionController = loader.getController();
                transactionController.setScene(scene);
                transactionController.refreshTable();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelTransaction.fxml"));
            scene.setRoot(loader.load());
            TransactionController transactionController = loader.getController();
            transactionController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEditableTransaction(Transaction editableTransaction) {
        this.editableTransaction = editableTransaction;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
