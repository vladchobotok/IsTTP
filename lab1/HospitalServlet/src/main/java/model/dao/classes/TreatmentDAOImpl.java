package model.dao.classes;

import model.dao.interfaces.TreatmentDAO;
import model.entity.Treatment;
import model.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class TreatmentDAOImpl implements TreatmentDAO {
    private static final Logger logger = Logger.getLogger(TreatmentDAOImpl.class);

    private static final String FIND_BY_ID = "select * from treatments where id = ?";
    private static final String INSERT_TREATMENT = "insert into treatments(assignment_id, diagnosis_id) values(?,?)";

    private static final String UPDATE_DIAGNOSIS = "update treatments set diagnosis_id = ? where id = ?";

    private static final String DELETE_TREATMENT_BY_ID = "delete from treatments where id = ?";

    public TreatmentDAOImpl() { }

    @Override
    public Treatment findById(int id) {
        Treatment treatment = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    treatment = new Treatment(resultSet.getInt("id"),
                            resultSet.getInt("assignment_id"),
                            resultSet.getInt("diagnosis_id"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find treatment by ID", e);
        }
        return treatment;
    }

    @Override
    public int insertTreatment(int assignmentId, int diagnosisId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TREATMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, assignmentId);
            statement.setInt(2, diagnosisId);
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (generatedKeys.getInt(1));
                }
                else {
                    logger.error("Creating treatment failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Can not insert treatment", e);
            return 0;
        }
        return 0;
    }

    @Override
    public void setDiagnosis(int diagnosisId, int treatmentId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DIAGNOSIS)) {
            statement.setInt(1, diagnosisId);
            statement.setInt(2, treatmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not update diagnosis", e);
        }
    }

    @Override
    public void deleteTreatmentById(int treatmentId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TREATMENT_BY_ID)) {
            statement.setInt(1, treatmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete treatment", e);
        }
    }
}
