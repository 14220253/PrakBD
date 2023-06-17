package formController;

import DAO.DeliveryDAO;
import controllers.DeliveryController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class FormDeliveryController {
    @FXML
    private TextField deliveryId;
    @FXML
    private DatePicker tanggalPengembalian;
    @FXML
    private TextField employeeId;
    private Stage stage;
    private DeliveryController controller;
    private String type;
    private DeliveryDAO dao;

    public void setType(String type) {
        this.type = type;
    }

    public void setDAO(DeliveryDAO DAO) {
        this.dao = DAO;
    }

    @FXML
    protected void initialize() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setController(DeliveryController controller) {
        this.controller = controller;
    }

    public void setInitialData(String deliveryId, String tanggalPengembalian, String employeeId) {
        this.deliveryId.setText(deliveryId);
        this.deliveryId.setEditable(false);
        this.tanggalPengembalian.setValue(
                LocalDate.of(Integer.parseInt(tanggalPengembalian.substring(0, 4)),
                        Integer.parseInt(tanggalPengembalian.substring(5, 7)),
                        Integer.parseInt(tanggalPengembalian.substring(8, 10))));
        this.employeeId.setText(employeeId);
    }

    @FXML
    protected void addData() {
        if (deliveryId.getText().length() > 0 &&
        tanggalPengembalian.getValue() != null &&
        employeeId.getText().length() > 0) {
            try {
                if (type.equalsIgnoreCase("add")) {
                    dao.addDelivery(deliveryId.getText(), tanggalPengembalian.getValue().toString(), employeeId.getText());
                    System.out.println("Data successfully added");
                } else if (type.equalsIgnoreCase("edit")) {
                    dao.updateCustomer(deliveryId.getText(), tanggalPengembalian.getValue().toString(), employeeId.getText());
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
        deliveryId.clear();
        employeeId.clear();
    }

}
