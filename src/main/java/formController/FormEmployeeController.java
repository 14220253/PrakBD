package formController;

import DAO.EmployeeDAO;
import controllers.EmployeeController;
import entity.Employees;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;

public class FormEmployeeController {
    @FXML
    private TextField employeeName;
    @FXML
    private TextField salary;
    @FXML
    private TextField jobId;
    private Scene scene;
    private boolean isEdit = false;
    private Employees editableEmployee;
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public void loadEditData(){
        employeeName.setText(editableEmployee.getEmployee_name());
        salary.setText(editableEmployee.getSalary());
        jobId.setText(editableEmployee.getJob_id());
    }
    private boolean isValid(){
        if(employeeName.getText().isBlank() || employeeName.getText().isEmpty()
                || salary.getText().isBlank() || salary.getText().isEmpty()
                || jobId.getText().isBlank() || jobId.getText().isEmpty()) {
            return false;
        }
        return true;
    }
    @FXML
    public void addData() throws SQLException {
        if (isValid()){
            if(!isEdit) {
                employeeDAO.addEmployee(employeeName.getText(),salary.getText(),jobId.getText());

            } else {
                employeeDAO.updateEmployee(editableEmployee.getEmployee_id(),employeeName.getText(),salary.getText(),jobId.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelEmployees.fxml"));
                scene.setRoot(loader.load());
                EmployeeController employeeController = loader.getController();
                employeeController.setScene(scene);
                employeeController.refreshTable();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelEmployees.fxml"));
            scene.setRoot(loader.load());
            EmployeeController employeeController = loader.getController();
            employeeController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEditableEmployee(Employees editableEmployee) {
        this.editableEmployee = editableEmployee;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
