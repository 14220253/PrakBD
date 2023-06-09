package entity;

import javafx.beans.property.SimpleStringProperty;

public class Transaction {

    private SimpleStringProperty transactionId;
    private SimpleStringProperty totalHarga;
    private SimpleStringProperty dpAmount;
    private SimpleStringProperty deliveryAddress;
    private SimpleStringProperty radiusAddress;
    private SimpleStringProperty tanggal;


    public Transaction(String transactionId, String totalHarga, String dpAmount, String deliveryAddress, String radiusAddress, String tanggal) {
        this.transactionId = new SimpleStringProperty(transactionId);
        this.totalHarga = new SimpleStringProperty(totalHarga);
        this.dpAmount = new SimpleStringProperty(dpAmount);
        this.deliveryAddress = new SimpleStringProperty(deliveryAddress);
        this.radiusAddress = new SimpleStringProperty(radiusAddress);
        this.tanggal = new SimpleStringProperty(tanggal);
    }
    public String getTransactionId() {
        return transactionId.get();
    }
    public SimpleStringProperty transactionIdProperty() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId.set(transactionId);
    }

    public String getTotalHarga() {
        return totalHarga.get();
    }

    public SimpleStringProperty totalHargaProperty() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga.set(totalHarga);
    }

    public String getDpAmount() {
        return dpAmount.get();
    }

    public SimpleStringProperty dpAmountProperty() {
        return dpAmount;
    }

    public void setDpAmount(String dpAmount) {
        this.dpAmount.set(dpAmount);
    }

    public String getDeliveryAddress() {
        return deliveryAddress.get();
    }

    public SimpleStringProperty deliveryAddressProperty() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress.set(deliveryAddress);
    }

    public String getRadiusAddress() {
        return radiusAddress.get();
    }

    public SimpleStringProperty radiusAddressProperty() {
        return radiusAddress;
    }

    public void setRadiusAddress(String radiusAddress) {
        this.radiusAddress.set(radiusAddress);
    }

    public String getTanggal() {
        return tanggal.get();
    }

    public SimpleStringProperty tanggalProperty() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal.set(tanggal);
    }
}
