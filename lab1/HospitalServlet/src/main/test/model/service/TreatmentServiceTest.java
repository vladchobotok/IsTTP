package model.service;

import model.entity.Diagnosis;
import model.entity.Patient;
import model.entity.Treatment;
import model.entity.User;
import model.pool.ConnectionPool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class TreatmentServiceTest {

    private Connection connection;
    private TreatmentService treatmentService;

    @Before
    public void before() {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        treatmentService = new TreatmentService();
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
    public void insertTreatmentTest() {
        int treatmentId = treatmentService.insertTreatment(1, 1);

        final Treatment actual = treatmentService.findTreatmentById(treatmentId);

        Assert.assertNotNull(actual);

        treatmentService.deleteTreatmentById(treatmentId);
    }

    @Test
    public void setDiagnosisTest() {
        int treatmentId = treatmentService.insertTreatment(1, 1);

        treatmentService.setDiagnosis(2, treatmentId);

        final Diagnosis actual = treatmentService.findTreatmentById(treatmentId).getDiagnosis();

        Assert.assertEquals(2, actual.getId());

        treatmentService.deleteTreatmentById(treatmentId);
    }
}
