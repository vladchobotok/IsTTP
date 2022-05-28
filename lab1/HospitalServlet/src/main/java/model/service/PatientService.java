package model.service;

import model.dao.classes.PatientDAOImpl;
import model.entity.Patient;
import utils.factory.dao.DaoFactory;
import utils.factory.dao.DaoFactoryImpl;

import java.util.List;

public class PatientService {
    private static final DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    private static final PatientDAOImpl patientDAO = daoFactory.createPatientDAO();

    public PatientService() {
    }

    public List<Patient> findAll() {
        return patientDAO.findAllPatients();
    }

    public List<Patient> findAllDoctorPatients(int doctorId) {
        return patientDAO.findAllDoctorPatients(doctorId);
    }

    public void assignDoctor(int doctorId, int patientId) {
        patientDAO.assignDoctorToPatient(doctorId, patientId);
    }

    public void assignTreatment(int treatmentId, int patientId) {patientDAO.assignTreatmentToPatient(treatmentId, patientId);}

    public boolean registerPatient(int userId) {
        return patientDAO.insertPatient(userId);
    }

    public Patient findPatientById(int id) {
        return patientDAO.findPatientById(id);
    }

    public Patient findPatientByUserId(int id) {
        return patientDAO.findPatientByUserId(id);
    }

}
