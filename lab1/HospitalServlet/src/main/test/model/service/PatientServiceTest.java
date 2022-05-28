package model.service;

import model.entity.Patient;
import model.entity.User;
import model.pool.ConnectionPool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PatientServiceTest {
    private Connection connection;
    private PatientService patientService;
    private UserService userService;

    @Before
    public void before() {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        patientService = new PatientService();
        userService = new UserService();
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerPatientAndFindPatientByUserIdTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());
        patientService.registerPatient(userId);

        final Patient actual = patientService.findPatientByUserId(userId);

        Assert.assertNotNull(actual);

        userService.deleteUserById(userId);
    }

    @Test
    public void findAllTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());
        patientService.registerPatient(userId);

        List<Patient> patientList = patientService.findAll();

        Assert.assertNotNull(patientList);

        userService.deleteUserById(userId);
    }

    @Test
    public void findAllDoctorPatientsAndAssignDoctorTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());
        patientService.registerPatient(userId);

        final Patient patient = patientService.findPatientByUserId(userId);

        patientService.assignDoctor(1, patient.getId());
        List<Patient> patientList = patientService.findAllDoctorPatients(1);

        Assert.assertNotNull(patientList);
        userService.deleteUserById(userId);
    }

    @Test
    public void assignTreatmentAndFindPatientByIdTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());
        patientService.registerPatient(userId);

        Patient patient = patientService.findPatientByUserId(userId);

        int patientId = patient.getId();

        patientService.assignTreatment(1, patientId);
        patient = patientService.findPatientById(patientId);

        Assert.assertEquals(1, patient.getTreatment().getId());
        userService.deleteUserById(userId);
    }
}
