package training.data;

import training.model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class StudentDaoImpl implements StudentDao {

    private final DataSource dataSource;

    public StudentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Student get(long id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.first() ? new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("group"),
                        resultSet.getInt("year")
                ) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(Student student) {
        String sql = "INSERT INTO student (`id`, `name`, `email`, `age`, `gender`, `group`, `year`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                "`name` = VALUES(`name`), `email` = VALUES(`email`), `age` = VALUES(`age`), " +
                "`gender` = VALUES(`gender`), `group` = VALUES(`group`), `year` = VALUES(`year`)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, student.id);
            preparedStatement.setString(2, student.name);
            preparedStatement.setString(3, student.email);
            preparedStatement.setInt(4, student.age);
            preparedStatement.setString(5, student.gender);
            preparedStatement.setString(6, student.group);
            preparedStatement.setInt(7, student.year);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(long id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
