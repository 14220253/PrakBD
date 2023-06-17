package formController;

import DAO.DeliveryDAO;
import DAO.JobsDAO;
import controllers.DeliveryController;
import controllers.JobsController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class FormJobsController {
    @FXML
    private TextField jobId;
    @FXML
    private TextField jobName;
    private Stage stage;
    private JobsController controller;
    private String type;
    private JobsDAO dao;

    public void setType(String type) {
        this.type = type;
    }

    public void setDAO(JobsDAO DAO) {
        this.dao = DAO;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setController(JobsController controller) {
        this.controller = controller;
    }

    public void setInitialData(String jobID, String name) {
        this.jobId.setText(jobID);
        this.jobId.setEditable(false);
        this.jobName.setText(name);
    }
    @FXML
    protected void addData() {
        if (jobId.getText().length() > 0 &&
                jobName.getText().length() > 0) {
            try {
                if (type.equalsIgnoreCase("add")) {
                    dao.addJob(jobId.getText(), jobName.getText());
                    System.out.println("Data successfully added");
                } else if (type.equalsIgnoreCase("edit")) {
                    dao.updateJob(jobId.getText(), jobName.getText());
                    System.out.println("Data successfully edited");
                }
                controller.refreshTable();
                stage.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
        }
        else {
            clear();
        }
    }
    @FXML
    protected void clear() {
        if (!type.equalsIgnoreCase("edit")) {
            jobId.clear();
        }
        jobName.clear();
    }
}
