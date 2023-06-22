package entity;

import javafx.beans.property.SimpleStringProperty;

public class Employees {
    private SimpleStringProperty employee_id;
    private SimpleStringProperty employee_name;
    private SimpleStringProperty salary;
    private SimpleStringProperty job_id;

    public Employees(String employee_id, String employee_name, String salary, String job_id) {
        this.employee_id = new SimpleStringProperty(employee_id);
        this.employee_name = new SimpleStringProperty(employee_name);
        this.salary = new SimpleStringProperty(salary);
        this.job_id = new SimpleStringProperty(job_id);
    }

    public Employees(String employee_name, String salary) {
        this.employee_name = new SimpleStringProperty(employee_name);
        this.salary = new SimpleStringProperty(salary);
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

    public String getEmployee_id() {
        return employee_id.get();
    }

    public SimpleStringProperty employee_idProperty() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id.set(employee_id);
    }

    public String getEmployee_name() {
        return employee_name.get();
    }

    public SimpleStringProperty employee_nameProperty() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name.set(employee_name);
    }

    public String getSalary() {
        return salary.get();
    }

    public SimpleStringProperty salaryProperty() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
    }
}
