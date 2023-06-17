package controllers;

import DAO.JobsDAO;
import com.example.bdmaven.HelloApplication;
import entity.Jobs;
import formController.FormJobsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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
            list.remove(selectedJob);
            try {
                DAO.deleteJob(selectedJob.getJob_id());
                System.out.println("Data successfully deleted");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
    }
    @FXML
    protected void addData(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("formJobs.fxml"));
            Scene scene1 = new Scene(loader.load(), 350, 400);
            stage.setTitle("Add Job");
            loader.<FormJobsController>getController().setStage(stage);
            loader.<FormJobsController>getController().setController(this);
            loader.<FormJobsController>getController().setType("add");
            loader.<FormJobsController>getController().setDAO(DAO);
            stage.setScene(scene1);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void editData(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(app.getClass().getResource("formJobs.fxml"));
            Scene scene1 = new Scene(loader.load(), 350, 400);
            stage.setTitle("Edit Delivery");
            loader.<FormJobsController>getController().setStage(stage);
            loader.<FormJobsController>getController().setController(this);
            loader.<FormJobsController>getController().setType("edit");
            loader.<FormJobsController>getController().setDAO(DAO);
            if (selectedJob != null) {
                loader.<FormJobsController>getController().setInitialData(
                        selectedJob.getJob_id(),
                        selectedJob.getJob_name());
            }
            stage.setScene(scene1);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void back(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
