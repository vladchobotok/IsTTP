package model.dao.classes;

import model.dao.interfaces.DiagnosisDAO;
import model.entity.Diagnosis;
import model.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisDAOImpl implements DiagnosisDAO {
    private static final Logger logger = Logger.getLogger(DiagnosisDAOImpl.class);

    private static final String FIND_BY_ID = "select * from diagnosis where id =?";
    private static final String FIND_ALL_TYPE = "select * from diagnosis";

    public DiagnosisDAOImpl() { }

    @Override
    public Diagnosis findById(int id) {
        Diagnosis diagnosis = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    diagnosis = new Diagnosis(resultSet.getInt("id"),
                            resultSet.getString("type"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find diagnosis by id", e);
        }
        return diagnosis;
    }

    @Override
    public List<Diagnosis> findAllType() {
        List<Diagnosis> types = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TYPE);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                Diagnosis diagnosis = new Diagnosis(id, type);
                types.add(diagnosis);
            }
        } catch (SQLException e) {
            logger.error("Can not find all Diagnosis types", e);
        }
        return types;
    }
}
