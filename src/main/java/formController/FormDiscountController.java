package formController;
import DAO.DiscoundDAO;
import DAO.ItemdetailsDAO;
import controllers.DiscountController;
import controllers.ListItemDetailsController;
import entity.Discount;
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
public class FormDiscountController {
    private boolean isEdit = false;

    private static final DiscoundDAO discoundDAO = new DiscoundDAO();
    private static final DiscountController discountController = new DiscountController();
    private Discount editable;
    private int index;
    @FXML
    private TextField txtdisc_id;
    @FXML
    private TextField txtdisc_name;
    @FXML
    private DatePicker datePickerdisc_tanggal_mulai;
    @FXML
    private DatePicker datePickerdisc_tanggal_selesai;
    @FXML
    private TextField txtdisc_percent;
    @FXML
    private TextField txtdisc_info;


    private Scene scene;

    public void loadEditData() {
        txtdisc_id.setText(editable.getDisc_id());
        txtdisc_name.setText(editable.getDisc_name());
        datePickerdisc_tanggal_mulai.setValue(LocalDate.parse(editable.getDisc_tanggal_mulai()));
        datePickerdisc_tanggal_selesai.setValue(LocalDate.parse(editable.getDisc_tanggal_selesai()));
        txtdisc_percent.setText(editable.getDisc_percent());
        txtdisc_info.setText(editable.getDisc_info());
    }
    private boolean isValid() {
        if (txtdisc_id.getText().isBlank() || txtdisc_id.getText().isEmpty() || txtdisc_name.getText().isBlank()
                || txtdisc_name.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    @FXML
    public void onSave() throws SQLException {
        if(isValid()){
            if(!isEdit){
                discoundDAO.Add(txtdisc_id.getText(),txtdisc_name.getText(),datePickerdisc_tanggal_mulai.getValue().toString(),datePickerdisc_tanggal_selesai.getValue().toString(),txtdisc_percent.getText(),txtdisc_info.getText());
            }
            else {
                discoundDAO.Update(editable.getDisc_id(), editable.getDisc_name(), editable.getDisc_tanggal_mulai(), editable.getDisc_tanggal_selesai(), editable.getDisc_percent(), editable.getDisc_info());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listDiscount.fxml"));
                scene.setRoot(loader.load());
                DiscountController discountController1 = loader.getController();
                discountController1.setScene(scene);
                discountController1.refreshTable();
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
        discountController.refreshTable();
    }

    @FXML
    public void onCancel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listDiscount.fxml"));
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


    public Discount getEditable() {
        return editable;
    }

    public void setEditable(Discount editable) {
        this.editable = editable;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TextField getTxtdisc_id() {
        return txtdisc_id;
    }

    public void setTxtdisc_id(TextField txtdisc_id) {
        this.txtdisc_id = txtdisc_id;
    }

    public TextField getTxtdisc_name() {
        return txtdisc_name;
    }

    public void setTxtdisc_name(TextField txtdisc_name) {
        this.txtdisc_name = txtdisc_name;
    }

    public DatePicker getDatePickerdisc_tanggal_mulai() {
        return datePickerdisc_tanggal_mulai;
    }

    public void setDatePickerdisc_tanggal_mulai(DatePicker datePickerdisc_tanggal_mulai) {
        this.datePickerdisc_tanggal_mulai = datePickerdisc_tanggal_mulai;
    }

    public DatePicker getDatePickerdisc_tanggal_selesai() {
        return datePickerdisc_tanggal_selesai;
    }

    public void setDatePickerdisc_tanggal_selesai(DatePicker datePickerdisc_tanggal_selesai) {
        this.datePickerdisc_tanggal_selesai = datePickerdisc_tanggal_selesai;
    }

    public TextField getTxtdisc_percent() {
        return txtdisc_percent;
    }

    public void setTxtdisc_percent(TextField txtdisc_percent) {
        this.txtdisc_percent = txtdisc_percent;
    }

    public TextField getTxtdisc_info() {
        return txtdisc_info;
    }

    public void setTxtdisc_info(TextField txtdisc_info) {
        this.txtdisc_info = txtdisc_info;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
