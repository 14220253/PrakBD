package DAO;
import com.example.bdmaven.JDBC;
import entity.Kategori;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import static com.example.bdmaven.JDBC.LOGGER;
public class KategoriDao {
    private final JDBC jdbc = new JDBC();

    public Collection<Kategori> GetAllKategori(){
        Collection<Kategori> kategoris = new ArrayList<>();
        String sql = "SELECT * FROM `kategori`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("kategori_id");
                    String name = resultSet.getString("nama_kategori");

                    Kategori kategori = new Kategori(id,name);
                    kategoris.add(kategori);

                    LOGGER.log(Level.INFO, "Found {0} in database", kategori);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        return kategoris;
    }

    public void Addkategori(String id, String name) throws SQLException {
        String sql = "INSERT INTO `kategori`(" +
                "`kategori_id`, " +
                "`nama_kategori`) "  +
                " VALUES (" +
                "?," +
                "?)" ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, id);
        stm.setString(2, name);
        stm.execute();
    }

    public void Deletekategori(String kategori_id) throws SQLException {
        String sql = "DELETE FROM `kategori` WHERE `kategori_id` = " + kategori_id ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void Updatekategori(String id, String name) throws SQLException {
        String sql = "UPDATE `kategori` SET " +
                "`nama_kategori` = ?" +
                " WHERE `kategori_id` = " + id;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);
        stm.execute();
    }

}
