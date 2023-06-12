package entity;

import java.util.Date;

public class Delivery {
    private int id_delivery;
    private Date tanggal_pengembalian;

    public Delivery(int id_delivery, Date tanggal_pengembalian) {
        this.id_delivery = id_delivery;
        this.tanggal_pengembalian = tanggal_pengembalian;
    }

    public int getId_delivery() {
        return id_delivery;
    }

    public void setId_delivery(int id_delivery) {
        this.id_delivery = id_delivery;
    }

    public Date getTanggal_pengembalian() {
        return tanggal_pengembalian;
    }

    public void setTanggal_pengembalian(Date tanggal_pengembalian) {
        this.tanggal_pengembalian = tanggal_pengembalian;
    }
}
