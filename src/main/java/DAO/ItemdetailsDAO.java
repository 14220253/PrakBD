package DAO;

import com.example.bdmaven.JDBC;
import entity.ItemDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

//                    LOGGER.log(Level.INFO, "Found {0} in database", itemDetails);
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
            String item_id,
            String transaction_id
    ) throws SQLException {

        String sql3 = "SELECT `lama_penyelesaian` FROM `items` WHERE `item_id` = ?";
        PreparedStatement stm3 = jdbc.connection.get().prepareStatement(sql3);
        stm3.setString(1, item_id);
        ResultSet result3 = stm3.executeQuery();
        String lama = "";
        while (result3.next()) {
            lama = result3.getString("lama_penyelesaian");
        }
        String sql2 = "SELECT `tanggal` FROM `transaction` WHERE `transaction_id` = ?";
        PreparedStatement stm2 = jdbc.connection.get().prepareStatement(sql2);
        stm2.setString(1, transaction_id);
        ResultSet result2 = stm2.executeQuery();
        String tanggal = "";
        while (result2.next()){
            tanggal = result2.getString("tanggal");
        }
        LocalDate date = LocalDate.parse(tanggal);

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
        stm.setString(4, String.valueOf(date.plusDays(Integer.parseInt(lama))));
        stm.setString(5, item_id);
        stm.setString(6, transaction_id);
        stm.execute();
        // get total price untuk update total price di transaction
        String sql6 = "SELECT `amount`,`harga` FROM `item_details` `id` JOIN `items` `i` on `id`.`item_id` = `i`.`item_id` " +
                "WHERE `id`.`transaction_id` = ?";
        PreparedStatement stm6 = jdbc.connection.get().prepareStatement(sql6);
        stm6.setString(1, transaction_id);
        ResultSet result6 = stm6.executeQuery();
        ArrayList<String> listAmount = new ArrayList<>();
        ArrayList<String> listHarga = new ArrayList<>();
        while (result6.next()) {
            listAmount.add(result6.getString(1));
            listHarga.add(result6.getString(2));
        }
        int totalHarga = 0;
        for(int i = 0; i < listAmount.size(); i++){
            totalHarga += Integer.parseInt(listAmount.get(i)) * Integer.parseInt(listHarga.get(i));
        }
        // get harga delivery kalau ada
        String sql4 = "SELECT `id_harga_delivery` FROM `transaction` WHERE `transaction_id` = " + transaction_id;
        PreparedStatement stm4 = jdbc.connection.get().prepareStatement(sql4);
        ResultSet result4 = stm4.executeQuery();
        String hargaDeliveryId = null;
        if (result4.next()) {
            hargaDeliveryId = result4.getString(1);
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
        // update transaction
        String sql7 = "UPDATE `transaction` SET" +
                " `total_harga` = ?" +
                " WHERE `transaction_id` = ?";
        PreparedStatement stm7 = jdbc.connection.get().prepareStatement(sql7);
        stm7.setString(1, String.valueOf(totalHarga));
        stm7.setString(2, transaction_id);
        stm7.execute();
    }

    public void Delete(String itemId, String transactionId) throws SQLException {
        String sql = "DELETE FROM `item_details` WHERE `item_id` = " + itemId + " AND `transaction_id` = " + transactionId;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void Update(
            String amount,
            String pilihan_laundry,
            String kondisi,
            String item_id,
            String transaction_id
    ) throws SQLException {
        String sql = "UPDATE `item_details` SET " +
                "`amount` = ?," +
                "`pilihan_laundry` = ?," +
                "`kondisi` = ?" +
                " WHERE `transaction_id` = ?"  +
                " AND `item_id` = " + item_id;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, amount);
        stm.setString(2, pilihan_laundry);
        stm.setString(3, kondisi);
        stm.setString(4, transaction_id);
        stm.execute();

        // get total price untuk update total price di transaction
        String sql3 = "SELECT `amount`,`harga` FROM `item_details` `id` JOIN `items` `i` on `id`.`item_id` = `i`.`item_id` " +
                "WHERE `id`.`transaction_id` = ?";
        PreparedStatement stm3 = jdbc.connection.get().prepareStatement(sql3);
        stm3.setString(1, transaction_id);
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
        // get harga delivery kalau ada
        String sql4 = "SELECT `id_harga_delivery` FROM `transaction` WHERE `transaction_id` = " + transaction_id;
        PreparedStatement stm4 = jdbc.connection.get().prepareStatement(sql4);
        ResultSet result4 = stm4.executeQuery();
        String hargaDeliveryId = null;
        if (result4.next()) {
            hargaDeliveryId = result4.getString(1);
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
        // update transaction
        String sql2 = "UPDATE `transaction` SET" +
                " `total_harga` = ?" +
                " WHERE `transaction_id` = ?";
        PreparedStatement stm2 = jdbc.connection.get().prepareStatement(sql2);
        stm2.setString(1, String.valueOf(totalHarga));
        stm2.setString(2, transaction_id);
        stm2.execute();
    }


}
