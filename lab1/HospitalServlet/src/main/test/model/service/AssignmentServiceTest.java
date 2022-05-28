package model.service;

import model.entity.Diagnosis;
import model.entity.Assignment;
import model.entity.Doctor;
import model.pool.ConnectionPool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class AssignmentServiceTest {
    private Connection connection;
    private AssignmentService assignmentService;

    private DoctorService doctorService;

    @Before
    public void before() {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assignmentService = new AssignmentService();
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
    public void insertAssignmentTest() {
        int assignmentId = assignmentService.insertAssignment(1, 2, "Test assignment", 1);

        final Assignment actual = assignmentService.findAssignmentById(assignmentId);

        Assert.assertNotNull(actual);

        assignmentService.deleteAssignmentById(assignmentId);
    }


    @Test
    public void updateAssignmentExecutorTest() {
        int assignmentId = assignmentService.insertAssignment(1, 2, "Test assignment", 1);

        assignmentService.updateAssignmentExecutor(1, assignmentId);

        Doctor actual = assignmentService.findAssignmentById(assignmentId).getExecutor();

        Assert.assertEquals(1, actual.getId());

        assignmentService.deleteAssignmentById(assignmentId);
    }
}
