package entity;

public class HargaDelivery {
    private int id_harga_delivery;
    private int radius;
    private int harga;

    public HargaDelivery(int id_harga_delivery, int radius, int harga) {
        this.id_harga_delivery = id_harga_delivery;
        this.radius = radius;
        this.harga = harga;
    }

    public int getId_harga_delivery() {
        return id_harga_delivery;
    }

    public void setId_harga_delivery(int id_harga_delivery) {
        this.id_harga_delivery = id_harga_delivery;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
