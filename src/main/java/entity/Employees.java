package entity;

public class Employees {
    private int employee_id;
    private String employee_name;
    private int salary;

    public Employees(int employee_id, String employee_name, int salary) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.salary = salary;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
