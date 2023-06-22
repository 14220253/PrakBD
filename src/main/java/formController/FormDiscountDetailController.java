package formController;

import DAO.DiscountDetailDAO;
import controllers.DiscountDetailController;
import entity.DiscountDetail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class FormDiscountDetailController {
    
    @FXML
    private TextField txtCategoryId;

    @FXML
    private TextField txtDiscountId;
    private Scene scene;
    private DiscountDetail editableDiscountDetail;
    private static final DiscountDetailDAO discountDetailDAO = new DiscountDetailDAO();
    private static final DiscountDetailController discountDetailController= new DiscountDetailController();

    public void loadEditData(){
        txtDiscountId.setText(editableDiscountDetail.getDiscountId());
        txtCategoryId.setText(editableDiscountDetail.getCategoryId());
    }
    private boolean isValid(){
        if(txtDiscountId.getText().isBlank() || txtDiscountId.getText().isEmpty()
                || txtCategoryId.getText().isBlank() || txtCategoryId.getText().isEmpty()) {
            return false;
        }
        return true;
    }
    @FXML
    public void onSave() throws SQLException {
        if (isValid()){
            discountDetailDAO.AddDiscountDetail(txtDiscountId.getText(),txtCategoryId.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelDiscountDetails.fxml"));
                scene.setRoot(loader.load());
                DiscountDetailController discountDetailController = loader.getController();
                discountDetailController.setScene(scene);
                discountDetailController.refreshTable();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Harap Cek Data Kembali !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
        discountDetailController.refreshTable();
    }
    @FXML
    public void onCancel(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelDiscountDetails.fxml"));
            scene.setRoot(loader.load());
            DiscountDetailController discountDetailController = loader.getController();
            discountDetailController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEditableDiscountDetail(DiscountDetail editableDiscountDetail) {
        this.editableDiscountDetail = editableDiscountDetail;
    }
}
