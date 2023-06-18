package entity;

import javafx.beans.property.SimpleStringProperty;

public class HargaDelivery {
    private SimpleStringProperty id_harga_delivery;
    private SimpleStringProperty radius;
    private SimpleStringProperty harga;
    private SimpleStringProperty employee_id;

    public HargaDelivery(String id_harga_delivery, String radius, String harga, String employee_id) {
        this.id_harga_delivery = new SimpleStringProperty(id_harga_delivery);
        this.radius = new SimpleStringProperty(radius);
        this.harga = new SimpleStringProperty(harga);
        this.employee_id = new SimpleStringProperty(employee_id);
    }

    public String getEmployee_id() {
        return employee_id.get();
    }

    public SimpleStringProperty employee_idProperty() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id.set(employee_id);
    }

    public String getId_harga_delivery() {
        return id_harga_delivery.get();
    }

    public SimpleStringProperty id_harga_deliveryProperty() {
        return id_harga_delivery;
    }

    public void setId_harga_delivery(String id_harga_delivery) {
        this.id_harga_delivery.set(id_harga_delivery);
    }

    public String getRadius() {
        return radius.get();
    }

    public SimpleStringProperty radiusProperty() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius.set(radius);
    }

    public String getHarga() {
        return harga.get();
    }

    public SimpleStringProperty hargaProperty() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga.set(harga);
    }
}
