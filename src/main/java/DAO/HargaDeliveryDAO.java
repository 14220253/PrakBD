package DAO;

import com.example.bdmaven.JDBC;
import entity.Employees;
import entity.HargaDelivery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class HargaDeliveryDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<HargaDelivery> getAllHargaDelivery(){
        Collection<HargaDelivery> hargaDeliveries = new ArrayList<>();
        String sql = "SELECT * FROM `harga_delivery`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("id_harga_delivery");
                    String radius = resultSet.getString("radius");
                    String harga = resultSet.getString("harga");
                    String employee_id = resultSet.getString("employee_id");

                    HargaDelivery hargaDelivery = new HargaDelivery(id, radius, harga, employee_id);
                    hargaDeliveries.add(hargaDelivery);

//                    LOGGER.log(Level.INFO, "Found {0} in database", delivery);
                }

            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
        });
        return hargaDeliveries;
    }
    public void addHargaDelivery(String id, String radius, String harga, String employee_id) throws SQLException {
        String sql = "INSERT INTO `harga_delivery`(" +
                "`id_harga_delivery`, " +
                "`radius`, " +
                "`harga`, " +
                "`employee_id`)" +
                " VALUES (" +
                "?," +
                "?," +
                "?," +
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, id);
        stm.setString(2,radius);
        stm.setString(3, harga);
        stm.setString(4, employee_id);
        stm.execute();
    }
    public void deleteHargaDelivery(String id_harga_delivery) throws SQLException {
        String sql = "DELETE FROM `harga_delivery` WHERE `id_harga_delivery` = " + id_harga_delivery ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }
    public void updateHargaDelivery(String id, String radius, String harga, String employee_id) throws SQLException {
        String sql = "UPDATE `harga_delivery` SET " +
                "`radius` = ?," +
                "`harga` = ?," +
                "`employee_id` = ?" +
                " WHERE `id_harga_delivery` = " + id;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, radius);
        stm.setString(2, harga);
        stm.setString(3, employee_id);
        stm.execute();
    }
}
