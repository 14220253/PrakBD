package formController;

import DAO.DeliveryDAO;
import DAO.EmployeeDAO;
import controllers.DeliveryController;
import controllers.EmployeeController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class FormEmployeeController {
    @FXML
    private TextField employeeId;
    @FXML
    private TextField employeeName;
    @FXML
    private TextField salary;
    @FXML
    private TextField jobId;
    private Stage stage;
    private EmployeeController controller;
    private String type;
    private EmployeeDAO dao;
    public void setType(String type) {
        this.type = type;
    }

    public void setDAO(EmployeeDAO DAO) {
        this.dao = DAO;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setController(EmployeeController controller) {
        this.controller = controller;
    }
    public void setInitialData(String employeeId, String employeeName, String salary, String jobId) {
        this.employeeId.setText(employeeId);
        this.employeeId.setEditable(false);
        this.employeeName.setText(employeeName);
        this.salary.setText(salary);
        this.jobId.setText(jobId);
    }
    @FXML
    protected void addData() {
        if (employeeName.getText().length() > 0 &&
                employeeId.getText().length() > 0 &&
                salary.getText().length() > 0 &&
                jobId.getText().length() > 0 ){
            try {
                if (type.equalsIgnoreCase("add")) {
                    dao.addEmployee(employeeId.getText(), employeeName.getText(), salary.getText(), jobId.getText());
                    System.out.println("Data successfully added");
                } else if (type.equalsIgnoreCase("edit")) {
                    dao.updateEmployee(employeeId.getText(), employeeName.getText(), salary.getText(), jobId.getText());
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
            employeeId.clear();
        }
        employeeName.clear();
        salary.clear();
        jobId.clear();
    }
}
