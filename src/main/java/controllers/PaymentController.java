package controllers;

import DAO.PaymentDAO;
import entity.Payment;
import formController.FormPaymentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class PaymentController {
    private Scene scene;
    @FXML
    private TableView tablePayment;
    private ObservableList<Payment> payments = FXCollections.observableArrayList();
    private static final PaymentDAO paymentDAO = new PaymentDAO();
    @FXML
    public void initialize(){
        TableColumn<Payment, String> paymentId = new TableColumn<>("Payment ID");
        paymentId.setCellValueFactory(cellData -> cellData.getValue().paymentIdProperty());

        TableColumn<Payment, String> paymentName = new TableColumn<>("Name");
        paymentName.setCellValueFactory(cellData -> cellData.getValue().paymentNameProperty());
        
        tablePayment.getColumns().clear();
        tablePayment.getColumns().add(paymentId);
        tablePayment.getColumns().add(paymentName);
        tablePayment.setPlaceholder(new Label("No content in table"));
        payments.setAll(paymentDAO.GetAllPayments());
        tablePayment.setItems(payments);

    }
    @FXML
    public void onAdd(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formPayment.fxml"));
        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FormPaymentController formPaymentController = loader.getController();
        formPaymentController.setScene(scene);

    }
    @FXML
    public void onEdit(){
        if(tablePayment.getSelectionModel().getSelectedItem() != null) {
            Payment payment = (Payment) tablePayment.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formPayment.fxml"));
                scene.setRoot(loader.load());
                FormPaymentController formPaymentController = loader.getController();
                formPaymentController.setScene(scene);
                formPaymentController.setEdit(true);
                formPaymentController.setEditablePayment(payment);
                formPaymentController.loadEditData();
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
        if(tablePayment.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                paymentDAO.DeletePayment(((Payment)tablePayment.getSelectionModel().getSelectedItem()).getPaymentId());
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
        payments.setAll(paymentDAO.GetAllPayments());
        tablePayment.setItems(payments);
    }
}
