package model.dao.classes;

import model.dao.interfaces.DoctorDAO;
import model.entity.Doctor;
import model.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {
    private static final Logger logger = Logger.getLogger(DoctorDAOImpl.class);

    private static final String INSERT_DOCTOR = "insert into doctors(user_id, doctor_type_id) values(?,?)";
    private static final String FIND_ALL_DOCTORS = "select * from doctors where doctor_type_id <>'1'";
    private static final String FIND_ALL_NURSES = "select * from doctors where doctor_type_id ='1'";
    private static final String FIND_DOCTOR_BY_USER_ID = "select * from doctors where user_id = ?";
    private static final String FIND_DOCTOR_BY_ID = "select * from doctors where id = ?";

    public DoctorDAOImpl() {
    }

    @Override
    public List<Doctor> findAllDoctors() {
 //       List<Doctor> doctors = new ArrayList<>();
        return getDoctors(new ArrayList<>(), FIND_ALL_DOCTORS);
    }

    @Override
    public List<Doctor> findAllNurses() {
        List<Doctor> doctors = new ArrayList<>();
        return getDoctors(doctors, FIND_ALL_NURSES);
    }

    private List<Doctor> getDoctors(List<Doctor> doctors, String sql) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int type = resultSet.getInt("doctor_type_id");

                Doctor doctor = new Doctor(id, userId, type);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            logger.error("Can not find all doctors", e);
        }
        return doctors;
    }

    @Override
    public boolean insertDoctor(int userId, int doctorType) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_DOCTOR)) {
            statement.setInt(1, userId);
            statement.setInt(2, doctorType);
            statement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("Can not insert doctor", e);
            return false;
        }
    }

    @Override
    public Doctor findDoctorByUserId(int id) {
        Doctor doctor = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DOCTOR_BY_USER_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    doctor = new Doctor(resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("doctor_type_id"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find doctor by user id", e);
        }
        return doctor;
    }

    @Override
    public Doctor findDoctorById(int id) {
        Doctor doctor = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DOCTOR_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    doctor = new Doctor(resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("doctor_type_id"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find doctor by id", e);
        }
        return doctor;
    }
}
