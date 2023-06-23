package DAO;

import com.example.bdmaven.JDBC;
import entity.Employees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class EmployeeDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<Employees> getAllEmployees(){
        Collection<Employees> employees = new ArrayList<>();
        String sql = "SELECT * FROM `employees`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("employee_id");
                    String name = resultSet.getString("employee_name");
                    String salary = resultSet.getString("salary");
                    String job_id = resultSet.getString("job_id");

                    Employees employee = new Employees(id, name, salary, job_id);
                    employees.add(employee);

//                    LOGGER.log(Level.INFO, "Found {0} in database", delivery);
                }

            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
        });
        return employees;
    }
    public void addEmployee(String id, String name, String salary, String job_id) throws SQLException {
        String sql = "INSERT INTO `employees`(" +
                "`employee_id`, " +
                "`employee_name`, " +
                "`salary`, " +
                "`job_id`)" +
                " VALUES (" +
                "?," +
                "?," +
                "?," +
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, id);
        stm.setString(2,name);
        stm.setString(3, salary);
        stm.setString(4, job_id);
        stm.execute();
    }
    public void deleteEmployee(String employee_id) throws SQLException {
        String sql = "DELETE FROM `employees` WHERE `employee_id` = " + employee_id ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }
    public void updateEmployee(String id, String name, String salary, String job_id) throws SQLException {
        String sql = "UPDATE `employees` SET " +
                "`employee_name` = ?," +
                "`salary` = ?," +
                "`job_id` = ?" +
                " WHERE `employee_id` = " + id;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, salary);
        stm.setString(3, job_id);
        stm.execute();
    }

    public Collection<Employees> getSortedEmployee() throws SQLException{
        Collection<Employees> collection = new ArrayList<>();

        String sql = "SELECT `employee_name`, `salary` FROM `employees` order by salary DESC";
        PreparedStatement stm = jdbc.getConnection().get().prepareStatement(sql);
        ResultSet result = stm.executeQuery();

        while (result.next()) {
            Employees e = new Employees(result.getString(1), result.getString(2));
            collection.add(e);
        }

        return collection;
    }
}
