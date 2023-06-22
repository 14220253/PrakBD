package DAO;

import com.example.bdmaven.JDBC;
import entity.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class TransactionDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<Transaction> GetAllTransactions(){
        Collection<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM `transaction`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("transaction_id");
                    String totalHarga = resultSet.getString("total_harga");
                    String dpAmount = resultSet.getString("dp_amount");
                    String deliveryAddress = resultSet.getString("delivery_address");
                    String radiusAddress = resultSet.getString("radius_address");
                    String tanggal = resultSet.getString("tanggal");
                    String discId = resultSet.getString("disc_id");
                    String paymentId = resultSet.getString("payment_id");
                    String deliveryId = resultSet.getString("id_delivery");
                    String hargaDeliveryId = resultSet.getString("id_harga_delivery");
                    String custId = resultSet.getString("cust_id");
                    Transaction transaction = new Transaction(id, totalHarga, dpAmount, deliveryAddress, radiusAddress,
                            tanggal,custId, paymentId, deliveryId, hargaDeliveryId,discId);
                    transactions.add(transaction);

                    LOGGER.log(Level.INFO, "Found {0} in database", transaction);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return transactions;
    }

    public void AddTransaction(
            String totalHarga,
            String dpAmount,
            String deliveryAddress,
            String radiusAddress,
            String tanggal,
            String discId,
            String paymentId,
            String deliveryId,
            String hargaDeliveryId,
            String custId
    ) throws SQLException {
        String sql = "INSERT INTO `transaction`(" +
                "`total_harga`, " +
                "`dp_amount`, " +
                "`delivery_address`, " +
                "`radius_address`, " +
                "`tanggal`, " +
                "`disc_id`, " +
                "`payment_id`, " +
                "`id_delivery`, " +
                "`id_harga_delivery`, " +
                "`cust_id`)" +
                " VALUES (" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, totalHarga);
        stm.setString(2, dpAmount);
        stm.setString(3, deliveryAddress);
        stm.setString(4, radiusAddress);
        stm.setString(5, tanggal);
        stm.setString(6, discId);
        stm.setString(7, paymentId);
        stm.setString(8, deliveryId);
        stm.setString(9, hargaDeliveryId);
        stm.setString(10, custId);
        stm.execute();
    }

    public void DeleteTransaction(String custId) throws SQLException {
        String sql = "DELETE FROM `transaction` WHERE `cust_id` = " + custId ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void UpdateTransaction(
            String transactionId,
            String totalHarga,
            String dpAmount,
            String deliveryAddress,
            String radiusAddress,
            String tanggal,
            String discId,
            String paymentId,
            String deliveryId,
            String hargaDeliveryId,
            String custId
    ) throws SQLException {
        String sql = "UPDATE `transaction` SET " +
                "`total_harga` = ?," +
                "`dp_amount` = ?," +
                "`delivery_address` = ?," +
                "`radius_address` = ?," +
                "`tanggal` = ?, " +
                "`disc_id` = ?, " +
                "`payment_id` = ?, " +
                "`id_delivery` = ?, " +
                "`id_harga_delivery` = ?, " +
                "`cust_id` = ?" +
                " WHERE `transaction_id` = " + transactionId;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, totalHarga);
        stm.setString(2, dpAmount);
        stm.setString(3, deliveryAddress);
        stm.setString(4, radiusAddress);
        stm.setString(5, tanggal);
        stm.setString(6, discId);
        stm.setString(7, paymentId);
        stm.setString(8, deliveryId);
        stm.setString(9, hargaDeliveryId);
        stm.setString(10, custId);
        stm.execute();
    }

    public String getTotal() throws SQLException {
        String sql = "SELECT sum(`total_harga`) FROM `transaction` ";

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        ResultSet result = stm.executeQuery();
        String s = "";
        if (result.next()) {
            s = result.getString(1);
        }
        return s;
    }

    public String getTotalFromMonth(int month) throws SQLException {
        String sql = "SELECT sum(`total_harga`) FROM `transaction` where extract(month from `tanggal`) = ?";

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, String.valueOf(month));
        ResultSet result = stm.executeQuery();
        String s = "";
        if (result.next()) {
            s = result.getString(1);
        }
        return s;
    }
    public String getTotalFromYear(int year) throws SQLException {
        String sql = "SELECT sum(`total_harga`) FROM `transaction` where extract(year from `tanggal`) = ?";

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, String.valueOf(year));
        ResultSet result = stm.executeQuery();
        String s = "";
        if (result.next()) {
            s = result.getString(1);
        }
        return s;
    }
}
