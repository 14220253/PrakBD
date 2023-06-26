package controllers;

import DAO.JobsDAO;
import com.example.bdmaven.HelloApplication;
import com.example.bdmaven.MenuController;
import entity.Jobs;
import formController.FormJobsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class JobsController {
    private HelloApplication app;
    @FXML
    private TableView<Jobs> table;
    private ObservableList<Jobs> list = FXCollections.observableArrayList();
    private static final JobsDAO DAO = new JobsDAO();
    private Jobs selectedJob;
    private Scene scene;
    public void setScene(Scene scene) {this.scene = scene;}

    @FXML
    public void initialize() {
        TableColumn<Jobs, String> idCol = new TableColumn<>("JOB_ID");
        TableColumn<Jobs, String> nameCol = new TableColumn<>("JOB_NAME");
        idCol.setCellValueFactory(cellData -> cellData.getValue().job_idProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().job_nameProperty());
        idCol.setPrefWidth(80);
        nameCol.setPrefWidth(220);

        table.getColumns().clear();
        table.getColumns().add(idCol);
        table.getColumns().add(nameCol);

        table.setPlaceholder(new Label("No content in table"));
        refreshTable();
    }
    public void refreshTable() {
        list.setAll(DAO.getAllJobs());
        table.setItems(list);
    }
    public void setApp(HelloApplication app) {
        this.app = app;
    }


    @FXML
    protected void getClicked() {
        selectedJob = table.getSelectionModel().getSelectedItem();
    }
    @FXML
    protected void deleteData() {
        if (selectedJob != null) {
            try {
                DAO.deleteJob(selectedJob.getJob_id());
                list.remove(selectedJob);
                System.out.println("Data successfully deleted");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
    }
    @FXML
    protected void addData(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formJobs.fxml"));
        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FormJobsController formJobsController = loader.getController();
        formJobsController.setScene(scene);

    }
    @FXML
    protected void editData(){
        if(table.getSelectionModel().getSelectedItem() != null) {
            Jobs jobs = (Jobs) table.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/formJobs.fxml"));
                scene.setRoot(loader.load());
                FormJobsController formJobsController = loader.getController();
                formJobsController.setScene(scene);
                formJobsController.setEdit(true);
                formJobsController.setEditableJobs(jobs);
                formJobsController.loadEditData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Tidak ada data yang dipilih");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }
    @FXML
    public void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/Menu.fxml"));
            scene.setRoot((Parent) loader.load());
            MenuController menuController = loader.getController();
            menuController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
