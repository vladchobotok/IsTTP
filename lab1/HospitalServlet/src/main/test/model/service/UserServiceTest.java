package model.service;

import model.entity.Patient;
import model.entity.User;
import model.pool.ConnectionPool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {
    private Connection connection;
    private UserService userService;

    @Before
    public void before() {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void registerUserAndGetUserByIdTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());

        Optional<User> actual = userService.getUserById(userId);

        Assert.assertNotNull(actual);

        userService.deleteUserById(userId);
    }

    @Test
    public void getAllUsersTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());

        List<User> userList = userService.getAllUsers();

        Assert.assertNotNull(userList);

        userService.deleteUserById(userId);
    }

    @Test
    public void signInTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());

        Optional<User> actual = userService.signIn(user.getEmail(), "DDDDDDd2");

        Assert.assertNotNull(actual);

        userService.deleteUserById(userId);
    }

    @Test
    public void updateRoleTest() {
        User user = new User("Ivan",
                "Ivanov",
                LocalDate.of(1990, 8, 13),
                "ivanivanov1308@gmail.com",
                "27D75B6D7BE237FC5CF26A893F0D42ACD502373FDE42617A94477A67322B0714",
                1);

        userService.registerUser(user);
        int userId = userService.getUserIdByEmail(user.getEmail());

        userService.updateRole(5, userId);

        Assert.assertEquals(5, userService.getUserById(userId).get().getRole().getId());

        userService.deleteUserById(userId);
    }
}
