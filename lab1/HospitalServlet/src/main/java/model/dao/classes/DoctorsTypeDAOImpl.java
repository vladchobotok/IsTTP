package model.dao.classes;

import model.dao.interfaces.DoctorsTypeDAO;
import model.entity.DoctorsType;
import model.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorsTypeDAOImpl implements DoctorsTypeDAO {
    private static final Logger logger = Logger.getLogger(DoctorsTypeDAOImpl.class);

    private static final String FIND_BY_ID = "select * from doctors_type where id =?";
    private static final String FIND_ALL_TYPE = "select * from doctors_type";

    public DoctorsTypeDAOImpl() { }

    @Override
    public DoctorsType findById(int id) {
        DoctorsType doctor = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    doctor = new DoctorsType(resultSet.getInt("id"),
                            resultSet.getString("type"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find doctors type by id", e);
        }
        return doctor;
    }

    @Override
    public List<DoctorsType> findAllType() {
        List<DoctorsType> types = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TYPE);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                DoctorsType doctor = new DoctorsType(id, type);
                types.add(doctor);
            }
        } catch (SQLException e) {
            logger.error("Can not find all doctors types", e);
        }
        return types;
    }
}
