package entity;

import javafx.beans.property.SimpleStringProperty;

public class Payment {
    private SimpleStringProperty paymentId;
    private SimpleStringProperty paymentName;
    public Payment(String paymentId, String paymentName) {
        this.paymentId = new SimpleStringProperty(paymentId);
        this.paymentName = new SimpleStringProperty(paymentName);
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

    public String getPaymentName() {
        return paymentName.get();
    }

    public SimpleStringProperty paymentNameProperty() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName.set(paymentName);
    }
}
