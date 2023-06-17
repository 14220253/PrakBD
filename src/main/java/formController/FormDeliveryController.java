package formController;

import DAO.DeliveryDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.bdmaven.JDBC.LOGGER;

public class FormDeliveryController {
    @FXML
    private TextField deliveryId;
    @FXML
    private TextField tanggalPengembalian;
    @FXML
    private TextField employeeId;
    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    protected void initialize() {
    }
    @FXML
    protected void addData(ActionEvent event) {
        if (deliveryId.getText().length() > 0 &&
        tanggalPengembalian.getText().length() > 0 &&
        employeeId.getText().length() > 0) {
            try {
                DeliveryDAO dao = new DeliveryDAO();
                dao.addDelivery(deliveryId.getText(), tanggalPengembalian.getText(), employeeId.getText());
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
        }
        else {

        }
    }
    @FXML
    protected void clear() {
        deliveryId.clear();
        tanggalPengembalian.clear();
        employeeId.clear();
    }

}
