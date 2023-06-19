package entity;

import javafx.beans.property.SimpleStringProperty;

public class ItemDetails {
    private SimpleStringProperty amount;
    private SimpleStringProperty pilihan_laundry;
    private SimpleStringProperty kondisi;

    private SimpleStringProperty tanggal_pengembalian;

    private SimpleStringProperty item_id;
    private SimpleStringProperty transaction_id;

    public ItemDetails(String amount, String pilihan_laundry, String kondisi, String tanggal_pengembalian,String item_id,String transaction_id){
        this.amount = new SimpleStringProperty(amount);
        this.pilihan_laundry = new SimpleStringProperty(pilihan_laundry);
        this.kondisi = new SimpleStringProperty(kondisi);
        this.tanggal_pengembalian = new SimpleStringProperty(tanggal_pengembalian);
        this.item_id = new SimpleStringProperty(item_id);
        this.transaction_id = new SimpleStringProperty(transaction_id);
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getPilihan_laundry() {
        return pilihan_laundry.get();
    }

    public SimpleStringProperty pilihan_laundryProperty() {
        return pilihan_laundry;
    }

    public void setPilihan_laundry(String pilihan_laundry) {
        this.pilihan_laundry.set(pilihan_laundry);
    }

    public String getKondisi() {
        return kondisi.get();
    }

    public SimpleStringProperty kondisiProperty() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi.set(kondisi);
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

    public String getItem_id() {
        return item_id.get();
    }

    public SimpleStringProperty item_idProperty() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id.set(item_id);
    }

    public String getTransaction_id() {
        return transaction_id.get();
    }

    public SimpleStringProperty transaction_idProperty() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id.set(transaction_id);
    }
}
