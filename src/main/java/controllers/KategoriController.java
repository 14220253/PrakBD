package controllers;

import DAO.KategoriDao;
import entity.Kategori;
import formController.formKategoriController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class KategoriController {
    private static final KategoriDao listkategori = new KategoriDao();

    private ObservableList<Kategori> KategoriObservableList = FXCollections.observableArrayList();
    private Scene scene;

    @FXML
    private TableView tableListKategori;

    public void initialize(){
        TableColumn<Kategori, String> Kategori_id = new TableColumn<>("Kategori id");
        Kategori_id.setCellValueFactory(celldata -> {
            return celldata.getValue().kategori_idProperty();
        });
        TableColumn<Kategori, String> Kategori_name = new TableColumn<>("Nama Kategori");
        Kategori_name.setCellValueFactory(celldata -> {
            return celldata.getValue().kategori_NameProperty();
        });

        tableListKategori.getColumns().clear();

        tableListKategori.getColumns().add(Kategori_id);
        tableListKategori.getColumns().add(Kategori_name);


        tableListKategori.setPlaceholder(new Label("Tidak ada data!"));
    }
    @FXML
    public void onAdd(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formKategori.fxml"));
            scene.setRoot((Parent) loader.load());
            formKategoriController formController = loader.getController();
            formController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onEdit(){
        if(tableListKategori.getSelectionModel().getSelectedItem() != null) {
            Kategori Kategori = (Kategori) tableListKategori.getSelectionModel().getSelectedItem();
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formKategori.fxml"));
                scene.setRoot((Parent) loader.load());
                formKategoriController formController = loader.getController();
                formController.setScene(scene);
                formController.setEdit(true);
                formController.setEditable(Kategori);
                formController.loadEditData();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    @FXML
    public void onDelete() throws SQLException {
        if(tableListKategori.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                listkategori.Deletekategori(String.valueOf(((Kategori)tableListKategori.getSelectionModel().getSelectedItem())));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    @FXML
    public void back(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/Menu.fxml"));
            scene.setRoot((Parent) loader.load());
            MenuController menuController = loader.getController();
            menuController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void refreshTable(){
        KategoriObservableList.setAll(listkategori.GetAllKategori());
        tableListKategori.setItems(KategoriObservableList);
    }



    public Scene getScene() {
        return scene;
    }
}
