package model.dao.classes;

import model.dao.interfaces.PatientDAO;
import model.pool.ConnectionPool;
import model.entity.Patient;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private static final Logger logger = Logger.getLogger(PatientDAOImpl.class);

    private static final String FIND_ALL_DOCTOR_PATIENTS = "select * from patients where doctor_id = ?";
    private static final String FIND_ALL_PATIENTS = "select * from patients";
    private static final String ASSIGN_DOCTOR = "update patients set doctor_id = ? where id = ?";
    private static final String ASSIGN_TREATMENT = "update patients set treatment_id = ? where id = ?";
    private static final String INSERT_PATIENT = "insert into patients(user_id, doctor_id, treatment_id) values(?,?,?)";
    private static final String FIND_PATIENT_BY_ID = "select * from patients where id = ?";
    private static final String FIND_PATIENT_BY_USER_ID = "select * from patients where user_id = ?";

    public PatientDAOImpl() {
    }

    @Override
    public Patient findPatientById(int id) {
        Patient patient = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PATIENT_BY_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    patient = new Patient(resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("doctor_id"),
                            resultSet.getInt("treatment_id"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find doctor by id", e);
        }
        return patient;
    }

    @Override
    public Patient findPatientByUserId(int id) {
        Patient patient = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PATIENT_BY_USER_ID)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    patient = new Patient(resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("doctor_id"),
                            resultSet.getInt("treatment_id"));
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find doctor by id", e);
        }
        return patient;
    }

    @Override
    public List<Patient> findAllPatients() {
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PATIENTS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user = resultSet.getInt("user_id");
                int doctor = resultSet.getInt("doctor_id");
                int treatment = resultSet.getInt("treatment_id");

                Patient patient = new Patient(id, user, doctor, treatment);
                patients.add(patient);
            }
        } catch (SQLException e) {
            logger.error("Can not find all users ", e);
        }
        return patients;
    }

    @Override
    public List<Patient> findAllDoctorPatients(int doctorId) {
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_DOCTOR_PATIENTS)) {
            statement.setInt(1, doctorId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int user = resultSet.getInt("user_id");
                    int doctor = resultSet.getInt("doctor_id");
                    int treatment = resultSet.getInt("treatment_id");

                    Patient patient = new Patient(id, user, doctor, treatment);
                    patients.add(patient);
                }
            }
        } catch (SQLException e) {
            logger.error("Can not find all doctor patients", e);
        }
        return patients;
    }

    @Override
    public void assignDoctorToPatient(int doctorId, int patientId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ASSIGN_DOCTOR)) {
            statement.setInt(1, doctorId);
            statement.setInt(2, patientId);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Can not assign doctor to patient", e);
        }
    }

    @Override
    public void assignTreatmentToPatient(int treatmentId, int patientId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ASSIGN_TREATMENT)) {
            statement.setInt(1, treatmentId);
            statement.setInt(2, patientId);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Can not assign treatment to patient", e);
        }
    }

    @Override
    public boolean insertPatient(int userId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PATIENT)) {
            statement.setInt(1, userId);
            statement.setInt(2, 7);
            statement.setInt(3, 4);
            statement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("Can not insert patient", e);
            return false;
        }
    }
}
