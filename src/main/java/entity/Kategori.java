package entity;

import javafx.beans.property.SimpleStringProperty;

public class Kategori {
    private SimpleStringProperty kategori_id;
    private SimpleStringProperty kategori_Name;


    public Kategori(String kategori_id, String kategori_Name) {
        this.kategori_Name = new SimpleStringProperty(kategori_id);
        this.kategori_id = new SimpleStringProperty(kategori_Name);
    }

    public String getKategori_id() {
        return kategori_id.get();
    }

    public SimpleStringProperty kategori_idProperty() {
        return kategori_id;
    }

    public void setKategori_id(String kategori_id) {
        this.kategori_id.set(kategori_id);
    }

    public String getKategori_Name() {
        return kategori_Name.get();
    }

    public SimpleStringProperty kategori_NameProperty() {
        return kategori_Name;
    }

    public void setKategori_Name(String kategori_Name) {
        this.kategori_Name.set(kategori_Name);
    }
}
