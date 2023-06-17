package entity;

import javafx.beans.property.SimpleStringProperty;

public class Jobs {
    private SimpleStringProperty job_id;
    private SimpleStringProperty  job_name;

    public Jobs(String job_id, String job_name) {
        this.job_id = new SimpleStringProperty(job_id);
        this.job_name = new SimpleStringProperty(job_name);
    }

    public String getJob_id() {
        return job_id.get();
    }

    public SimpleStringProperty job_idProperty() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id.set(job_id);
    }

    public String getJob_name() {
        return job_name.get();
    }

    public SimpleStringProperty job_nameProperty() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name.set(job_name);
    }
}
