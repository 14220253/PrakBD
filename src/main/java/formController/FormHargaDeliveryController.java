package formController;

import DAO.HargaDeliveryDAO;
import DAO.HargaDeliveryDAO;
import controllers.HargaDeliveryController;
import controllers.HargaDeliveryController;
import entity.HargaDelivery;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class FormHargaDeliveryController {
    @FXML
    private TextField radius;
    @FXML
    private TextField harga;
    @FXML
    private TextField employeeId;
    private Scene scene;
    private boolean isEdit = false;
    private HargaDelivery editableHargaDelivery;
    private static final HargaDeliveryDAO hargaDeliveryDAO = new HargaDeliveryDAO();

    public void loadEditData(){
        radius.setText(editableHargaDelivery.getRadius());
        harga.setText(editableHargaDelivery.getHarga());
        employeeId.setText(editableHargaDelivery.getEmployee_id());
    }
    private boolean isValid(){
        if(radius.getText().isBlank() || radius.getText().isEmpty()
                || harga.getText().isBlank() || harga.getText().isEmpty()
                || employeeId.getText().isBlank() || employeeId.getText().isEmpty()) {
            return false;
        }
        return true;
    }
    @FXML
    public void addData() throws SQLException {
        if (isValid()){
            if(!isEdit) {
                hargaDeliveryDAO.addHargaDelivery(radius.getText(),harga.getText(),employeeId.getText());
            } else {
                hargaDeliveryDAO.updateHargaDelivery(editableHargaDelivery.getId_harga_delivery(),radius.getText(),harga.getText(),employeeId.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelHargaDelivery.fxml"));
                scene.setRoot(loader.load());
                HargaDeliveryController hargaDeliveryController = loader.getController();
                hargaDeliveryController.setScene(scene);
                hargaDeliveryController.refreshTable();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelHargaDelivery.fxml"));
            scene.setRoot(loader.load());
            HargaDeliveryController hargaDeliveryController = loader.getController();
            hargaDeliveryController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEditableHargaDelivery(HargaDelivery editableHargaDelivery) {
        this.editableHargaDelivery = editableHargaDelivery;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
