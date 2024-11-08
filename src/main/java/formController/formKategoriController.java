package formController;
import DAO.ItemdetailsDAO;
import DAO.KategoriDao;
import controllers.KategoriController;
import controllers.ListItemDetailsController;
import entity.ItemDetails;
import entity.Kategori;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
public class formKategoriController {
    private boolean isEdit = false;

    private static final KategoriDao kategoriDao = new KategoriDao();
    private Kategori editable;
    private int index;
    @FXML
    private TextField txtnama;


    private Scene scene;

    public void loadEditData() {
        txtnama.setText(editable.getKategori_Name());

    }
    private boolean isValid() {
        if (txtnama.getText().isBlank() || txtnama.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    @FXML
    public void onSave() throws SQLException {
        if(isValid()){
            if(!isEdit){
                kategoriDao.AddKategori(txtnama.getText());
            }
            else {
                kategoriDao.Updatekategori(editable.getKategori_id(),txtnama.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listKategori.fxml"));
                scene.setRoot(loader.load());
                KategoriController listkategoricontroller = loader.getController();
                listkategoricontroller.setScene(scene);
                listkategoricontroller.refreshTable();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listKategori.fxml"));
            scene.setRoot(loader.load());
            KategoriController listkategoricontroller = loader.getController();
            listkategoricontroller.setScene(scene);

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

    public Kategori getEditable() {
        return editable;
    }

    public void setEditable(Kategori editable) {
        this.editable = editable;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TextField getTxtnama() {
        return txtnama;
    }

    public void setTxtnama(TextField txtnama) {
        this.txtnama = txtnama;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
