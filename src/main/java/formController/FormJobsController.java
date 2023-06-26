package formController;

import DAO.JobsDAO;
import DAO.DeliveryDAO;
import DAO.JobsDAO;
import controllers.JobsController;
import controllers.DeliveryController;
import controllers.JobsController;
import entity.Jobs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class FormJobsController {
    @FXML
    private TextField jobName;

    private Scene scene;
    private boolean isEdit = false;
    private Jobs editableJobs;
    private static final JobsDAO jobsDAO = new JobsDAO();

    public void loadEditData(){
        jobName.setText(editableJobs.getJob_name());
    }
    private boolean isValid(){
        if(jobName.getText().isBlank() || jobName.getText().isEmpty()) {
            return false;
        }
        return true;
    }
    @FXML
    public void addData() throws SQLException {
        if (isValid()){
            if(!isEdit) {
                jobsDAO.addJob(jobName.getText());

            } else {
                jobsDAO.updateJob(editableJobs.getJob_id(),jobName.getText());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data berhasil disimpan !" );
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelJobs.fxml"));
                scene.setRoot(loader.load());
                JobsController jobsController = loader.getController();
                jobsController.setScene(scene);
                jobsController.refreshTable();
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
    }
    @FXML
    public void onCancel(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelJobs.fxml"));
            scene.setRoot(loader.load());
            JobsController jobsController = loader.getController();
            jobsController.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEditableJobs(Jobs editableJobs) {
        this.editableJobs = editableJobs;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
