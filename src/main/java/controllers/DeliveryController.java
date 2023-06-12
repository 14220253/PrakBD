package controllers;

import com.example.bdmaven.HelloApplication;
import entity.Delivery;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Date;

public class DeliveryController {
    HelloApplication app = new HelloApplication();
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
    @FXML
    TableColumn<Delivery, Integer> delivery_id_col;
    @FXML
    TableColumn<Delivery, Date> delivery_date_col;
}
