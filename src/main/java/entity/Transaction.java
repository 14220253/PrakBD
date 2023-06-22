package entity;

import javafx.beans.property.SimpleStringProperty;

public class Transaction {

    private SimpleStringProperty transactionId;
    private SimpleStringProperty totalHarga;
    private SimpleStringProperty dpAmount;
    private SimpleStringProperty deliveryAddress;
    private SimpleStringProperty radiusAddress;
    private SimpleStringProperty tanggal;
    private SimpleStringProperty customerId;
    private SimpleStringProperty paymentId;
    private SimpleStringProperty deliveryId;
    private SimpleStringProperty hargaDeliveryId;
    private SimpleStringProperty discId;


    public Transaction(String transactionId, String totalHarga, String dpAmount, String deliveryAddress, String radiusAddress,
                       String tanggal, String customerId, String paymentId, String deliveryId, String hargaDeliveryId,
                       String discId) {
        this.transactionId = new SimpleStringProperty(transactionId);
        this.totalHarga = new SimpleStringProperty(totalHarga);
        this.dpAmount = new SimpleStringProperty(dpAmount);
        this.deliveryAddress = new SimpleStringProperty(deliveryAddress);
        this.radiusAddress = new SimpleStringProperty(radiusAddress);
        this.tanggal = new SimpleStringProperty(tanggal);
        this.customerId = new SimpleStringProperty(customerId);
        this.paymentId = new SimpleStringProperty(paymentId);
        this.deliveryId = new SimpleStringProperty(deliveryId);
        this.hargaDeliveryId = new SimpleStringProperty(hargaDeliveryId);
        this.discId = new SimpleStringProperty(discId);

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

    public String getCustomerId() {
        return customerId.get();
    }

    public SimpleStringProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId.set(customerId);
    }

    public String getPaymentId() {
        return paymentId.get();
    }

    public SimpleStringProperty paymentIdProperty() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId.set(paymentId);
    }

    public String getDeliveryId() {
        return deliveryId.get();
    }

    public SimpleStringProperty deliveryIdProperty() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId.set(deliveryId);
    }

    public String getHargaDeliveryId() {
        return hargaDeliveryId.get();
    }

    public SimpleStringProperty hargaDeliveryIdProperty() {
        return hargaDeliveryId;
    }

    public void setHargaDeliveryId(String hargaDeliveryId) {
        this.hargaDeliveryId.set(hargaDeliveryId);
    }

    public String getDiscId() {
        return discId.get();
    }

    public SimpleStringProperty discIdProperty() {
        return discId;
    }

    public void setDiscId(String discId) {
        this.discId.set(discId);
    }
}