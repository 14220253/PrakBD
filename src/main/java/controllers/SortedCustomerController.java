package controllers;

import DAO.CustomerDAO;
import com.example.bdmaven.HelloApplication;
import entity.Item;
import entity.SortCustomer;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortedCustomerController {
    CustomerDAO dao = new CustomerDAO();
    private Scene scene;
    private HelloApplication app;
    @FXML
    TableView<SortCustomer> table;
    @FXML
    protected void initialize() {
        ResultSet results;
        try {
            results = dao.sortCustomer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        TableColumn<SortCustomer, String> name = new TableColumn<>("Customer Name");
        name.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getName()));


        TableColumn<SortCustomer, String> count = new TableColumn<>("Transaction Count");
        count.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getCount()));

        table.getColumns().clear();
        table.getColumns().add(name);
        table.getColumns().add(count);
        ObservableList<SortCustomer> list = FXCollections.observableArrayList();
        try {
            list.addAll(dao.getCustFromSort(results));
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        table.setPlaceholder(new Label("Tidak ada data!"));
    }
    @FXML
    public void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelCustomer.fxml"));
            scene.setRoot((Parent) loader.load());
            CustomerController controller = loader.getController();
            controller.setScene(scene);
            controller.setApp(app);

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
