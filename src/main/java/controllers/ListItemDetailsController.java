package controllers;

import DAO.ItemdetailsDAO;
import com.example.bdmaven.HelloApplication;
import controllers.MenuController;
import entity.ItemDetails;
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

public class ListItemDetailsController {
    private static final ItemdetailsDAO listitemDetails = new ItemdetailsDAO();

    private ObservableList<ItemDetails> itemDetailsObservableList = FXCollections.observableArrayList();
    private Scene scene;

    private HelloApplication app;

    @FXML
    private TableView<ItemDetails> tableListItemDetails;

    public void initialize(){
        TableColumn<ItemDetails, String> Amount = new TableColumn<>("Amount");
        Amount.setCellValueFactory(celldata -> {
            return celldata.getValue().amountProperty();
        });
        TableColumn<ItemDetails, String> Pilihan_laundry = new TableColumn<>("Pilihan Laundry");
        Pilihan_laundry.setCellValueFactory(celldata -> {
            return celldata.getValue(). pilihan_laundryProperty();
        });
        TableColumn<ItemDetails, String> Kondisi = new TableColumn<>("Kondisi");
        Kondisi.setCellValueFactory(celldata -> {
            return celldata.getValue().kondisiProperty();
        });
        TableColumn<ItemDetails, String> Tanggal_pengembalian = new TableColumn<>("Tanggal pengembalian");
        Tanggal_pengembalian.setCellValueFactory(celldata -> {
            return celldata.getValue().tanggal_pengembalianProperty();
        });

        TableColumn<ItemDetails,String> Itemid = new TableColumn<>("Item Id");
        Itemid.setCellValueFactory(celldata -> {
            return celldata.getValue().item_idProperty();
        });

        TableColumn<ItemDetails,String> Transaction_id = new TableColumn<>("Transaction id");
        Transaction_id.setCellValueFactory(celldata -> {
            return celldata.getValue().transaction_idProperty();
        });
        tableListItemDetails.getColumns().clear();

        tableListItemDetails.getColumns().add(Amount);
        tableListItemDetails.getColumns().add(Pilihan_laundry);
        tableListItemDetails.getColumns().add(Kondisi);
        tableListItemDetails.getColumns().add(Tanggal_pengembalian);
        tableListItemDetails.getColumns().add(Itemid);
        tableListItemDetails.getColumns().add(Transaction_id);
        itemDetailsObservableList.setAll(listitemDetails.GetAllItemdetails());
        tableListItemDetails.setItems(itemDetailsObservableList);
        tableListItemDetails.setPlaceholder(new Label("Tidak ada data!"));
    }
    @FXML
    public void onAdd(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formItemDetails.fxml"));
            scene.setRoot((Parent) loader.load());
            formItemDetailsController formController = loader.getController();
            formController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onEdit(){
        if(tableListItemDetails.getSelectionModel().getSelectedItem() != null) {
             ItemDetails itemDetails = (ItemDetails) tableListItemDetails.getSelectionModel().getSelectedItem();
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formItemDetails.fxml"));
                scene.setRoot((Parent) loader.load());
                formItemDetailsController formController = loader.getController();
                formController.setScene(scene);
                formController.setEdit(true);
                formController.setEditable(itemDetails);
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
        if(tableListItemDetails.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                listitemDetails.Delete((tableListItemDetails.getSelectionModel().getSelectedItem()).getItem_id());
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
            menuController.setApp(app);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void refreshTable(){
        itemDetailsObservableList.setAll(listitemDetails.GetAllItemdetails());
        tableListItemDetails.setItems(itemDetailsObservableList);
    }

    public void setApp(HelloApplication app) {
        this.app = app;
    }

    public Scene getScene() {
        return scene;
    }

}
