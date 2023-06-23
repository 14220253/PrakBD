package DAO;

import com.example.bdmaven.JDBC;
import entity.ItemDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class ItemdetailsDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<ItemDetails> GetAllItemdetails(){
        Collection<ItemDetails> itemdetails = new ArrayList<>();
        String sql = "SELECT * FROM `item_details`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String amount = resultSet.getString("amount");
                    String pilihan_laundry = resultSet.getString("pilihan_laundry");
                    String kondisi = resultSet.getString("kondisi");
                    String tanggal_pengembalian = resultSet.getString("tanggal_pengembalian");
                    String item_id = resultSet.getString("item_id");
                    String transaction_id = resultSet.getString("transaction_id");

                    ItemDetails itemDetails = new ItemDetails(amount,pilihan_laundry,kondisi,tanggal_pengembalian,item_id,transaction_id);
                    itemdetails.add(itemDetails);

                    LOGGER.log(Level.INFO, "Found {0} in database", itemDetails);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return itemdetails;
    }

    public void Add(
            String amount,
            String pilihan_laundry,
            String kondisi,
            String tanggal_pengembalian,
            String item_id,
            String transaction_id
    ) throws SQLException {
        String sql = "INSERT INTO `item_details`(" +
                "`amount`, " +
                "`pilihan_laundry`, " +
                "`kondisi`, " +
                "`tanggal_pengembalian`, " +
                "`item_id`, " +
                "`transaction_id`) " +
                " VALUES (" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, amount);
        stm.setString(2, pilihan_laundry);
        stm.setString(3, kondisi);
        stm.setString(4,tanggal_pengembalian);
        stm.setString(5, item_id);
        stm.setString(6, transaction_id);
        stm.execute();
    }

    public void Delete(String itemId) throws SQLException {
        String sql = "DELETE FROM `item_details` WHERE `item_id` = " + itemId;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void Update(
            String item_id,
            String amount,
            String pilihan_laundry,
            String kondisi,
            String tanggal_pengembalian,
            String transaction_id
    ) throws SQLException {
        String sql = "UPDATE `item_details` SET " +
                "`amount` = ?," +
                "`pilihan_laundry` = ?," +
                "`kondisi` = ?," +
                "`tanggal_pengembalian` = ?," +
                "`transaction_id` = ?" +
                " WHERE `item_id` = " + item_id;


        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, amount);
        stm.setString(2, pilihan_laundry);
        stm.setString(3, kondisi);
        stm.setString(4,tanggal_pengembalian);
        stm.setString(5,transaction_id);
        stm.execute();
    }


}
