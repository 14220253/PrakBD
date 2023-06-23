package formController;

import DAO.PaymentDAO;
import controllers.PaymentController;
import entity.Payment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class FormPaymentController {
    @FXML
    private TextField txtPaymentName;
    private Scene scene;
    private boolean isEdit = false;
    private Payment editablePayment;
    private static final PaymentDAO paymentDAO = new PaymentDAO();
    private static final PaymentController paymentController= new PaymentController();

    public void loadEditData(){
        txtPaymentName.setText(editablePayment.getPaymentName());
    }
    private boolean isValid(){
        if(txtPaymentName.getText().isBlank() || txtPaymentName.getText().isEmpty()) {
            return false;
        }
        return true;
    }
    @FXML
    public void onSave() throws SQLException {
        if (isValid()){
            if(!isEdit) {
                paymentDAO.AddPayment(txtPaymentName.getText());

            } else {
                paymentDAO.UpdatePayment(editablePayment.getPaymentId(), txtPaymentName.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelPayment.fxml"));
                scene.setRoot(loader.load());
                PaymentController paymentController = loader.getController();
                paymentController.setScene(scene);
                paymentController.refreshTable();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelPayment.fxml"));
            scene.setRoot(loader.load());
            PaymentController paymentController = loader.getController();
            paymentController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEditablePayment(Payment editablePayment) {
        this.editablePayment = editablePayment;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
