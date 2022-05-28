package model.dao.classes;

import model.dao.interfaces.RoleDAO;
import model.entity.Role;
import model.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = Logger.getLogger(RoleDAOImpl.class);

    private static final String FIND_ROLE_BY_ID = "select * from roles where id = ?";
    private static final String FIND_ROLE_BY_NAME = "select * from roles where name = ?";

    public RoleDAOImpl() { }

    @Override
    public Role findById(int id) {
        Role role = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ROLE_BY_ID)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    role = new Role(resultSet.getInt("id"),
                            resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find Role by Id ", e);
        }
        return role;
    }

    @Override
    public Role findByName(String name) {
        Role role = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ROLE_BY_NAME)) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    role = new Role(resultSet.getInt("id"),
                            resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find Role by name ", e);
        }
        return role;
    }
}
