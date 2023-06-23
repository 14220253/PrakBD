package controllers;

import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import com.example.bdmaven.HelloApplication;
import entity.Employees;
import entity.SortCustomer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SortedEmployeeController {
    EmployeeDAO dao = new EmployeeDAO();
    private Scene scene;
    private HelloApplication app;
    @FXML
    TableView<Employees> table;
    @FXML
    protected void initialize() {
        TableColumn<Employees, String> name = new TableColumn<>("Customer Name");
        name.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getEmployee_name()));


        TableColumn<Employees, String> salary = new TableColumn<>("Transaction Count");
        salary.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getSalary()));

        table.getColumns().clear();
        table.getColumns().add(name);
        table.getColumns().add(salary);
        ObservableList<Employees> list = FXCollections.observableArrayList();
        try {
            list.addAll(dao.getSortedEmployee());
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        table.setPlaceholder(new Label("Tidak ada data!"));
    }
    @FXML
    public void back(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("tabelEmployees.fxml"));
            stage.setScene(new Scene(loader.load(), 700, 400));
            loader.<EmployeeController>getController().setApp(app);
            loader.<EmployeeController>getController().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setApp(HelloApplication app) {
        this.app = app;
    }
}
