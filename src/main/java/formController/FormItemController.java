package formController;
import DAO.ItemDao;
import controllers.ItemController;
import controllers.KategoriController;
import entity.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class FormItemController {
    private boolean isEdit = false;

    private static final ItemDao itemDao = new ItemDao();
    private Item editable;
    @FXML
    private TextField txtnama;

    @FXML
    private TextField txtharga;

    @FXML
    private TextField txtlamapenyelesaian;

    @FXML
    private TextField txtkategoriid;

    private Scene scene;

    public void loadEditData() {
        txtnama.setText(editable.getName());
        txtharga.setText(editable.getHarga());
        txtlamapenyelesaian.setText(editable.getLama_penyeleasaian());
        txtkategoriid.setText(editable.getKategori_id());
    }
    private boolean isValid() {
        if (txtnama.getText().isBlank() || txtnama.getText().isEmpty()
                || txtharga.getText().isBlank() || txtharga.getText().isEmpty()
                || txtlamapenyelesaian.getText().isBlank() || txtlamapenyelesaian.getText().isEmpty()
                || txtkategoriid.getText().isBlank() || txtkategoriid.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    @FXML
    public void onSave() throws SQLException {
        if(isValid()){
            if(!isEdit){
                itemDao.AddItem(txtnama.getText(),txtharga.getText(),txtlamapenyelesaian.getText(),txtkategoriid.getText());
            }
            else {
                itemDao.UpdateItem(editable.getId(), txtnama.getText(),txtharga.getText(),txtlamapenyelesaian.getText(),txtkategoriid.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listItems.fxml"));
                scene.setRoot(loader.load());
                ItemController listitemcontroller = loader.getController();
                listitemcontroller.setScene(scene);
                listitemcontroller.refreshTable();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Harap Cek Data Kembali!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void onCancel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listItems.fxml"));
            scene.setRoot(loader.load());
           ItemController itemController1 = loader.getController();
            itemController1.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public Item getEditable() {
        return editable;
    }

    public void setEditable(Item editable) {
        this.editable = editable;
    }

    public TextField getTxtnama() {
        return txtnama;
    }

    public void setTxtnama(TextField txtnama) {
        this.txtnama = txtnama;
    }

    public TextField getTxtharga() {
        return txtharga;
    }

    public void setTxtharga(TextField txtharga) {
        this.txtharga = txtharga;
    }

    public TextField getTxtlamapenyelesaian() {
        return txtlamapenyelesaian;
    }

    public void setTxtlamapenyelesaian(TextField txtlamapenyelesaian) {
        this.txtlamapenyelesaian = txtlamapenyelesaian;
    }

    public TextField getTxtkategoriid() {
        return txtkategoriid;
    }

    public void setTxtkategoriid(TextField txtkategoriid) {
        this.txtkategoriid = txtkategoriid;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
