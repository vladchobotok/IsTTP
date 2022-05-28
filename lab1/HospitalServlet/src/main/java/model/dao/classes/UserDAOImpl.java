package model.dao.classes;

import model.dao.interfaces.UserDAO;
import model.entity.User;
import model.pool.ConnectionPool;
import org.apache.log4j.Logger;
import utils.hashing.Hasher;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
    private static final Hasher hasher = new Hasher();
    private final static String FIND_BY_ID = "select * from users where id =?";
    private final static String FIND_ALL = "select * from users";
    private final static String FIND_BY_EMAIL_AND_PASS = "select * from users where email = ? and password = ?";
    private final static String FIND_BY_EMAIL = "select * from users where email = ? ";
    private final static String INSERT_USER = "insert into users(name, surname, birthday, email, password, role_id)  " +
            "values (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ROLE = "update users set role_id = ? where id = ?";

    private static final String DELETE_USER_BY_ID = "delete from users where id = ?";

    public UserDAOImpl() {
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                String email = resultSet.getString("email");
                String pass = resultSet.getString("password");
                int role = resultSet.getInt("role_id");

                User user = new User(id, name, surname, birthday, email, pass, role);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Can not find all users ", e);
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setDate(3, Date.valueOf(user.getBirthday()));
            statement.setString(4, user.getEmail());
            statement.setString(5, hasher.hashPassword(user.getPassword()));
            statement.setInt(6, user.getRole().getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("Can not insert into user table ", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Something went wrong with hashing ", e);
        }
        return false;
    }

    @Override
    public Optional<User> findByEmailAndPass(String email, String password) {
        User user;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASS)) {
            statement.setString(1, email);
            statement.setString(2, hasher.hashPassword(password));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("role_id"));
                return Optional.of(user);
            }
        }
        catch (SQLException e) {
            logger.error("Can not find by name and email ", e);
        }
        catch (NoSuchAlgorithmException e) {
            logger.error("Something went wrong with hashing ", e);
        }
        return Optional.empty();
    }

    @Override
    public int findUserIdByEmail(String email) {
        User user;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("role_id"));
                return user.getId();
            }
        }
        catch (SQLException e) {
            logger.error("Can not find by email ", e);
        }
        return 0;
    }

    @Override
    public Optional<User> findById(int id) {
        User user;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getDate("birthday").toLocalDate(),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getInt("role_id"));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find by id ", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateRole(int role, int userId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ROLE)) {
            statement.setInt(1, role);
            statement.setInt(2, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Can not update role", e);
        }
    }

    @Override
    public boolean deleteUserById(int userId){
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Can not delete user by id", e);
            return false;
        }
    }
}
