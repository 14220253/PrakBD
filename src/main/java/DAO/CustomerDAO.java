package DAO;

import com.example.bdmaven.JDBC;
import entity.Customer;
import entity.SortCustomer;

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

    public void DeleteCustomer(String custId) throws SQLException {
        String sql = "DELETE FROM `customers` WHERE `cust_id` = " + custId ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void UpdateCustomer(
            String custId,
            String name,
            String address,
            String phone
    ) throws SQLException {
        String sql = "UPDATE `customers` SET " +
                "`cust_name` = ?," +
                "`address` = ?," +
                "`no_telp` = ?" +
                " WHERE `cust_id` = " + custId;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, address);
        stm.setString(3, phone);
        stm.execute();
    }

    public ResultSet sortCustomer() throws SQLException{
        String sql = "SELECT `cust_name`, count(`t`.`cust_id`) " +
                "FROM `customers` `c` " +
                "right outer join `transaction` `t` on `c`.`cust_id` = `t`.`cust_id` " +
                "group by `t`.`cust_id` " +
                "order by COUNT(`t`.`cust_id`) DESC";
        PreparedStatement stm = jdbc.getConnection().get().prepareStatement(sql);
        return stm.executeQuery();
    }
    public Collection<SortCustomer> getCustFromSort(ResultSet result) throws SQLException{
        Collection<SortCustomer> collection = new ArrayList<>();
        while (result.next()) {
            collection.add(new SortCustomer(result.getString(1), result.getString(2)));
        }
        return collection;
    }
}
