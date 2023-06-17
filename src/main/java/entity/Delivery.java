package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Delivery {
    private SimpleStringProperty id_delivery;
    private SimpleStringProperty tanggal_pengembalian;
    private SimpleStringProperty employee_id;

    public Delivery(String id_delivery, String tanggal_pengembalian, String employee_id) {
        this.id_delivery = new SimpleStringProperty(id_delivery);
        this.tanggal_pengembalian = new SimpleStringProperty(tanggal_pengembalian);
        this.employee_id = new SimpleStringProperty(employee_id);
    }

    public String getId_delivery() {
        return id_delivery.get();
    }

    public SimpleStringProperty id_deliveryProperty() {
        return id_delivery;
    }

    public void setId_delivery(String id_delivery) {
        this.id_delivery.set(id_delivery);
    }

    public String getTanggal_pengembalian() {
        return tanggal_pengembalian.get();
    }

    public SimpleStringProperty tanggal_pengembalianProperty() {
        return tanggal_pengembalian;
    }

    public void setTanggal_pengembalian(String tanggal_pengembalian) {
        this.tanggal_pengembalian.set(tanggal_pengembalian);
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
}
