package formController;

import DAO.HargaDeliveryDAO;
import controllers.HargaDeliveryController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class FormHargaDeliveryController {
    @FXML
    private TextField idHargaDelivery;
    @FXML
    private TextField radius;
    @FXML
    private TextField harga;
    @FXML
    private TextField employeeId;
    private Stage stage;
    private HargaDeliveryController controller;
    private String type;
    private HargaDeliveryDAO dao;
    public void setType(String type) {
        this.type = type;
    }

    public void setDAO(HargaDeliveryDAO DAO) {
        this.dao = DAO;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setController(HargaDeliveryController controller) {
        this.controller = controller;
    }
    public void setInitialData(String idHargaDelivery, String radius, String harga, String employeeId) {
        this.idHargaDelivery.setText(idHargaDelivery);
        this.idHargaDelivery.setEditable(false);
        this.radius.setText(radius);
        this.harga.setText(harga);
        this.employeeId.setText(employeeId);
    }
    @FXML
    protected void addData() {
        if (radius.getText().length() > 0 &&
                employeeId.getText().length() > 0 &&
                harga.getText().length() > 0 &&
                idHargaDelivery.getText().length() > 0 ){
            try {
                if (type.equalsIgnoreCase("add")) {
                    dao.addHargaDelivery(idHargaDelivery.getText(), radius.getText(), harga.getText(), employeeId.getText());
                    System.out.println("Data successfully added");
                } else if (type.equalsIgnoreCase("edit")) {
                    dao.updateEmployee(idHargaDelivery.getText(), radius.getText(), harga.getText(), employeeId.getText());
                    System.out.println("Data successfully edited");
                }
                controller.refreshTable();
                stage.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
        }
        else {
            clear();
        }
    }
    @FXML
    protected void clear() {
        if (!type.equalsIgnoreCase("edit")) {
            idHargaDelivery.clear();
        }
        radius.clear();
        harga.clear();
        employeeId.clear();
    }
}
