package controllers;

import com.example.bdmaven.HelloApplication;
import com.example.bdmaven.JDBC;
import entity.Delivery;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryController {
    HelloApplication app;
    Scene scene;
    @FXML
    Button backButton;
    @FXML
    Button addButton;
    @FXML
    Button editButton;
    @FXML
    Button deleteButton;
    @FXML
    TableView<Delivery> table;
    TableColumn<Delivery, Integer> idCol = new TableColumn<>();
    TableColumn<Delivery, Date> dateCol = new TableColumn<>();
    JDBC jdbc = new JDBC();
    ObservableList<Delivery> list = FXCollections.observableArrayList();
    PreparedStatement statement;
    ResultSet result;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void initialize() {
        TableColumn<Delivery, SimpleStringProperty> idCol = new TableColumn<>("ID_DELIVERY");
        TableColumn<Delivery, Date> dateCol = new TableColumn<>("TANGGAL_PENGEMBALIAN");
        String sql = "SELECT * FROM `delivery`";
        idCol.setMinWidth(80);
        dateCol.setMinWidth(220);

        load(sql);
    }
    protected void load(String sql) {
        update(sql);

        idCol.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getId_delivery()));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    protected void update(String sql) {
        list.clear();
        try {
            statement = jdbc.connection.get().prepareStatement(sql);
            result = statement.executeQuery();

            while (result.next()) {
                list.add(new Delivery(result.getInt("id_delivery"),
                        result.getDate("tanggal_pengembalian"),
                        result.getInt("employee_id")));
                table.setItems(list);
            }
        }catch (SQLException e) {
            Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    @FXML
    protected void addData(ActionEvent event) {

    }
}
