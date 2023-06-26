package DAO;

import com.example.bdmaven.JDBC;
import entity.Delivery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class DeliveryDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<Delivery> getAllDelivery(){
        Collection<Delivery> deliveries = new ArrayList<>();
        String sql = "SELECT * FROM `delivery`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("id_delivery");
                    String date = resultSet.getString("tanggal_pengembalian");
                    String employee_id = resultSet.getString("employee_id");

                    Delivery delivery = new Delivery(id, date, employee_id);
                    deliveries.add(delivery);

//                    LOGGER.log(Level.INFO, "Found {0} in database", delivery);
                }

            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
        });
        return deliveries;
    }

    public void addDelivery(String date, String emp_id) throws SQLException {
        String sql = "INSERT INTO `delivery`(" +
                "`tanggal_pengembalian`, " +
                "`employee_id`)" +
                " VALUES (" +
                "?," +
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1,date);
        stm.setString(2, emp_id);
        stm.execute();
    }

    public void deleteDelivery(String id_delivery) throws SQLException {
        String sql = "DELETE FROM `delivery` WHERE `id_delivery` = " + id_delivery ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void updateDelivery(String id_delivery, String date, String emp_id) throws SQLException {
        String sql = "UPDATE `delivery` SET " +
                "`tanggal_pengembalian` = ?," +
                "`employee_id` = ?" +
                " WHERE `id_delivery` = " + id_delivery;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, date);
        stm.setString(2, emp_id);
        stm.execute();
    }
}
