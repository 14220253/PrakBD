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
                    String tanggal = resultSet.getString("tanggal");
                    String discId = resultSet.getString("disc_id");
                    String paymentId = resultSet.getString("payment_id");
                    String deliveryId = resultSet.getString("id_delivery");
                    String hargaDeliveryId = resultSet.getString("id_harga_delivery");
                    String custId = resultSet.getString("cust_id");
                    Transaction transaction = new Transaction(id, totalHarga, dpAmount, deliveryAddress,
                            tanggal,custId, paymentId, deliveryId, hargaDeliveryId,discId);
                    transactions.add(transaction);

//                    LOGGER.log(Level.INFO, "Found {0} in database", transaction);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return transactions;
    }

    public void AddTransaction(
            String dpAmount,
            String deliveryAddress,
            String tanggal,
            String discId,
            String paymentId,
            String deliveryId,
            String hargaDeliveryId,
            String custId
    ) throws SQLException {
        String hargaDelivery = null;
        if(hargaDeliveryId != null){
            String sql2 = "SELECT `harga` FROM `harga_delivery` WHERE `id_harga_delivery` = ?";
            PreparedStatement stm2 = jdbc.connection.get().prepareStatement(sql2);
            stm2.setString(1, hargaDeliveryId);
            ResultSet result2 = stm2.executeQuery();
            if (result2.next()) {
                hargaDelivery = result2.getString(1);
            }
        }
        String sql = "INSERT INTO `transaction`(" +
                "`total_harga`, " +
                "`dp_amount`, " +
                "`delivery_address`, " +
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
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        System.out.println((hargaDeliveryId == null) ? "0" : hargaDelivery);
        stm.setString(1, (hargaDeliveryId == null) ? "0" : hargaDelivery);
        stm.setString(2, dpAmount);
        stm.setString(3, deliveryAddress);
        stm.setString(4, tanggal);
        stm.setString(5, discId);
        stm.setString(6, paymentId);
        stm.setString(7, (deliveryId.equals("") ? null : deliveryId));
        stm.setString(8, hargaDeliveryId);
        stm.setString(9, custId);
        stm.execute();
    }

    public void DeleteTransaction(String transactionId) throws SQLException {
        String sql = "DELETE FROM `transaction` WHERE `transaction_id` = " + transactionId;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void UpdateTransaction(
            String transactionId,
            String dpAmount,
            String deliveryAddress,
            String tanggal,
            String discId,
            String paymentId,
            String deliveryId,
            String hargaDeliveryId,
            String custId

    ) throws SQLException {
        // get total price untuk update total price di transaction
        String sql3 = "SELECT `amount`,`harga` FROM `item_details` `id` JOIN `items` `i` on `id`.`item_id` = `i`.`item_id` " +
                "WHERE `id`.`transaction_id` = ?";
        PreparedStatement stm3 = jdbc.connection.get().prepareStatement(sql3);
        stm3.setString(1, transactionId);
        ResultSet result3 = stm3.executeQuery();
        ArrayList<String> listAmount = new ArrayList<>();
        ArrayList<String> listHarga = new ArrayList<>();
        while (result3.next()) {
            listAmount.add(result3.getString(1));
            listHarga.add(result3.getString(2));
        }
        int totalHarga = 0;
        for(int i = 0; i < listAmount.size(); i++){
            totalHarga += Integer.parseInt(listAmount.get(i)) * Integer.parseInt(listHarga.get(i));
        }
        if(hargaDeliveryId != null){
            String sql5 = "SELECT `harga` FROM `harga_delivery` WHERE `id_harga_delivery` = " + hargaDeliveryId;
            PreparedStatement stm5 = jdbc.connection.get().prepareStatement(sql5);
            ResultSet result5 = stm5.executeQuery();
            String hargaDelivery = null;
            if (result5.next()) {
                hargaDelivery = result5.getString(1);
            }
            totalHarga += Integer.parseInt(hargaDelivery);
        }
        String sql = "UPDATE `transaction` SET " +
                "`total_harga` = ?," +
                "`dp_amount` = ?," +
                "`delivery_address` = ?," +
                "`tanggal` = ?, " +
                "`disc_id` = ?, " +
                "`payment_id` = ?, " +
                "`id_delivery` = ?, " +
                "`id_harga_delivery` = ?, " +
                "`cust_id` = ?" +
                " WHERE `transaction_id` = " + transactionId;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, String.valueOf(totalHarga));
        stm.setString(2, dpAmount);
        stm.setString(3, deliveryAddress);
        stm.setString(4, tanggal);
        stm.setString(5, discId);
        stm.setString(6, paymentId);
        if(deliveryId != null){
            stm.setString(7, (deliveryId.equals("") ? null : deliveryId));
        } else{
            stm.setString(7, null);
        }
        stm.setString(8, hargaDeliveryId);
        stm.setString(9, custId);
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
    public String getTotalFromBoth(int month, int year) throws SQLException {
        String sql = "SELECT sum(`total_harga`) FROM `transaction` where extract(year from `tanggal`) = ? and extract(month from `tanggal`) = ?";

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, String.valueOf(year));
        stm.setString(2, String.valueOf(month));
        ResultSet result = stm.executeQuery();
        String s = "";
        if (result.next()) {
            s = result.getString(1);
        }
        return s;
    }
}
