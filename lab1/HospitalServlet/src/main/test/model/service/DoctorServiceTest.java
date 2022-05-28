package model.service;

import model.entity.Doctor;
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

public class DoctorServiceTest {
    private Connection connection;
    private DoctorService doctorService;
    private UserService userService;

    @Before
    public void before() {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userService = new UserService();
        doctorService = new DoctorService();
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
    public void addDoctorAndFindDoctorByIdTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());
        doctorService.addDoctor(userId, 2);

        Doctor actual = doctorService.findDoctorById(doctorService.findDoctorByUserId(userId).getId());

        Assert.assertNotNull(actual);

        userService.deleteUserById(userId);
   }

    @Test
    public void findAllDoctorsTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());
        doctorService.addDoctor(userId, 2);

        List<Doctor> doctorList = doctorService.findAllDoctors();

        Assert.assertNotNull(doctorList);

        userService.deleteUserById(userId);
    }

    @Test
    public void findAllNursesTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());
        doctorService.addDoctor(userId, 1);

        List<Doctor> nurseList = doctorService.findAllNurses();

        Assert.assertNotNull(nurseList);

        userService.deleteUserById(userId);
    }
}
