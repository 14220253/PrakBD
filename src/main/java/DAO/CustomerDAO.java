package DAO;

import com.example.bdmaven.JDBC;
import entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import static com.example.bdmaven.JDBC.LOGGER;

public class CustomerDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<Customer> GetAllCustomers(){
        Collection<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM `customers`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("cust_id");
                    String name = resultSet.getString("cust_name");
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("no_telp");

                    Customer customer = new Customer(id, name, address, phone);
                    customers.add(customer);

                    LOGGER.log(Level.INFO, "Found {0} in database", customer);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return customers;
    }

    public void AddCustomer(
            String name,
            String address,
            String phone
    ) throws SQLException {
        String sql = "INSERT INTO `customers`(" +
                "`cust_name`, " +
                "`address`, " +
                "`no_telp`)" +
                " VALUES (" +
                "?," +
                "?," +
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, address);
        stm.setString(3, phone);
        stm.execute();
    }

    public void DeleteCustomer(){

    }

    public void UpdateCustomer(){

    }
}
