package entity;

import javafx.beans.property.SimpleStringProperty;

public class ItemDetails {
    private SimpleStringProperty amount;
    private SimpleStringProperty pilihan_laundry;
    private SimpleStringProperty kondisi;

    public ItemDetails(String amount, String pilihan_laundry, String kondisi){
        this.amount = new SimpleStringProperty(amount);
        this.pilihan_laundry = new SimpleStringProperty(pilihan_laundry);
        this.kondisi = new SimpleStringProperty(kondisi);
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
}
