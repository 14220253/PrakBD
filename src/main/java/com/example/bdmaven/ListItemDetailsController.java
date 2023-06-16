package com.example.bdmaven;

import controllers.MenuController;
import entity.ItemDetails;
import controllers.formItemDetailsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ListItemDetailsController {
    private ArrayList<ItemDetails> listitemDetails;

    private Scene scene;

    @FXML
    private TableView tableListItemDetails;

    public void initialize(){
        TableColumn<ItemDetails, String> Amount = new TableColumn<>("Amount");
        Amount.setCellValueFactory(celldata -> {
            return celldata.getValue().amountProperty();
        });
        TableColumn<ItemDetails, String> Pilihan_laundry = new TableColumn<>("Pilihan Laundry");
        Pilihan_laundry.setCellValueFactory(celldata -> {
            return celldata.getValue().amountProperty();
        });
        TableColumn<ItemDetails, String> Kondisi = new TableColumn<>("Kondisi");
        Kondisi.setCellValueFactory(celldata -> {
            return celldata.getValue().amountProperty();
        });

        tableListItemDetails.getColumns().clear();

        tableListItemDetails.getColumns().add(Amount);
        tableListItemDetails.getColumns().add(Pilihan_laundry);
        tableListItemDetails.getColumns().add(Kondisi);

        tableListItemDetails.setPlaceholder(new Label("Tidak ada data!"));
    }
    @FXML
    public void onAdd(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formItemDetails.fxml"));
            scene.setRoot((Parent) loader.load());
            formItemDetailsController formController = loader.getController();
            formController.setScene(scene);
            formController.setListitemdetails(listitemDetails);
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
                formController.setListitemdetails(listitemDetails);
                formController.setIndex(tableListItemDetails.getSelectionModel().getSelectedIndex());

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
    public void onDelete(){
        if(tableListItemDetails.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                ItemDetails itemDetails = (ItemDetails) tableListItemDetails.getSelectionModel().getSelectedItem();
                listitemDetails.remove(itemDetails);
                refreshTable();
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
    public void setList(ArrayList<ItemDetails> list) {
        this.listitemDetails = list;
    }
    public void refreshTable(){
        ObservableList<ItemDetails> data = FXCollections.observableArrayList(
                listitemDetails
        );
        tableListItemDetails.setItems(data);
    }

    public ArrayList<ItemDetails> getList() {
        return listitemDetails;
    }

    public Scene getScene() {
        return scene;
    }

}
