package controllers;

import DAO.EmployeeDAO;
import com.example.bdmaven.HelloApplication;
import entity.Employees;
import formController.FormDeliveryController;
import formController.FormEmployeeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class EmployeeController {
    private HelloApplication app;
    @FXML
    private TableView<Employees> table;
    private ObservableList<Employees> list = FXCollections.observableArrayList();
    private static final EmployeeDAO DAO = new EmployeeDAO();
    private Employees selectedEmployee;
    private Scene scene;
    public void setScene(Scene scene) {this.scene = scene;}

    @FXML
    public void initialize() {
        TableColumn<Employees, String> idCol = new TableColumn<>("EMPLOYEE_ID");
        TableColumn<Employees, String> nameCol = new TableColumn<>("EMPLOYEE_NAME");
        TableColumn<Employees, String> salaryCol = new TableColumn<>("SALARY");
        TableColumn<Employees, String> jobCol = new TableColumn<>("JOB_ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().employee_idProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().employee_nameProperty());
        salaryCol.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());
        jobCol.setCellValueFactory(cellData -> cellData.getValue().job_idProperty());
        idCol.setPrefWidth(90);
        nameCol.setPrefWidth(150);
        salaryCol.setPrefWidth(90);
        jobCol.setPrefWidth(80);

        table.getColumns().clear();
        table.getColumns().add(idCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(salaryCol);
        table.getColumns().add(jobCol);

        table.setPlaceholder(new Label("No content in table"));

        refreshTable();
    }
    public void refreshTable() {
        list.setAll(DAO.getAllEmployees());
        table.setItems(list);
    }
    public void setApp(HelloApplication app) {
        this.app = app;
    }
    @FXML
    protected void getClicked() {
        selectedEmployee = table.getSelectionModel().getSelectedItem();
    }
    @FXML
    protected void deleteData() {
        if (selectedEmployee != null) {
            list.remove(selectedEmployee);
            try {
                DAO.deleteEmployee(selectedEmployee.getEmployee_id());
                System.out.println("Data successfully deleted");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
    }
    @FXML
    protected void addData(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("formEmployees.fxml"));
            Scene scene1 = new Scene(loader.load(), 350, 400);
            stage.setTitle("Add Delivery");
            loader.<FormEmployeeController>getController().setStage(stage);
            loader.<FormEmployeeController>getController().setController(this);
            loader.<FormEmployeeController>getController().setType("add");
            loader.<FormEmployeeController>getController().setDAO(DAO);
            stage.setScene(scene1);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void editData(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("formEmployees.fxml"));
            Scene scene1 = new Scene(loader.load(), 350, 400);
            stage.setTitle("Edit Delivery");
            loader.<FormEmployeeController>getController().setStage(stage);
            loader.<FormEmployeeController>getController().setController(this);
            loader.<FormEmployeeController>getController().setType("edit");
            loader.<FormEmployeeController>getController().setDAO(DAO);
            if (selectedEmployee != null) {
                loader.<FormEmployeeController>getController().setInitialData(
                        selectedEmployee.getEmployee_id(),
                        selectedEmployee.getEmployee_name(),
                        selectedEmployee.getSalary(),
                        selectedEmployee.getJob_id());
            }
            stage.setScene(scene1);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void back(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
