package com.example.bdmaven;

import entity.Transaction;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListTransactionController {

    private Scene scene;

    @FXML
    private TableView tableTransaction;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void initialize(){
        TableColumn<Transaction, String> transactionId = new TableColumn<>("Transaction Id");
        transactionId.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().transactionIdProperty();
                });
        transactionId.setMinWidth(100);
        TableColumn<Transaction, String> totalHarga = new TableColumn<>("Total Harga");
        totalHarga.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().totalHargaProperty();
                });
        totalHarga.setMinWidth(100);
        TableColumn<Transaction, String> dpAmount = new TableColumn<>("DP Amount");
        dpAmount.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().dpAmountProperty();
                });
        dpAmount.setMinWidth(100);
        TableColumn<Transaction, String> deliveryAddress = new TableColumn<>("Delivery Address");
        deliveryAddress.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().deliveryAddressProperty();
                });
        deliveryAddress.setMinWidth(100);
        TableColumn<Transaction, String> radiusAddress = new TableColumn<>("Radius Address");
        radiusAddress.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().radiusAddressProperty();
                });
        radiusAddress.setMinWidth(100);
        TableColumn<Transaction, String> tanggal = new TableColumn<>("Tanggal");
        tanggal.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().tanggalProperty();
                });
        tanggal.setMinWidth(100);
        tableTransaction.getColumns().clear();
        tableTransaction.getColumns().add(transactionId);
        tableTransaction.getColumns().add(totalHarga);
        tableTransaction.getColumns().add(dpAmount);
        tableTransaction.getColumns().add(deliveryAddress);
        tableTransaction.getColumns().add(radiusAddress);
        tableTransaction.getColumns().add(tanggal);

    }
}
