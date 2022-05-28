package model.dao.classes;

import model.dao.interfaces.AssignmentDAO;
import model.entity.Assignment;
import model.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class AssignmentDAOImpl implements AssignmentDAO {
    private static final Logger logger = Logger.getLogger(AssignmentDAOImpl.class);

    private static final String FIND_BY_ID = "select * from assignments where id =?";
    private static final String UPDATE_EXECUTOR = "update assignments set executor_id = ? where id = ?";
    private static final String UPDATE_PRESCRIBER = "update assignments set prescriber_id = ? where id = ?";

    private static final String INSERT_ASSIGNMENT = "insert into assignments(executor_id, prescriber_id, description, assignment_type_id) values(?,?,?,?)";

    private static final String DELETE_ASSIGNMENT_BY_ID = "delete from assignments where id = ?";

    public AssignmentDAOImpl() {
    }

    @Override
    public Assignment findById(int id) {
        Assignment assignment = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    assignment = new Assignment(resultSet.getInt("id"),
                            resultSet.getInt("executor_id"),
                            resultSet.getInt("prescriber_id"),
                            resultSet.getString("description"),
                            resultSet.getInt("assignment_type_id"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find by Id", e);
        }
        return assignment;
    }

    @Override
    public void updateAssignmentExecutor(int executorId, int id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EXECUTOR)) {
            statement.setInt(1, executorId);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not update assignment executor", e);
        }
    }

    @Override
    public void updateAssignmentPrescriber(int prescriberId, int id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRESCRIBER)) {
            statement.setInt(1, prescriberId);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not update assignment prescriber", e);
        }
    }

    @Override
    public int insertAssignment(int executorId, int prescriberId, String description, int assignmentTypeId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ASSIGNMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, executorId);
            statement.setInt(2, prescriberId);
            statement.setString(3, description);
            statement.setInt(4, assignmentTypeId);
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (generatedKeys.getInt(1));
                }
                else {
                    logger.error("Creating assignment failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Can not insert assignment", e);
            return 0;
        }
        return 0;
    }

    @Override
    public void deleteAssignmentById(int assignmentId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ASSIGNMENT_BY_ID)) {
            statement.setInt(1, assignmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete assignment", e);
        }
    }
}
