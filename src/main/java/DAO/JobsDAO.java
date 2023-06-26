package DAO;

import com.example.bdmaven.JDBC;
import entity.Jobs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import static com.example.bdmaven.JDBC.LOGGER;

public class JobsDAO {
    private final JDBC jdbc = new JDBC();

    public Collection<Jobs> getAllJobs(){
        Collection<Jobs> jobs = new ArrayList<>();
        String sql = "SELECT * FROM `jobs`";

        jdbc.connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String id = resultSet.getString("job_id");
                    String name = resultSet.getString("job_name");

                    Jobs job = new Jobs(id, name);
                    jobs.add(job);

//                    LOGGER.log(Level.INFO, "Found {0} in database", delivery);
                }

            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
        });
        return jobs;
    }

    public void addJob(String name) throws SQLException {
        String sql = "INSERT INTO `jobs`(" +
                "`job_name`) " +
                " VALUES (" +
                "?)";
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, name);
        stm.execute();
    }

    public void deleteJob(String job_id) throws SQLException {
        String sql = "DELETE FROM `jobs` WHERE `job_id` = " + job_id ;
        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.execute();
    }

    public void updateJob(String job_id, String job_name) throws SQLException {
        String sql = "UPDATE `jobs` SET " +
                "`job_name` = ?" +
                " WHERE `job_id` = " + job_id;

        PreparedStatement stm = jdbc.connection.get().prepareStatement(sql);
        stm.setString(1, job_name);
        stm.execute();
    }
}
