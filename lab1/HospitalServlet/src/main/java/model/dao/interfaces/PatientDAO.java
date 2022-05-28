package model.dao.interfaces;

import model.entity.Patient;

import java.util.List;

public interface PatientDAO {

    /**
     * The method returns all patients of specific doctor
     *
     * @param doctorId - id of doctor
     * @return - list of doctor's patients
     */
    List<Patient> findAllDoctorPatients(int doctorId);

    /**
     * The method assigns specific doctor to specific patient
     *
     * @param doctorId - id of doctor
     * @param patientId - id of patient
     */
    void assignDoctorToPatient(int doctorId, int patientId);

    /**
     * The method assigns specific treatment to specific patient
     *
     * @param treatmentId - id of treatment
     * @param patientId - id of patient
     */
    void assignTreatmentToPatient(int treatmentId, int patientId);

    /**
     * The method returns all patients
     *
     * @return - list of patients
     */
    List<Patient> findAllPatients();

    /**
     * The method inserts new patient into database
     *
     * @param userId - user id of patient
     * @return - is patient successfully inserted
     */
    boolean insertPatient(int userId);

    /**
     * The method finds patient by id and returns it
     *
     * @param id - id of patient
     * @return - expected patient
     */
    Patient findPatientById(int id);

    /**
     * The method finds doctor by user id and returns it
     *
     * @param id - user id of patient
     * @return - expected patient
     */
    Patient findPatientByUserId(int id);

}
