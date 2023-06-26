package controllers;

import DAO.EmployeeDAO;
import com.example.bdmaven.HelloApplication;
import com.example.bdmaven.MenuController;
import entity.Employees;
import formController.FormEmployeeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import analysis.SortedPaymentController;

import java.io.IOException;
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
            try {
                DAO.deleteEmployee(selectedEmployee.getEmployee_id());
                list.remove(selectedEmployee);
                System.out.println("Data successfully deleted");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
    }
    @FXML
    protected void addData(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formEmployees.fxml"));
        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FormEmployeeController formEmployeeController = loader.getController();
        formEmployeeController.setScene(scene);

    }
    @FXML
    protected void editData(){
        if(table.getSelectionModel().getSelectedItem() != null) {
            Employees employees = (Employees) table.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formEmployees.fxml"));
                scene.setRoot(loader.load());
                FormEmployeeController formEmployeeController = loader.getController();
                formEmployeeController.setScene(scene);
                formEmployeeController.setEdit(true);
                formEmployeeController.setEditableEmployee(employees);
                formEmployeeController.loadEditData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }
    @FXML
    protected void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/Menu.fxml"));
            scene.setRoot((Parent) loader.load());
            MenuController menuController = loader.getController();
            menuController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void toSortedEmployee(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("tabelSortedPayment.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 400));
            loader.<SortedPaymentController>getController().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
