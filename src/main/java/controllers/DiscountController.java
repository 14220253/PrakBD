package controllers;
import DAO.DiscoundDAO;
import DAO.ItemdetailsDAO;
import controllers.MenuController;
import entity.Discount;
import entity.ItemDetails;
import formController.FormDiscountController;
import formController.formItemDetailsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
public class DiscountController {
    private static final DiscoundDAO discoundDAO = new DiscoundDAO();

    private ObservableList<Discount> discountObservableList = FXCollections.observableArrayList();
    private Scene scene;

    @FXML
    private TableView<Discount> tablediscount;

    public void initialize(){
        TableColumn<Discount, String> disc_id = new TableColumn<>("Disc id");
        disc_id.setCellValueFactory(celldata -> {
            return celldata.getValue().disc_idProperty();
        });
        TableColumn<Discount, String> disc_name = new TableColumn<>("Disc name");
        disc_name.setCellValueFactory(celldata -> {
            return celldata.getValue().disc_nameProperty();
        });
        TableColumn<Discount, String> disc_tanggal_mulai = new TableColumn<>("Disc tanggal mulai");
        disc_tanggal_mulai.setCellValueFactory(celldata -> {
            return celldata.getValue().disc_tanggal_mulaiProperty();
        });
        TableColumn<Discount, String> disc_tanggal_selesai = new TableColumn<>("Disc tanggal selesai");
        disc_tanggal_selesai.setCellValueFactory(celldata -> {
            return celldata.getValue().disc_tanggal_selesaiProperty();
        });

        TableColumn<Discount,String> disc_percent = new TableColumn<>("disc percent");
        disc_percent.setCellValueFactory(celldata -> {
            return celldata.getValue().disc_percentProperty();
        });

        TableColumn<Discount,String> disc_info = new TableColumn<>("disc info");
        disc_info.setCellValueFactory(celldata -> {
            return celldata.getValue().disc_infoProperty();
        });
        tablediscount.getColumns().clear();

        tablediscount.getColumns().add(disc_id);
        tablediscount.getColumns().add(disc_name);
        tablediscount.getColumns().add(disc_tanggal_mulai);
        tablediscount.getColumns().add(disc_tanggal_selesai);
        tablediscount.getColumns().add(disc_percent);
        tablediscount.getColumns().add(disc_info);
        discountObservableList.setAll(discoundDAO.GetAll());
        tablediscount.setItems(discountObservableList);
        tablediscount.setPlaceholder(new Label("Tidak ada data!"));
    }
    @FXML
    public void onAdd(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formDiscount.fxml"));
            scene.setRoot((Parent) loader.load());
            FormDiscountController formController = loader.getController();
            formController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onEdit(){
        if(tablediscount.getSelectionModel().getSelectedItem() != null) {
            Discount discount =  tablediscount.getSelectionModel().getSelectedItem();
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formDiscount.fxml"));
                scene.setRoot((Parent) loader.load());
                FormDiscountController formController = loader.getController();
                formController.setScene(scene);
                formController.setEdit(true);
                formController.setEditable(discount);
                formController.loadEditData();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    @FXML
    public void onDelete() throws SQLException {
        if(tablediscount.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                discoundDAO.Delete((tablediscount.getSelectionModel().getSelectedItem()).getDisc_id());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
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
        discountObservableList.setAll(discoundDAO.GetAll());
        tablediscount.setItems(discountObservableList);
    }



    public Scene getScene() {
        return scene;
    }

}
