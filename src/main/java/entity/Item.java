package entity;

import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty harga;
    private SimpleStringProperty lama_penyeleasaian;
    private SimpleStringProperty kategori_id;

    public Item(String id, String name, String harga, String lama_penyeleasaian, String kategori_id) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.harga = new SimpleStringProperty(harga);
        this.lama_penyeleasaian = new SimpleStringProperty(lama_penyeleasaian);
        this.kategori_id = new SimpleStringProperty(kategori_id);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getLama_penyeleasaian() {
        return lama_penyeleasaian.get();
    }

    public SimpleStringProperty lama_penyeleasaianProperty() {
        return lama_penyeleasaian;
    }

    public void setLama_penyeleasaian(String lama_penyeleasaian) {
        this.lama_penyeleasaian.set(lama_penyeleasaian);
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
}
