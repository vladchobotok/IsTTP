package model.dao.classes;

import model.dao.interfaces.AssignmentsTypeDAO;
import model.entity.AssignmentsType;
import model.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentsTypeDAOImpl implements AssignmentsTypeDAO {
    private static final Logger logger = Logger.getLogger(AssignmentsTypeDAOImpl.class);

    private static final String FIND_BY_ID = "select * from assignments_type where id =?";
    private static final String FIND_ALL_TYPE = "select * from assignments_type";

    public AssignmentsTypeDAOImpl() { }

    @Override
    public AssignmentsType findById(int id) {
        AssignmentsType assignment = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    assignment = new AssignmentsType(resultSet.getInt("id"),
                            resultSet.getString("type"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find assignment type by id", e);
        }
        return assignment;
    }

    @Override
    public List<AssignmentsType> findAllType() {
        List<AssignmentsType> types = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TYPE);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                AssignmentsType assignment = new AssignmentsType(id, type);
                types.add(assignment);
            }
        } catch (SQLException e) {
            logger.error("Can not find all Assignment types", e);
        }
        return types;
    }
}
