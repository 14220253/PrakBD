package DAO;
import com.example.bdmaven.JDBC;
import entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import static com.example.bdmaven.JDBC.LOGGER;
public class ItemDao {
    private final JDBC jdbc = new JDBC();

    public Collection<Item> GetAll(){
        Collection<Item> Items = new ArrayList<>();
        String sql = "SELECT * FROM `items`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("item_id");
                    String name = resultSet.getString("item_name");
                    String harga = resultSet.getString("harga");
                    String lama_penyelesaian = resultSet.getString("lama_penyelesaian");
                    String kategori_id =resultSet.getString("kategori_id");

                    Item item = new Item(id,name,harga,lama_penyelesaian,kategori_id);
                    Items.add(item);

                    LOGGER.log(Level.INFO, "Found {0} in database", item);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return Items;
    }

    public void AddItem(String id, String name,String harga,String lama_penyeleasaian,String kategori_id) throws SQLException {
        String sql = "INSERT INTO `items`(" +
                "`item_id`, " +
                "`item_name`, " +
                "`harga`, " +
                "`lama_penyelesaian`, " +
                "`kategori_id`) "  +
                " VALUES (" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?)" ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, id);
        stm.setString(2, name);
        stm.setString(3, harga);
        stm.setString(4, lama_penyeleasaian);
        stm.setString(5, kategori_id);
        stm.execute();
    }

    public void DeleteItem(String Item_id) throws SQLException {
        String sql = "DELETE FROM `items` WHERE `item_id` = " + Item_id ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void UpdateItem(String id, String name,String harga,String lama_penyeleasaian,String kategori_id) throws SQLException {
        String sql = "UPDATE `items` SET " +
                "`item_name` = ?" +
                "`harga` = ?" +
                "`lama_penyelesaian` = ?" +
                " WHERE `item_id` = " + id;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);

        stm.execute();
    }

}
