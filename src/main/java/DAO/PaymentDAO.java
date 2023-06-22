package DAO;

import com.example.bdmaven.JDBC;
import entity.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class PaymentDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<Payment> GetAllPayments(){
        Collection<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM `payment`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("payment_id");
                    String name = resultSet.getString("payment_name");

                    Payment payment = new Payment(id, name);
                    payments.add(payment);

                    LOGGER.log(Level.INFO, "Found {0} in database", payment);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return payments;
    }

    public void AddPayment(
            String name
    ) throws SQLException {
        String sql = "INSERT INTO `payment`(" +
                "`payment_name`)" +
                " VALUES (" +
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);
        stm.execute();
    }

    public void DeletePayment(String paymentId) throws SQLException {
        String sql = "DELETE FROM `payment` WHERE `payment_id` = " + paymentId ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void UpdatePayment(
            String paymentId,
            String name
    ) throws SQLException {
        String sql = "UPDATE `payment` SET " +
                "`payment_name` = ?" +
                " WHERE `payment_id` = " + paymentId;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);
        stm.execute();
    }
}
