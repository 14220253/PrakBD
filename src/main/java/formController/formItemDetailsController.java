package formController;

import DAO.ItemdetailsDAO;
import controllers.ListItemDetailsController;
import entity.ItemDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class formItemDetailsController {
    private boolean isEdit = false;
    
    private static final ItemdetailsDAO itemdetailsDAO = new ItemdetailsDAO();
    private static final ListItemDetailsController listitemdetails = new ListItemDetailsController();
    private ItemDetails editable;
    private int index;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtPilihanlaundry;
    @FXML
    private TextField txtKondisi;
    @FXML
    private DatePicker datePickerTanggalpengembalian;

    @FXML
    private TextField txtitem_id;

    @FXML TextField txttransaction_id;
    
    private Scene scene;

    public void loadEditData() {
        txtAmount.setText(editable.getAmount());
        txtPilihanlaundry.setText(editable.getPilihan_laundry());
        txtKondisi.setText(editable.getKondisi());
        datePickerTanggalpengembalian.setValue(LocalDate.parse(editable.getTanggal_pengembalian()));
        txtitem_id.setText(editable.getItem_id());
        txttransaction_id.setText(editable.getTransaction_id());

    }
    private boolean isValid() {
        if (txtAmount.getText().isBlank() || txtAmount.getText().isEmpty() || txtPilihanlaundry.getText().isBlank()
                || txtPilihanlaundry.getText().isEmpty()) {
            return false;
        }
        return true;
    }
 
    @FXML
    public void onSave() throws SQLException {
        if(isValid()){
            if(!isEdit){
               itemdetailsDAO.Add(txtAmount.getText(),txtPilihanlaundry.getText(),txtKondisi.getText(),datePickerTanggalpengembalian.getValue().toString(),txtitem_id.getText(),txttransaction_id.getText());
            }
            else {
                itemdetailsDAO.Update(editable.getAmount(), editable.getPilihan_laundry(), editable.getKondisi(), editable.getTanggal_pengembalian(), editable.getItem_id(), editable.getTransaction_id());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listItemdetails.fxml"));
                scene.setRoot(loader.load());
                ListItemDetailsController listItemDetailsController = loader.getController();
                listItemDetailsController.setScene(scene);
                listItemDetailsController.refreshTable();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Harap Cek Data Kembali!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
        }
        listitemdetails.refreshTable();
    }

    @FXML
    public void onCancel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listItemdetails.fxml"));
            scene.setRoot(loader.load());
            ListItemDetailsController listItemDetailsController = loader.getController();
            listItemDetailsController.setScene(scene);

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


    public ItemDetails getEditable() {
        return editable;
    }

    public void setEditable(ItemDetails editable) {
        this.editable = editable;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TextField getTxtAmount() {
        return txtAmount;
    }

    public void setTxtAmount(TextField txtAmount) {
        this.txtAmount = txtAmount;
    }

    public TextField getTxtPilihanlaundry() {
        return txtPilihanlaundry;
    }

    public void setTxtPilihanlaundry(TextField txtPilihanlaundry) {
        this.txtPilihanlaundry = txtPilihanlaundry;
    }

    public TextField getTxtKondisi() {
        return txtKondisi;
    }

    public void setTxtKondisi(TextField txtKondisi) {
        this.txtKondisi = txtKondisi;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public DatePicker getDatePickerTanggalpengembalian() {
        return datePickerTanggalpengembalian;
    }

    public void setDatePickerTanggalpengembalian(DatePicker datePickerTanggalpengembalian) {
        this.datePickerTanggalpengembalian = datePickerTanggalpengembalian;
    }

    public TextField getTxtitem_id() {
        return txtitem_id;
    }

    public void setTxtitem_id(TextField txtitem_id) {
        this.txtitem_id = txtitem_id;
    }

    public TextField getTxttransaction_id() {
        return txttransaction_id;
    }

    public void setTxttransaction_id(TextField txttransaction_id) {
        this.txttransaction_id = txttransaction_id;
    }
}

        
