package formController;

import DAO.DeliveryDAO;
import DAO.DeliveryDAO;
import controllers.DeliveryController;
import controllers.DeliveryController;
import entity.Delivery;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class FormDeliveryController {
    @FXML
    private DatePicker tanggalPengembalian;
    @FXML
    private TextField employeeId;
    private Scene scene;
    private boolean isEdit = false;
    private Delivery editableDelivery;
    private static final DeliveryDAO deliveryDAO = new DeliveryDAO();

    public void loadEditData(){
        employeeId.setText(editableDelivery.getEmployee_id());
        tanggalPengembalian.setValue(LocalDate.parse(editableDelivery.getTanggal_pengembalian()));
    }
    private boolean isValid(){
        if(employeeId.getText().isBlank() || employeeId.getText().isEmpty()
                || tanggalPengembalian.getValue() == null) {
            return false;
        }
        return true;
    }
    @FXML
    public void addData() throws SQLException {
        if (isValid()){
            if(!isEdit) {
                deliveryDAO.addDelivery(tanggalPengembalian.getValue().toString(),employeeId.getText());

            } else {
                deliveryDAO.updateDelivery(editableDelivery.getId_delivery(),tanggalPengembalian.getValue().toString(),employeeId.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelDelivery.fxml"));
                scene.setRoot(loader.load());
                DeliveryController deliveryController = loader.getController();
                deliveryController.setScene(scene);
                deliveryController.refreshTable();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelDelivery.fxml"));
            scene.setRoot(loader.load());
            DeliveryController deliveryController = loader.getController();
            deliveryController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEditableDelivery(Delivery editableDelivery) {
        this.editableDelivery = editableDelivery;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }


}
