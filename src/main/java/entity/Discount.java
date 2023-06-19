package entity;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Discount {
    private SimpleStringProperty disc_id;
    private SimpleStringProperty disc_name;
    private SimpleStringProperty disc_tanggal_mulai;
    private SimpleStringProperty disc_tanggal_selesai;
    private SimpleStringProperty disc_percent;
    private SimpleStringProperty disc_info;


    public Discount(String disc_id, String disc_name, String disc_tanggal_mulai, String disc_tanggal_selesai, String disc_percent, String disc_info) {
        this.disc_id = new SimpleStringProperty(disc_id);
        this.disc_name = new SimpleStringProperty(disc_name);
        this.disc_tanggal_mulai = new SimpleStringProperty(disc_tanggal_mulai);
        this.disc_tanggal_selesai = new SimpleStringProperty(disc_tanggal_selesai);
        this.disc_percent = new SimpleStringProperty(disc_percent);
        this.disc_info = new SimpleStringProperty(disc_info);
    }

    public String getDisc_id() {
        return disc_id.get();
    }

    public SimpleStringProperty disc_idProperty() {
        return disc_id;
    }

    public void setDisc_id(String disc_id) {
        this.disc_id.set(disc_id);
    }

    public String getDisc_name() {
        return disc_name.get();
    }

    public SimpleStringProperty disc_nameProperty() {
        return disc_name;
    }

    public void setDisc_name(String disc_name) {
        this.disc_name.set(disc_name);
    }

    public String getDisc_tanggal_mulai() {
        return disc_tanggal_mulai.get();
    }

    public SimpleStringProperty disc_tanggal_mulaiProperty() {
        return disc_tanggal_mulai;
    }

    public void setDisc_tanggal_mulai(String disc_tanggal_mulai) {
        this.disc_tanggal_mulai.set(disc_tanggal_mulai);
    }

    public String getDisc_tanggal_selesai() {
        return disc_tanggal_selesai.get();
    }

    public SimpleStringProperty disc_tanggal_selesaiProperty() {
        return disc_tanggal_selesai;
    }

    public void setDisc_tanggal_selesai(String disc_tanggal_selesai) {
        this.disc_tanggal_selesai.set(disc_tanggal_selesai);
    }

    public String getDisc_percent() {
        return disc_percent.get();
    }

    public SimpleStringProperty disc_percentProperty() {
        return disc_percent;
    }

    public void setDisc_percent(String disc_percent) {
        this.disc_percent.set(disc_percent);
    }

    public String getDisc_info() {
        return disc_info.get();
    }

    public SimpleStringProperty disc_infoProperty() {
        return disc_info;
    }

    public void setDisc_info(String disc_info) {
        this.disc_info.set(disc_info);
    }
}
