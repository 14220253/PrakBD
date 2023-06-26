package DAO;

import com.example.bdmaven.JDBC;
import entity.DiscountDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class DiscountDetailDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<DiscountDetail> GetAllDiscountDetails(){
        Collection<DiscountDetail> discountDetails = new ArrayList<>();
        String sql = "SELECT * FROM `discount_details`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String discountId = resultSet.getString("disc_id");
                    String categoryId = resultSet.getString("kategori_id");

                    DiscountDetail discountDetail = new DiscountDetail(discountId, categoryId);
                    discountDetails.add(discountDetail);

//                    LOGGER.log(Level.INFO, "Found {0} in database", discountDetail);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return discountDetails;
    }

    public void AddDiscountDetail(
            String discountId,
            String categoryId
    ) throws SQLException {
        String sql = "INSERT INTO `discount_details`(" +
                "`disc_id`, " +
                "`kategori_id`)" +
                " VALUES (" +
                "?," +
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, discountId);
        stm.setString(2, categoryId);
        stm.execute();
    }

    public void DeleteDiscountDetail(String discId, String categoryId) throws SQLException {
        String sql = "DELETE FROM `discount_details` WHERE `disc_id` = " + discId + " AND `kategori_id` = " + categoryId;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

}
