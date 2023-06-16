package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Delivery {
    private SimpleIntegerProperty id_delivery;
    private SimpleDateFormat tanggal_pengembalian;
    private SimpleIntegerProperty employee_id;

    public Delivery(int id_delivery, Date tanggal_pengembalian, int employee_id) {
        this.id_delivery = new SimpleIntegerProperty(id_delivery);
        this.tanggal_pengembalian = new SimpleDateFormat(tanggal_pengembalian.toString());
        this.employee_id = new SimpleIntegerProperty(employee_id);
    }

    public int getId_delivery() {
        return id_delivery.get();
    }

    public SimpleIntegerProperty id_deliveryProperty() {
        return id_delivery;
    }

    public SimpleDateFormat getTanggal_pengembalian() {
        return tanggal_pengembalian;
    }

    public int getEmployee_id() {
        return employee_id.get();
    }

    public SimpleIntegerProperty employee_idProperty() {
        return employee_id;
    }
}
