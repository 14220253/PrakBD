package analysis;

import DAO.PaymentDAO;
import com.example.bdmaven.MenuController;
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

public class SortedPaymentController {
    PaymentDAO dao = new PaymentDAO();
    private Scene scene;
    @FXML
    TableView<SortPayment> table;
    @FXML
    protected void initialize() {
        ResultSet results;
        try {
            results = dao.sortPayment();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableColumn<SortPayment, String> name = new TableColumn<>("Payment Name");
        name.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getName()));


        TableColumn<SortPayment, String> count = new TableColumn<>("Transaction Count");
        count.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getCount()));

        table.getColumns().clear();
        table.getColumns().add(name);
        table.getColumns().add(count);
        ObservableList<SortPayment> list = FXCollections.observableArrayList();
        try {
            list.addAll(dao.getPaymentFromSort(results));
            table.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        table.setPlaceholder(new Label("Tidak ada data!"));
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

}
