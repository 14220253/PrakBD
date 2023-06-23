package controllers;

import DAO.DiscountDetailDAO;
import com.example.bdmaven.HelloApplication;
import entity.DiscountDetail;
import formController.FormDiscountDetailController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class DiscountDetailController {
    private Scene scene;
    private HelloApplication app;
    @FXML
    private TableView tableDiscountDetail;
    private ObservableList<DiscountDetail> discountDetails = FXCollections.observableArrayList();
    private static final DiscountDetailDAO discountDetailDAO = new DiscountDetailDAO();
    @FXML
    public void initialize(){
        TableColumn<DiscountDetail, String> discountId = new TableColumn<>("Discount ID");
        discountId.setCellValueFactory(cellData -> cellData.getValue().discountIdProperty());

        TableColumn<DiscountDetail, String> categoryId = new TableColumn<>("Category ID");
        categoryId.setCellValueFactory(cellData -> cellData.getValue().categoryIdProperty());

        tableDiscountDetail.getColumns().clear();
        tableDiscountDetail.getColumns().add(discountId);
        tableDiscountDetail.getColumns().add(categoryId);
        tableDiscountDetail.setPlaceholder(new Label("No content in table"));
        discountDetails.setAll(discountDetailDAO.GetAllDiscountDetails());
        tableDiscountDetail.setItems(discountDetails);
    }
    @FXML
    public void onAdd(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formDiscountDetail.fxml"));
        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FormDiscountDetailController formDiscountDetailController = loader.getController();
        formDiscountDetailController.setScene(scene);

    }
    @FXML
    public void onDelete() throws SQLException {
        if(tableDiscountDetail.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                discountDetailDAO.DeleteDiscountDetail(((DiscountDetail)tableDiscountDetail.getSelectionModel().getSelectedItem()).getDiscountId(),
                        ((DiscountDetail)tableDiscountDetail.getSelectionModel().getSelectedItem()).getCategoryId());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
        refreshTable();
    }
    @FXML
    public void back(){
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
    public void refreshTable(){
        discountDetails.setAll(discountDetailDAO.GetAllDiscountDetails());
        tableDiscountDetail.setItems(discountDetails);
    }

    public void setApp(HelloApplication app) {
        this.app = app;
    }
}
