package controllers;

import DAO.TransactionDAO;
import com.example.bdmaven.HelloApplication;
import entity.Transaction;
import entity.Transaction;
import formController.FormTransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class TransactionController {

    private Scene scene;
    private HelloApplication app;

    @FXML
    private TableView tableTransaction;
    
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    private static final TransactionDAO transactionDAO = new TransactionDAO();
    
    public void initialize(){
        TableColumn<Transaction, String> transactionId = new TableColumn<>("Transaction ID");
        transactionId.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().transactionIdProperty();
                });
        TableColumn<Transaction, String> totalHarga = new TableColumn<>("Total Harga");
        totalHarga.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().totalHargaProperty();
                });
        TableColumn<Transaction, String> dpAmount = new TableColumn<>("DP Amount");
        dpAmount.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().dpAmountProperty();
                });
        TableColumn<Transaction, String> deliveryAddress = new TableColumn<>("Delivery Address");
        deliveryAddress.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().deliveryAddressProperty();
                });
        TableColumn<Transaction, String> radiusAddress = new TableColumn<>("Radius Address");
        radiusAddress.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().radiusAddressProperty();
                });
        TableColumn<Transaction, String> tanggal = new TableColumn<>("Tanggal");
        tanggal.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().tanggalProperty();
                });
        TableColumn<Transaction, String> discId = new TableColumn<>("Discount ID");
        discId.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().discIdProperty();
                });
        TableColumn<Transaction, String> paymentId = new TableColumn<>("Payment ID");
        paymentId.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().paymentIdProperty();
                });
        TableColumn<Transaction, String> deliveryId = new TableColumn<>("Delivery ID");
        deliveryId.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().deliveryIdProperty();
                });
        TableColumn<Transaction, String> hargaDeliveryId= new TableColumn<>("Harga Delivery ID");
        hargaDeliveryId.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().hargaDeliveryIdProperty();
                });

        TableColumn<Transaction, String> customerId= new TableColumn<>("Customer ID");
        customerId.setCellValueFactory
                (cellData -> {
                    return cellData.getValue().customerIdProperty();
                });
        tableTransaction.getColumns().clear();
        tableTransaction.getColumns().add(transactionId);
        tableTransaction.getColumns().add(totalHarga);
        tableTransaction.getColumns().add(dpAmount);
        tableTransaction.getColumns().add(deliveryAddress);
        tableTransaction.getColumns().add(radiusAddress);
        tableTransaction.getColumns().add(tanggal);
        tableTransaction.getColumns().add(discId);
        tableTransaction.getColumns().add(paymentId);
        tableTransaction.getColumns().add(deliveryId);
        tableTransaction.getColumns().add(hargaDeliveryId);
        tableTransaction.getColumns().add(customerId);
        tableTransaction.setPlaceholder(new Label("No content in table"));
        transactions.setAll(transactionDAO.GetAllTransactions());
        tableTransaction.setItems(transactions);
    }
    @FXML
    public void onAdd(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formTransaction.fxml"));
        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FormTransactionController formTransactionController = loader.getController();
        formTransactionController.setScene(scene);

    }
    @FXML
    public void onEdit(){
        if(tableTransaction.getSelectionModel().getSelectedItem() != null) {
            Transaction transaction = (Transaction) tableTransaction.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formTransaction.fxml"));
                scene.setRoot(loader.load());
                FormTransactionController formTransactionController = loader.getController();
                formTransactionController.setScene(scene);
                formTransactionController.setEdit(true);
                formTransactionController.setEditableTransaction(transaction);
                formTransactionController.loadEditData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }
    @FXML
    public void onDelete() throws SQLException {
        if(tableTransaction.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                transactionDAO.DeleteTransaction(((Transaction)tableTransaction.getSelectionModel().getSelectedItem()).getTransactionId());
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
            menuController.setApp(app);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void refreshTable(){
        transactions.setAll(transactionDAO.GetAllTransactions());
        tableTransaction.setItems(transactions);
    }

    public void setApp(HelloApplication app) {
        this.app = app;
    }

    @FXML
    public void toTotal() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/showTotalTransaction.fxml"));
            scene.setRoot((Parent) loader.load());

            TransactionTotalController controller = loader.getController();
            controller.setScene(scene);
            controller.setApp(app);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
