package queryController;

import DAO.TransactionDAO;
import com.example.bdmaven.HelloApplication;
import controllers.MenuController;
import controllers.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormatSymbols;

public class TransactionTotalController {
    @FXML
    Label label;
    @FXML
    Label total;
    @FXML
    ChoiceBox<Integer> choicebox1;
    @FXML
    ChoiceBox<Integer> choicebox2;
    Scene scene;
    HelloApplication app;
    TransactionDAO dao = new TransactionDAO();
    int month = 0;
    int year = 0;
    @FXML
    public void initialize() {
        choicebox1.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        choicebox2.getItems().addAll(2023, 2024, 2025);

        try {
            update();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        choicebox1.setOnAction(this::getInput1);
        choicebox2.setOnAction(this::getInput2);

    }
    @FXML
    public void getInput1(ActionEvent event){
        try {
            month = choicebox1.getValue();
            if (year == 0) {
                updateMonth(month);
            } else {
                updateBoth(month, year);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (label.getText() == null) {
            label.setText("0");
        }
    }
    @FXML
    public void getInput2(ActionEvent event) {
        try {
            year = choicebox2.getValue();
            if (month == 0) {
                updateYear(year);
            } else {
                updateBoth(month, year);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (label.getText() == null) {
            label.setText("0");
        }
    }
    public void update() throws SQLException {
        label.setText(dao.getTotal());
    }
    public void updateMonth(int month) throws SQLException{
        label.setText(dao.getTotalFromMonth(month));
        total.setText("Total Penjualan " + new DateFormatSymbols().getMonths()[month - 1]);
    }
    public void updateYear(int year) throws SQLException {
        label.setText(dao.getTotalFromYear(year));
        total.setText("Total Penjualan " + year);
    }
    public void updateBoth(int month, int year) throws SQLException {
        label.setText(dao.getTotalFromBoth(month, year));
        total.setText("Total Penjualan " + new DateFormatSymbols().getMonths()[month - 1] + " " + year);
    }

    @FXML
    public void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/Menu.fxml"));
            scene.setRoot((Parent) loader.load());
            MenuController menuController = loader.getController();
            menuController.setScene(scene);

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
