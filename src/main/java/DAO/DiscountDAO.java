package DAO;
import com.example.bdmaven.JDBC;
import entity.Discount;
import analysis.SortDiscount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import static com.example.bdmaven.JDBC.LOGGER;
public class DiscountDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<Discount> GetAll(){
        Collection<Discount> discounts = new ArrayList<>();
        String sql = "SELECT * FROM `discount`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("disc_id");
                    String name = resultSet.getString("disc_name");
                    String tanggal_mulai = resultSet.getString("disc_tanggal_mulai");
                    String tanggal_selesai = resultSet.getString("disc_tanggal_selesai");
                    String percent =resultSet.getString("disc_percent");
                    String disc_info =resultSet.getString("disc_info");
                    Discount discount = new Discount(id,name,tanggal_mulai,tanggal_selesai,percent,disc_info);
                    discounts.add(discount);

//                    LOGGER.log(Level.INFO, "Found {0} in database", discount);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return discounts;
    }

    public void Add(String name,String tanggal_mulai,String tanggal_selesai,String percent,String disc_info) throws SQLException {
        String sql = "INSERT INTO `discount`(" +
                "`disc_name`, " +
                "`disc_tanggal_mulai`, " +
                "`disc_tanggal_selesai`, " +
                "`disc_percent`, "  +
                "`disc_info`) "  +
                " VALUES (" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?)" ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, tanggal_mulai);
        stm.setString(3, tanggal_selesai);
        stm.setString(4, percent);
        stm.setString(5, disc_info);
        stm.execute();
    }

    public void Delete(String disc_id) throws SQLException {
        String sql = "DELETE FROM `discount` WHERE `disc_id` = " + disc_id ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void Update(String id, String name,String tanggal_mulai,String tanggal_selesai,String percent,String disc_info) throws SQLException {
        String sql = "UPDATE `discount` SET " +
                "`disc_name` = ?," +
                "`disc_tanggal_mulai` = ?," +
                "`disc_tanggal_selesai` = ?," +
                "`disc_percent` = ?," +
                "`disc_info` = ?" +
                " WHERE `disc_id` = " + id;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);
        stm.setString(2, tanggal_mulai);
        stm.setString(3, tanggal_selesai);
        stm.setString(4, percent);
        stm.setString(5, disc_info);
        stm.execute();
    }
    public ResultSet sortDiscount() throws SQLException{
        String sql = "SELECT `disc_name`, count(`t`.`disc_id`) " +
                "FROM `discount` `d` " +
                "join `transaction` `t` on `d`.`disc_id` = `t`.`disc_id` " +
                "group by `t`.`disc_id` " +
                "order by COUNT(`t`.`disc_id`) DESC";
        PreparedStatement stm = jdbc.getConnection().get().prepareStatement(sql);
        return stm.executeQuery();
    }
    public Collection<SortDiscount> getDiscountFromSort(ResultSet result) throws SQLException{
        Collection<SortDiscount> collection = new ArrayList<>();
        while (result.next()) {
            collection.add(new SortDiscount(result.getString(1), result.getString(2)));
        }
        return collection;
    }
}
