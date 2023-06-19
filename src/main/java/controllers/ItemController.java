package controllers;
import DAO.ItemDao;
import DAO.ItemDao;
import entity.Item;
import entity.Item;
import formController.FormItemController;
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
public class ItemController {
    private static final ItemDao itemDao = new ItemDao();

    private ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
    private Scene scene;

    @FXML
    private TableView<Item> tableListItem;

    @FXML
    public void initialize(){
        TableColumn<Item, String> Item_id = new TableColumn<>("Item id");
        Item_id.setCellValueFactory(celldata -> celldata.getValue().idProperty());

        TableColumn<Item, String> Item_name = new TableColumn<>("Item name");
        Item_name.setCellValueFactory(celldata -> celldata.getValue().nameProperty());

        TableColumn<Item, String> harga = new TableColumn<>("Harga");
        harga.setCellValueFactory(celldata -> celldata.getValue().hargaProperty());

        TableColumn<Item, String> lama_penyelesaian = new TableColumn<>("lama penyelesaian");
        lama_penyelesaian.setCellValueFactory(celldata -> celldata.getValue().lama_penyeleasaianProperty());

        TableColumn<Item, String> kategori_id = new TableColumn<>("kategori id");
        kategori_id.setCellValueFactory(celldata -> celldata.getValue().kategori_idProperty());

        tableListItem.getColumns().clear();
        tableListItem.getColumns().add(Item_id);
        tableListItem.getColumns().add(Item_name);
        tableListItem.getColumns().add(harga);
        tableListItem.getColumns().add(lama_penyelesaian);
        tableListItem.getColumns().add(kategori_id);
        itemObservableList.setAll(itemDao.GetAll());
        tableListItem.setItems(itemObservableList);
        tableListItem.setPlaceholder(new Label("Tidak ada data!"));
    }
    @FXML
    public void onAdd(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formItem.fxml"));
            scene.setRoot((Parent) loader.load());
            FormItemController formController = loader.getController();
            formController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onEdit(){
        if(tableListItem.getSelectionModel().getSelectedItem() != null) {
            Item items = tableListItem.getSelectionModel().getSelectedItem();
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formItem.fxml"));
                scene.setRoot((Parent) loader.load());
                FormItemController formController = loader.getController();
                formController.setScene(scene);
                formController.setEdit(true);
                formController.setEditable(items);
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
        if(tableListItem.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Apakah anda yakin ingin menghapus data ?" );
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                itemDao.DeleteItem(( tableListItem.getSelectionModel().getSelectedItem()).getId());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
        }
        refreshTable();
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
        itemObservableList.setAll(itemDao.GetAll());
        tableListItem.setItems(itemObservableList);
    }


    public ObservableList<Item> getItemObservableList() {
        return itemObservableList;
    }

    public void setItemObservableList(ObservableList<Item> ItemObservableList) {
        ItemObservableList = ItemObservableList;
    }

    public TableView getTableListItem() {
        return tableListItem;
    }


    public Scene getScene() {
        return scene;
    }
}

