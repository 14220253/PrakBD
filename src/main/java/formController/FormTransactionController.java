package formController;

import DAO.TransactionDAO;
import com.example.bdmaven.JDBC;
import controllers.TransactionController;
import entity.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FormTransactionController {

    @FXML
    private DatePicker transactionDate;

    @FXML
    private ChoiceBox cbCust;

    @FXML
    private TextField txtDeliveryAddress;

    @FXML
    private TextField txtDeliveryId;

    @FXML
    private ChoiceBox cbDisc;

    @FXML
    private TextField txtDpAmount;

    @FXML
    private ChoiceBox cbHargaDelivery;

    @FXML
    private ChoiceBox cbPayment;

    private Scene scene;
    private boolean isEdit = false;
    private Transaction editableTransaction;
    private static final TransactionDAO transactionDAO = new TransactionDAO();
    private final JDBC jdbc = new JDBC();
    public void loadEditData() throws SQLException {
        txtDpAmount.setText(editableTransaction.getDpAmount());
        txtDeliveryAddress.setText(editableTransaction.getDeliveryAddress());
        transactionDate.setValue(LocalDate.parse(editableTransaction.getTanggal()));
        String sql = "SELECT `radius` FROM `harga_delivery` WHERE `id_harga_delivery` = ?";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, editableTransaction.getHargaDeliveryId());
        ResultSet result = stm.executeQuery();
        String radius = null;
        if (result.next()) {
            radius = result.getString(1);
        }
        cbHargaDelivery.setValue(radius);
        String sql2 = "SELECT `payment_name` FROM `payment` WHERE `payment_id` = ?";
        PreparedStatement stm2 = jdbc.connection.get().prepareStatement(sql2);
        stm2.setString(1, editableTransaction.getPaymentId());
        ResultSet result2 = stm2.executeQuery();
        String payment = null;
        if (result2.next()) {
            payment = result2.getString(1);
        }
        cbPayment.setValue(payment);
        txtDeliveryId.setText(editableTransaction.getDeliveryId());
        String sql3 = "SELECT `disc_name` FROM `discount` WHERE `disc_id` = ?";
        PreparedStatement stm3 = jdbc.connection.get().prepareStatement(sql3);
        stm3.setString(1, editableTransaction.getDiscId());
        ResultSet result3 = stm3.executeQuery();
        String discount = null;
        if (result3.next()) {
            discount = result3.getString(1);
        }
        cbDisc.setValue(discount);
        String sql4 = "SELECT `cust_name` FROM `customers` WHERE `cust_id` = ?";
        PreparedStatement stm4 = jdbc.connection.get().prepareStatement(sql4);
        stm4.setString(1, editableTransaction.getCustomerId());
        ResultSet result4 = stm4.executeQuery();
        String customer = null;
        if (result4.next()) {
            customer = result4.getString(1);
        }
        cbCust.setValue(customer);
    }
    private boolean isValid(){
        if(txtDpAmount.getText().isBlank() || txtDpAmount.getText().isEmpty() || transactionDate.getValue() == null
                || cbCust.getSelectionModel().isEmpty() || cbPayment.getSelectionModel().isEmpty()) {
            return false;
        }
        return true;
    }
    @FXML
    public void initialize() throws SQLException {
        String sql = "SELECT `radius` FROM `harga_delivery`";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        ResultSet result = stm.executeQuery();
        ArrayList listRadius = new ArrayList<>();
        while (result.next()) {
            listRadius.add(result.getString(1));
        }
        listRadius.add(null);
        cbHargaDelivery.getItems().removeAll(cbPayment.getItems());
        cbHargaDelivery.getItems().addAll(listRadius);
        String sql2 = "SELECT `payment_name` FROM `payment`";
        PreparedStatement stm2 = jdbc.connection.get().prepareStatement(sql2);
        ResultSet result2 = stm2.executeQuery();
        ArrayList listPayment = new ArrayList<>();
        while(result2.next()){
            listPayment.add(result2.getString(1));
        }
        cbPayment.getItems().removeAll(cbPayment.getItems());
        cbPayment.getItems().addAll(listPayment);
        String sql3 = "SELECT `disc_name` FROM `discount`";
        PreparedStatement stm3 = jdbc.connection.get().prepareStatement(sql3);
        ResultSet result3 = stm3.executeQuery();
        ArrayList listDiscount = new ArrayList<>();
        while (result3.next()) {
            listDiscount.add(result3.getString(1));
        }
        listDiscount.add(null);
        cbDisc.getItems().removeAll(cbDisc.getItems());
        cbDisc.getItems().addAll(listDiscount);
        String sql4 = "SELECT `cust_name` FROM `customers`";
        PreparedStatement stm4 = jdbc.connection.get().prepareStatement(sql4);
        ResultSet result4 = stm4.executeQuery();
        ArrayList listCustomer = new ArrayList<>();
        while (result4.next()) {
            listCustomer.add(result4.getString(1));
        }
        cbCust.getItems().removeAll(cbCust.getItems());
        cbCust.getItems().addAll(listCustomer);
    }
    @FXML
    public void onSave() throws SQLException {
        if (isValid()){
            String sql = "SELECT `id_harga_delivery` FROM `harga_delivery` WHERE `radius` = ?";
            PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
            stm.setString(1, (String) cbHargaDelivery.getSelectionModel().getSelectedItem());
            ResultSet result = stm.executeQuery();
            String hargaDeliveryId = null;
            if (result.next()) {
                hargaDeliveryId = result.getString(1);
            }
            String sql2 = "SELECT `payment_id` FROM `payment` WHERE `payment_name` = ?";
            PreparedStatement stm2 = jdbc.connection.get().prepareStatement(sql2);
            stm2.setString(1, (String) cbPayment.getSelectionModel().getSelectedItem());
            ResultSet result2 = stm2.executeQuery();
            String paymentId = null;
            if (result2.next()) {
                paymentId = result2.getString(1);
            }
            String sql3 = "SELECT `disc_id` FROM `discount` WHERE `disc_name` = ?";
            PreparedStatement stm3 = jdbc.connection.get().prepareStatement(sql3);
            stm3.setString(1, (String) cbDisc.getSelectionModel().getSelectedItem());
            ResultSet result3 = stm3.executeQuery();
            String discountId = null;
            if (result3.next()) {
                discountId = result3.getString(1);
            }
            String sql4 = "SELECT `cust_id` FROM `customers` WHERE `cust_name` = ?";
            PreparedStatement stm4 = jdbc.connection.get().prepareStatement(sql4);
            stm4.setString(1, (String) cbCust.getSelectionModel().getSelectedItem());
            ResultSet result4 = stm4.executeQuery();
            String customerId = null;
            if (result4.next()) {
                customerId = result4.getString(1);
            }
            if(!isEdit) {
                transactionDAO.AddTransaction(txtDpAmount.getText(),txtDeliveryAddress.getText(),
                        transactionDate.getValue().toString(),discountId,paymentId, txtDeliveryId.getText(),
                        hargaDeliveryId, customerId);

            } else {
                transactionDAO.UpdateTransaction(editableTransaction.getTransactionId(), txtDpAmount.getText(),
                        txtDeliveryAddress.getText(), transactionDate.getValue().toString(), discountId,
                        paymentId, txtDeliveryId.getText(), hargaDeliveryId, customerId);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelTransaction.fxml"));
                scene.setRoot(loader.load());
                TransactionController transactionController = loader.getController();
                transactionController.setScene(scene);
                transactionController.refreshTable();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Harap Cek Data Kembali !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }
    @FXML
    public void onCancel(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelTransaction.fxml"));
            scene.setRoot(loader.load());
            TransactionController transactionController = loader.getController();
            transactionController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEditableTransaction(Transaction editableTransaction) {
        this.editableTransaction = editableTransaction;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
