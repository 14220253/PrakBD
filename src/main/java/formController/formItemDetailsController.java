package formController;

import DAO.ItemdetailsDAO;
import controllers.ListItemDetailsController;
import entity.ItemDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class formItemDetailsController {
    private boolean isEdit = false;
    
    private static final ItemdetailsDAO itemdetailsDAO = new ItemdetailsDAO();
    private ItemDetails editable;
    private int index;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtKondisi;
    @FXML
    private TextField txtitem_id;

    @FXML TextField txttransaction_id;

    @FXML
    ChoiceBox cbType;
    
    private Scene scene;

    public void loadEditData() {
        txtAmount.setText(editable.getAmount());
        txtKondisi.setText(editable.getKondisi());
        cbType.setValue(editable.getPilihan_laundry());
        txtitem_id.setText(editable.getItem_id());
        txttransaction_id.setText(editable.getTransaction_id());

    }
    private boolean isValid() {
        if (txtAmount.getText().isBlank() || txtAmount.getText().isEmpty()
                || txtitem_id.getText().isBlank() || txtitem_id.getText().isEmpty()
                || txttransaction_id.getText().isBlank() || txttransaction_id.getText().isEmpty()
                || cbType.getSelectionModel().isEmpty()) {
            return false;
        }
        return true;
    }
    @FXML
    public void initialize(){
        cbType.getItems().removeAll(cbType.getItems());
        cbType.getItems().addAll("Dry Clean","Wash & Dry Clean");
    }
 
    @FXML
    public void onSave() throws SQLException {
        if(isValid()){
            if(!isEdit){
               itemdetailsDAO.Add(txtAmount.getText(), (String) cbType.getSelectionModel().getSelectedItem(),txtKondisi.getText(),txtitem_id.getText(),txttransaction_id.getText());
            }
            else {
                itemdetailsDAO.Update(txtAmount.getText(), (String) cbType.getSelectionModel().getSelectedItem(),txtKondisi.getText(),txtitem_id.getText(),txttransaction_id.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
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

        
